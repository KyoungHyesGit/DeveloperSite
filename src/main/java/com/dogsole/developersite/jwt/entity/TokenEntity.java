package com.dogsole.developersite.jwt.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @ToString
@Setter
@Entity(name="token")
public class TokenEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="token" ,unique = true)
    private String token = this.token;

    @Column(name="tokeniss")
    private Date tokenIss;

    @Column(name="tokenexp")
    private Date tokenExp;

    public TokenEntity() {

    }

    public TokenEntity(String token, Date tokenIss, Date tokenExp){
        this.token = token;
        this.tokenIss = tokenIss;
        this.tokenExp = tokenExp;
    }

}
