package net.uzmanfikir.staj1.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "contacts")
@SuperBuilder
@NoArgsConstructor
public class Contact extends BaseEntity {
    private String name;
    private String email;
    private String title;
    private String pronoun;
    private String position;
    private String department;
    private String phone;
    private String linkedInUrl;
    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;
}