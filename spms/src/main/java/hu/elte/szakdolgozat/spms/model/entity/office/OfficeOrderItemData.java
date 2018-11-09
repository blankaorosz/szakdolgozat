package hu.elte.szakdolgozat.spms.model.entity.office;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODDTA_F55OD040")
@Data
public class OfficeOrderItemData {
    public static final String TRANSACTION_TYPE_STORNO = "ES";

    @Id
    @Column(name = "RPUKID", nullable = false)
    private String orderItemIdentifier;

    @Column(name = "RPDOCO")
    private String transactionNum;

    @Column(name = "RPDCTO")
    private String transactionType;

    @Column(name = "RPAN8", nullable = false)
    private String companyIdentifier;

    @Column(name = "RPLITM", nullable = false)
    private String itemIdentifier;

    @Column(name = "RPDSC1", nullable = false)
    private String itemNameFirstPart;

    @Column(name = "RPDSC2", nullable = false)
    private String itemNameSecondPart;

    @Column(name = "RPTRDJ", nullable = false)
    private String transactionDateJDEJulian;

    @Column(name = "RPUOM1", nullable = false)
    private String productUnit;

    @Column(name = "RPUORG", nullable = false)
    private String amount;

    @Column(name = "RPAEXP", nullable = false)
    private String transactionPrice;

    @Column(name = "RPAC07")
    private String salesAgentCode;
}
