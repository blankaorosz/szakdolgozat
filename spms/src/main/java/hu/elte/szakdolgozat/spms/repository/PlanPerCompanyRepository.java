package hu.elte.szakdolgozat.spms.repository;

import hu.elte.szakdolgozat.spms.model.Comment;
import hu.elte.szakdolgozat.spms.model.Plan;
import hu.elte.szakdolgozat.spms.model.PlanPerCompany;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface PlanPerCompanyRepository extends CrudRepository<PlanPerCompany, Long>{
}
