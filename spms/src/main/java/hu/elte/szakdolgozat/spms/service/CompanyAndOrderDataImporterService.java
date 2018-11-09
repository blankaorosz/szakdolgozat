package hu.elte.szakdolgozat.spms.service;

import hu.elte.szakdolgozat.spms.model.entity.office.OfficeCompanyData;
import hu.elte.szakdolgozat.spms.model.entity.office.OfficeOrderItemData;
import hu.elte.szakdolgozat.spms.model.entity.spms.*;
import hu.elte.szakdolgozat.spms.repository.office.OfficeCompanyDataRepository;
import hu.elte.szakdolgozat.spms.repository.office.OfficeOrderItemDataRepository;
import hu.elte.szakdolgozat.spms.repository.spms.*;
import hu.elte.szakdolgozat.spms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompanyAndOrderDataImporterService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OfficeCompanyDataRepository officeCompanyDataRepository;
    @Autowired
    OfficeOrderItemDataRepository officeOrderItemDataRepository;

    public void importByRange(Date fromDate, Date toDate) {
        List<OfficeCompanyData> officeCompanyDataList = officeCompanyDataRepository.findAll();

        for (OfficeCompanyData o : officeCompanyDataList) {
            Company company = getAndUpdateOrCreateCompanyByOfficeCompanyData(o);

            Integer fromJDEDate = DateUtil.dateToJDEDate(fromDate);
            Integer toJDEDate = DateUtil.dateToJDEDate(toDate);

            List<OfficeOrderItemData> officeOrderItemDataList =
                    officeOrderItemDataRepository
                            .findByTransactionDateJDEJulianBetweenAndTransactionTypeNotAndCompanyIdentifier(
                                    fromJDEDate,
                                    toJDEDate,
                                    OfficeOrderItemData.TRANSACTION_TYPE_STORNO,
                                    o.getCompanyIdentifier(),
                                    new Sort(Sort.Direction.ASC, "transactionId")
                            );

            createOrdersByOfficeOrderItemDataList(officeOrderItemDataList, company);
        }
    }

    private void createOrdersByOfficeOrderItemDataList(List<OfficeOrderItemData> officeOrderItemDataList, Company company) {
        String transactionId = null;
        Order order = null;

        for (OfficeOrderItemData officeOrderItemData : officeOrderItemDataList) {
            //if orderItem already imported skip processing and move onto the next item
            if (orderItemRepository.findByOfficeOrderItemCode(officeOrderItemData.getOrderItemIdentifier()).isPresent()) {
                continue;
            }

            if (!officeOrderItemData.getTransactionId().equals(transactionId)) {
                transactionId = officeOrderItemData.getTransactionId();

                if (order != null) {
                    ordersRepository.save(order);
                }

                order = new Order(
                        DateUtil.JDEDateToUnixDate(officeOrderItemData.getTransactionDateJDEJulian()),
                        company,
                        new ArrayList<>());
            }

            OrderItem orderItem = new OrderItem(
                    officeOrderItemData.getOrderItemIdentifier(),
                    Integer.valueOf(officeOrderItemData.getAmount()),
                    calcProductPrice(officeOrderItemData),
                    order,
                    getOrCreateProductByOfficeOrderItemDate(officeOrderItemData)
            );

            order.getOrderItemList().add(orderItem);
        }

        //save the last order also
        if (order != null) {
            ordersRepository.save(order);
        }
    }

    private BigDecimal calcProductPrice(OfficeOrderItemData officeOrderItemData) {
        //unit price equals transactionPrice devide by amount
        BigDecimal unitPrice = new BigDecimal(officeOrderItemData.getTransactionPrice())
                .divide(new BigDecimal(officeOrderItemData.getAmount()), RoundingMode.HALF_UP);

        return unitPrice;
    }

    private Product getOrCreateProductByOfficeOrderItemDate(OfficeOrderItemData officeOrderItemData) {
        Product product = productRepository.findByName(officeOrderItemData.getProductName());

        if (product == null) {
            product = new Product(officeOrderItemData.getProductName(),
                    calcProductPrice(officeOrderItemData),
                    officeOrderItemData.getProductUnit()
            );

            productRepository.save(product);
        }

        return product;
    }

    private Company getAndUpdateOrCreateCompanyByOfficeCompanyData(OfficeCompanyData officeCompanyData) {
        boolean updatedOrNew = false;
        Company c = companyRepository.findByName(officeCompanyData.getCompanyName());

        if (c == null) {
            //there is no Company, create new
            c = new Company(officeCompanyData.getCompanyName(),
                    officeCompanyData.getCompanyActive(),
                    getOrCreateSalesUserForCompany(officeCompanyData),
                    null);

            updatedOrNew = true;
        } else {
            //company already exists, check if any relevant update happened
            //check whether isActive status changed
            if (c.isActive() != officeCompanyData.getCompanyActive()) {
                c.setActive(officeCompanyData.getCompanyActive());

                updatedOrNew = true;
            }

            User user = c.getUser();
            //check whether any user related update happened (there was no user or the user has been changed underneath)
            if (user == null
                    || !user.getAgentCode().equals(officeCompanyData.getSalesAgentCode())
                    || !user.getUserName().equals(officeCompanyData.getSalesUserName())) {

                user = getOrCreateSalesUserForCompany(officeCompanyData);

                if (user.getCompanies() == null) {
                    user.setCompanies(new ArrayList<>());
                }
                user.getCompanies().add(c);

                updatedOrNew = true;
            }
        }

        if (updatedOrNew) {
            c = companyRepository.save(c);
        }

        return c;
    }

    private User getOrCreateSalesUserForCompany(OfficeCompanyData officeCompanyData) {
        User u = userRepository.findByUserName(officeCompanyData.getSalesUserName());

        if (u == null) {
            u = new User(officeCompanyData.getSalesUserName(),
                    officeCompanyData.getSalesAgentCode(),
                    "",
                    roleRepository.findByName(Role.RoleName.SALES),
                    null,
                    null);

            u = userRepository.save(u);
        }

        return u;
    }
}
