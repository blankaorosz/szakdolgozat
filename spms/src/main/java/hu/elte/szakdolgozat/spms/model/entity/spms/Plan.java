/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.model.entity.spms;

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
@Table(name = "PLAN")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Plan extends BaseEntity{
    @Column
    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    public enum PlanStatus {
        AT_SALES, AT_CONTROLLING, ACCEPTED, AT_SALES_FOR_EDIT
    }
    
    @JoinColumn
    @ManyToOne(targetEntity = Period.class)
    private Period period;
    
    @JoinColumn
    @ManyToOne(targetEntity = User.class)
    private User user;
    
    @OneToMany(targetEntity = Comment.class, mappedBy = "plan")
    private List<Comment> comments;

    @OneToMany(targetEntity = PlanPerCompany.class, mappedBy = "plan", cascade = CascadeType.ALL)
    private List<PlanPerCompany> planPerCompanyList;
}
