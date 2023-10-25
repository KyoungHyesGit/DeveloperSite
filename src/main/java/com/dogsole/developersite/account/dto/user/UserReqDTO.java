package com.dogsole.developersite.account.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserReqDTO {
    private Long userId;

    @NotBlank(message = "이메일 필수 입력")
    @Email(message="이메일 형식 유지")
    private String userEmail;

    @NotBlank(message = "이름은 필수 입력 항목")
    private String userName;

    @NotBlank(message = "패스워드는 필수 입력 항목")
    private String passwd;

    @Pattern(regexp ="^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$", message = "휴대폰번호 확인")
    @NotBlank(message = "연락처는 필수 입력 항목")
    private String phone;

    private String birth;

    private String privacyState;

    private LocalDateTime createdAt;

    private String state;


}
