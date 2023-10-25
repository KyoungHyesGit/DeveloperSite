package com.dogsole.developersite.account.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserResDTO {
    private Long userId;

    private String userEmail;

    private String userName;

    private String passwd;

    private String phone;

    private String birth;

    private String privacyState;

    private LocalDateTime createdAt;

    private String state;
}
