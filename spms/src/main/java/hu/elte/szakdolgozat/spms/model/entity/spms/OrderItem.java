/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.model.entity.spms;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Blanka Orosz
 */
@Entity
@Table(name = "ORDER_ITEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderItem extends BaseEntity{
    @Column(nullable = false)
    private String officeOrderItemCode;

    @Column(nullable = false)
    private int quantity;
    
    @Column(nullable = false)
    private BigDecimal productPriceAtOrderTime;
    
    @JoinColumn
    @ManyToOne(targetEntity = Order.class)
    private Order order;
    
    @JoinColumn
    @ManyToOne(targetEntity = Product.class)
    private Product product;
}
