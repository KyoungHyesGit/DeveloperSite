package com.dogsole.developersite.vender.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@DynamicUpdate
@Table(name = "VENDER")
public class VenderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vender_id")
    private Long id;
    @Column(name = "vender_email")
    private String email;
    @Column(name = "vender_name")
    private String name;
    @Column(name = "vender_passwd")
    private String passwd;

    @Column(name = "photo")
    private String photo;

    @Column(name = "CREATE_DT")
    private LocalDateTime createDt;
    @Column(name = "UPDATE_DT")
    private LocalDateTime updateDt;
}
