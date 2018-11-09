package hu.elte.szakdolgozat.spms.repository.office;

import hu.elte.szakdolgozat.spms.model.entity.office.OfficeOrderItemData;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface OfficeOrderItemDataRepository extends Repository<OfficeOrderItemData, String> {
    List<OfficeOrderItemData> findByTransactionDateJDEJulianBetweenAndTransactionTypeNotAndCompanyIdentifier
            (Integer from, Integer to, String transactionType, String companyIdentifier, Sort sort);


}
