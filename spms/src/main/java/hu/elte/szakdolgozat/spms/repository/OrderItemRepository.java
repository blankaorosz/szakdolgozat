/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository;

import hu.elte.szakdolgozat.spms.model.Order;
import hu.elte.szakdolgozat.spms.model.OrderItem;
import hu.elte.szakdolgozat.spms.model.Product;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    
    Optional<OrderItem> findByQuantity(int quantity);

    Optional<OrderItem> findByProductPriceAtOrderTime(BigDecimal productPriceAtOrderTime);

    Optional<OrderItem> findByOrder(Order order);

    Optional<OrderItem> findByProduct(Product product);
}