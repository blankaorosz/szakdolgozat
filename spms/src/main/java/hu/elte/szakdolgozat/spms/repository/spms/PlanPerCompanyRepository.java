package hu.elte.szakdolgozat.spms.repository.spms;

import hu.elte.szakdolgozat.spms.model.entity.spms.PlanPerCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface PlanPerCompanyRepository extends CrudRepository<PlanPerCompany, Long>{
}
