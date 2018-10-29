package hu.elte.szakdolgozat.spms.model.entity.office;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODDTA_F55OD043")
@Data
public class OfficeCompanyData {

    @Id
    @Column(name = "RPAN8", nullable = false)
    private String companyIdentifier;

    @Column(name = "RPAC07")
    private String salesAgentCode;

    @Column(name = "RPDL07")
    private String salesUserName;

    @Column(name = "RPDL011", nullable = false)
    private String companyName;

    @Column(name = "RPCUSTS")
//    @Type(type = "true_false")
    private Boolean companyActive;
}
