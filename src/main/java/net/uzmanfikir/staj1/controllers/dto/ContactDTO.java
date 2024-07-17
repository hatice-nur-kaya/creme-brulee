package net.uzmanfikir.staj1.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.uzmanfikir.staj1.persistence.Contact;

@Data
@SuperBuilder
@NoArgsConstructor
public class ContactDTO {
    private Long id;
    private String name;
    private String title;
    private String pronoun;
    private String position;
    private String department;
    private String phone;
    private String email;
    private String linkedInUrl;
    private Long companyId;

    public static ContactDTO of(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .title(contact.getTitle())
                .pronoun(contact.getPronoun())
                .position(contact.getPosition())
                .department(contact.getDepartment())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .companyId(contact.getCompany().getId())
                .linkedInUrl(contact.getLinkedInUrl())
                .build();
    }
}