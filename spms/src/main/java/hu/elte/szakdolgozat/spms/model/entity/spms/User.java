/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.model.entity.spms;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User extends BaseEntity{

    public static Object withDefaultPasswordEncoder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Column(nullable = false)
    private String userName;

    @Column
    private String agentCode;
    
    @Column(nullable = false)
    private String password;
   
    @JoinColumn
    @ManyToOne(targetEntity = Role.class)
    private Role role;
    
    @OneToMany(targetEntity = Plan.class, mappedBy = "user")
    private List<Plan> plans;
    
    @OneToMany(targetEntity = Company.class, mappedBy = "user")
    private List<Company> companies;
}
