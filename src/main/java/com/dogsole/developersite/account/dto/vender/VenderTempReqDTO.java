package com.dogsole.developersite.account.dto.vender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VenderTempReqDTO {

    private Long id;

    @NotBlank(message = "이름은 필수 입력 항목")
    private String name;

    private MultipartFile photo;

    @NotBlank(message = "이메일 필수 입력 항목")
    private String email;

    @NotBlank(message = "전화번호 필수 입력 항목")
    private String phone;

    private String state;

    @NotBlank(message = "사업자 등록 번호 필수")
    private String bNo;


    @NotBlank(message = "우편번호 필수")
    private String zipcode;
    @NotBlank(message = "주소 필수")
    private String streetAddr;
    private String detailAddr;
    @NotBlank(message = "주소 필수")
    private String extraAddr;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;
    private String reqState;


}
