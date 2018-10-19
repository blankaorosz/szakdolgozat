/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository;

import hu.elte.szakdolgozat.spms.model.Company;
import hu.elte.szakdolgozat.spms.model.Order;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface OrdersRepository extends CrudRepository<Order, Long> {
    
    Optional<Order> findByOrderDate(Date orderDate);

    Optional<Order> findByCompany(Company company);
}
