package hu.elte.szakdolgozat.spms.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "PLAN_PER_COMPANY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PlanPerCompany extends BaseEntity{

    @JoinColumn
    @ManyToOne(targetEntity = Company.class)
    private Company company;

    @JoinColumn
    @ManyToOne(targetEntity = Plan.class)
    private Plan plan;

    @Column
    private BigDecimal janPricePlan;

    @Column
    private BigDecimal febPricePlan;

    @Column
    private BigDecimal marPricePlan;

    @Column
    private BigDecimal aprPricePlan;

    @Column
    private BigDecimal mayPricePlan;

    @Column
    private BigDecimal junPricePlan;

    @Column
    private BigDecimal julPricePlan;

    @Column
    private BigDecimal augPricePlan;

    @Column
    private BigDecimal sepPricePlan;

    @Column
    private BigDecimal oktPricePlan;

    @Column
    private BigDecimal novPricePlan;

    @Column
    private BigDecimal decPricePlan;
}
