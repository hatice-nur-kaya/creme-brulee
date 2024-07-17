package net.uzmanfikir.staj1.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.uzmanfikir.staj1.enums.PermissionType;
import net.uzmanfikir.staj1.persistence.User;

import java.util.List;

@Data
@SuperBuilder
// DTO: Data transfer object
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private List<PermissionType> permissions;

    public static UserDTO of(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .permissions(user.getPermissions())
                .build();
    }
}
