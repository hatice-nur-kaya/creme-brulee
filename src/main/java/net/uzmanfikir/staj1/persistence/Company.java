package net.uzmanfikir.staj1.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "companies")
@SuperBuilder
@NoArgsConstructor
public class Company extends BaseEntity {
    private String name;
    private String website;
    private String address;
    private String phoneNumber;
}