/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository.spms;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface PeriodRepository extends CrudRepository<Period, Long> {
    
    Optional<Period> findByYearPlanned(int yearPlanned);

    Optional<Period> findByPlanningEnabled(boolean planningEnabled);
}
