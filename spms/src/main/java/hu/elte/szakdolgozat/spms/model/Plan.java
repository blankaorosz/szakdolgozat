/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "PLAN")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column
    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    public enum PlanStatus {
        AT_SALES, AT_CONTROLLING, ACCEPTED, AT_SALES_FOR_EDIT //A plan with a comment at the sales is sent back to be edited
    }
    
    @JoinColumn
    @ManyToOne(targetEntity = Period.class)
    private Period period;
    
    @JoinColumn
    @ManyToOne(targetEntity = User.class)
    private User user;
    
    @OneToMany(targetEntity = Comment.class, mappedBy = "plan")
    private List<Comment> comments;
}
