package hu.elte.szakdolgozat.spms.test.service;

import hu.elte.szakdolgozat.spms.model.entity.spms.*;
import hu.elte.szakdolgozat.spms.repository.spms.CompanyRepository;
import hu.elte.szakdolgozat.spms.repository.spms.OrderItemRepository;
import hu.elte.szakdolgozat.spms.repository.spms.OrdersRepository;
import hu.elte.szakdolgozat.spms.repository.spms.UserRepository;
import hu.elte.szakdolgozat.spms.service.CompanyAndOrderDataImporterService;
import hu.elte.szakdolgozat.spms.util.DateUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class OfficeDataTest {

    private static boolean INITIALIZED = false;

    @Autowired
    private CompanyAndOrderDataImporterService importerService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void loadOfficeData() {
        if (!INITIALIZED) {
            importerService.importByRange(
                    DateUtil.parseDate("2018-01-01"),
                    new Date()
            );

            INITIALIZED = true;
        }
    }

    @Test
    public void testCompanyImport() {
        Iterable<Company> companies = companyRepository.findAll();
        long companyCount = StreamSupport.stream(companies.spliterator(),false).count();
        assertEquals(companyCount,10l);
    }

    @Test
    public void testUserImport() {
        Iterable<User> users = userRepository.findAll();
        long userCount = StreamSupport.stream(users.spliterator(),false).count();
        assertEquals(userCount,4l);
    }

    @Test
    public void testOrderItemImport() {
        Iterable<OrderItem> orderItems = orderItemRepository.findAll();
        long orderItemCount = StreamSupport.stream(orderItems.spliterator(),false).count();
        assertEquals(orderItemCount,10l);
    }

    @Test
    public void testOrderImport() {
        Iterable<Order> orders = ordersRepository.findAll();
        long orderCount = StreamSupport.stream(orders.spliterator(),false).count();
        assertEquals(orderCount,5l);
    }
}
