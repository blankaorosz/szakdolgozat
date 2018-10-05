/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "ROLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public enum RoleName {
        SALES, CONTROLLER, CEO, ADMIN
    }
    
    @OneToMany(targetEntity = User.class, mappedBy = "role")
    private List<User> user;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Right> rights;
}
