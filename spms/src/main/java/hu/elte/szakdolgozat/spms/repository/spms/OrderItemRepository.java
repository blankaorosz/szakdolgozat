/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository.spms;

import hu.elte.szakdolgozat.spms.model.entity.spms.Order;
import hu.elte.szakdolgozat.spms.model.entity.spms.OrderItem;
import hu.elte.szakdolgozat.spms.model.entity.spms.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

    List<OrderItem> findByQuantity(int quantity);

    List<OrderItem> findByProductPriceAtOrderTime(BigDecimal productPriceAtOrderTime);

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByProduct(Product product);

    Optional<OrderItem> findByOfficeOrderItemCode(String officeOrderItemCode);
}
