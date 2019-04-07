package ru.phoenigm.stuffer.payload;

import lombok.Builder;
import lombok.Data;
import ru.phoenigm.stuffer.domain.User;

@Data
@Builder
public class Profile {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String avatarUrl;

    public static Profile fromUser(User user) {
        return Profile.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }
}
