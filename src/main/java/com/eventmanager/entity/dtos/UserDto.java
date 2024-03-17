package com.eventmanager.entity.dtos;

import com.eventmanager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {
    private long id;
    private String login;
    private String password;
    private String displayName;
    private String role;

    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .displayName(user.getDisplayName())
                .role(user.getRole())
                .build();
    }

    public static User mapFromDto(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .displayName(userDto.getDisplayName())
                .role(userDto.getRole())
                .build();
    }
}
