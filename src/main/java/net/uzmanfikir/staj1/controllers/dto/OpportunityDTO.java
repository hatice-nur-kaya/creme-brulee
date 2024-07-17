package net.uzmanfikir.staj1.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.uzmanfikir.staj1.enums.StatusType;
import net.uzmanfikir.staj1.persistence.BaseEntity;
import net.uzmanfikir.staj1.persistence.Opportunity;

import java.util.Date;
import java.util.Optional;

@Data
@SuperBuilder
@NoArgsConstructor
public class OpportunityDTO {
    private Long id;
    private StatusType status;
    private String discount;
    private String unit;
    private Long contactId;
    private Long salesPersonId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    public static OpportunityDTO of(Opportunity opportunity) {
        return OpportunityDTO.builder()
                .id(opportunity.getId())
                .status(opportunity.getStatus())
                .discount(opportunity.getDiscount())
                .unit(opportunity.getUnit())
                .contactId(opportunity.getContact().getId())
                .salesPersonId(Optional.ofNullable(opportunity.getSalesPerson()).map(BaseEntity::getId).orElse(null))
                .invoiceDate(opportunity.getInvoiceDate())
                .build();
    }
}
