package hu.elte.szakdolgozat.spms.repository.office;

import hu.elte.szakdolgozat.spms.model.entity.office.OfficeCompanyData;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface OfficeCompanyDataRepository extends Repository<OfficeCompanyData, String> {
}
