/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.model.entity.spms;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Blanka Orosz
 */
@Entity
@Table(name = "ORDER_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Order extends BaseEntity{    
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date orderDate;
    
    @JoinColumn
    @ManyToOne(targetEntity = Company.class)
    private Company company;

    @OneToMany(targetEntity = OrderItem.class, mappedBy = "order",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<OrderItem> orderItemList;
}
