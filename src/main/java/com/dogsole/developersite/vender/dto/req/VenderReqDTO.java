package com.dogsole.developersite.vender.dto.req;

import lombok.*;

import java.time.LocalDateTime;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VenderReqDTO {
    private Long id;
    private String email;
    private String name;
    private String passwd;
    private String photo;

    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}