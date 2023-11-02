package com.dogsole.developersite.account.dto.vender;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VenderTempResDTO {

    private Long id;

    private String name;

    private String photo;

    private String email;

    private String phone;

    private String state;

    private String bNo;


    private String zipcode;
    private String streetAddr;
    private String detailAddr;
    private String extraAddr;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;
    private String reqState;

}
