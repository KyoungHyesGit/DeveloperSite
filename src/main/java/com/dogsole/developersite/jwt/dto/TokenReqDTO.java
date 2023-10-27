package com.dogsole.developersite.jwt.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TokenReqDTO {
    private Long id;
    private String token;
    private String tokenIss;
    private Date tokenExp;
}
