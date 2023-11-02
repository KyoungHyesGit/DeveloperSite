package com.dogsole.developersite.account.dto.vender;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VenderReqDTO {

    private Long venderId;

    @Email(message = "email 형식 유지")
    @NotBlank(message = "이메일은 필수 입력 항목")
    private String venderEmail;
    @NotBlank(message = "이름은 필수 입력 항목")
    private String venderName;
    @NotBlank(message = "패스워드는 필수 입력 항목")
    private String venderPasswd;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

    private String photo;

    private String photoUrl;

    private String phone;
    private String bNo;
    private String zipcode;
    private String streetAddr;
    private String detailAddr;
    private String extraAddr;

}
