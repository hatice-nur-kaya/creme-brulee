package net.uzmanfikir.staj1.controllers.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.uzmanfikir.staj1.persistence.Company;


@Data
@SuperBuilder
// DTO: Data transfer object
@NoArgsConstructor
public class CompanyDTO {
    private Long id;
    private String name;
    private String website;
    private String address;
    private String phoneNumber;


    public static CompanyDTO of(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .website(company.getWebsite())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .build();
    }
}