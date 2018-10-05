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
import javax.persistence.ManyToMany;
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
@Table(name = "RIGHT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Right {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column
    @Enumerated(EnumType.STRING)
    private RightName name;

    public enum RightName {
        WRITE_PLAN, READ_PLAN, SUBMIT_PLAN, 
        WRITE_COMMENT, READ_COMMENT,
        READ_USER, WRITE_USER
    }
    
    @ManyToMany(mappedBy = "rights")
    private List<Role> roles;
}
