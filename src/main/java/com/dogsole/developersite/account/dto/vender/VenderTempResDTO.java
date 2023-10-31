package com.dogsole.developersite.account.dto.vender;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VenderTempResDTO {

    private Long id;

    private String venderName;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

    private String photo;

    private String state;

    private String zipcode;
    private String streetAddr;
    private String detailAddr;
    private String extraAddr;

}
