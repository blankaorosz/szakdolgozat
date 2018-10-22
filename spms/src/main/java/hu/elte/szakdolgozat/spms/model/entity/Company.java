/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.model.entity;

import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Blanka Orosz
 */
@Entity
@Table(name = "COMPANY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Company extends BaseEntity{    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private boolean active;
    
    @JoinColumn
    @ManyToOne(targetEntity = User.class)
    private User user;

    @OneToMany(targetEntity = Order.class, mappedBy = "company")
    private List<Order> orders;
}
