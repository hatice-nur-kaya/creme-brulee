package net.uzmanfikir.staj1.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.uzmanfikir.staj1.enums.PermissionType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
@SuperBuilder
@NoArgsConstructor
public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<PermissionType> permissions;
}
