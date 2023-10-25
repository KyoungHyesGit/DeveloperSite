package com.dogsole.developersite.account.dto.vender;


import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VenderResDTO {

    private Long venderId;

    private String venderEmail;

    private String venderName;

    private String venderPasswd;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;
}
