package ru.phoenigm.stuffer.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.phoenigm.stuffer.domain.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StufferDto {
    private Long id;
    private String avatarUrl;
    private String firstName;
    private String lastName;

    public static StufferDto from(User user) {
        return StufferDto.builder()
                .id(user.getId())
                .avatarUrl(user.getAvatarUrl())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
