/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.model.entity.spms;

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
@Table(name = "COMMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Comment extends BaseEntity{
    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String nameFrom;

    @Column(nullable = false)
    private boolean checked;
    
    @JoinColumn
    @ManyToOne(targetEntity = Plan.class)
    private Plan plan;
}
