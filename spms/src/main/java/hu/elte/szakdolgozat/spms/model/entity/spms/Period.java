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
@Table(name = "PERIOD")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Period extends BaseEntity{
    @Column(nullable = false)
    private boolean planningEnabled;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MonthName begingMonth;

    public enum MonthName {
        JAN(0), FEB(1), MAR(2), APR(3), MAY(4),
        JUN(5), JUL(6), AUG(7), SEP(8), OCT(9), NOV(10), DEC(11);

        private int index;

        MonthName(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    @Column(nullable = false)
    private int yearPlanned;
    
    @OneToMany(targetEntity = Plan.class, mappedBy = "period", fetch = FetchType.EAGER)
    private List<Plan> plans;
}
