package hu.elte.szakdolgozat.spms.repository;

import hu.elte.szakdolgozat.spms.model.entity.PlanPerCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface PlanPerCompanyRepository extends CrudRepository<PlanPerCompany, Long>{
}
