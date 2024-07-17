package net.uzmanfikir.staj1.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.uzmanfikir.staj1.enums.StatusType;

import java.util.Date;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "opportunities")
public class Opportunity extends BaseEntity {
    private String discount;
    private String unit;
    @ManyToOne
    private Contact contact;
    @ManyToOne
    private User salesPerson;
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;
    @Enumerated(EnumType.STRING)
    private StatusType status;
}
