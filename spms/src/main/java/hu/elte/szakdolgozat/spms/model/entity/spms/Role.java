/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.model.entity.spms;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
public class Role extends BaseEntity{
    @Column
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public enum RoleName {
        SALES, CONTROLLER, CEO, ADMIN
    }
    
    @OneToMany(targetEntity = User.class, mappedBy = "role")
    private List<User> user;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Right> rights;
}
