/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository.spms;

import hu.elte.szakdolgozat.spms.model.entity.spms.Company;
import hu.elte.szakdolgozat.spms.model.entity.spms.Order;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface OrdersRepository extends CrudRepository<Order, Long> {

    List<Order> findByCompanyAndOrderDateBetween(Company company, Date from, Date to);
}
