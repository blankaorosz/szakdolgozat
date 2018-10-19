/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository;

import hu.elte.szakdolgozat.spms.model.Period;
import hu.elte.szakdolgozat.spms.model.User;
import hu.elte.szakdolgozat.spms.model.Plan;
import hu.elte.szakdolgozat.spms.model.Plan.PlanStatus;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {

    Optional<Plan> findByStatus(PlanStatus status);

    Optional<Plan> findByPeriod(Period period);

    Optional<Plan> findByUser(User user);
}
