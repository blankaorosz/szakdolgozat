package hu.elte.szakdolgozat.spms.repository.office;

import hu.elte.szakdolgozat.spms.model.entity.office.OfficeCompanyData;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface OfficeCompanyDataRepository extends Repository<OfficeCompanyData, String> {
    List<OfficeCompanyData> findAll();
    
}
