package com.dogsole.developersite.account.entity.vender;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;


@Entity(name="vender")
public class VenderEntity {

    @Id
    @Column(name = "vender_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venderId;

    @Column(name = "vender_email", unique = true)
    private String venderEmail;

    @Column(name = "vender_name")
    private String venderName;

    @Column(name = "vender_passwd")
    private String venderPasswd;

    @Column(name = "create_dt")
    @CreationTimestamp
    private LocalDateTime createDt =LocalDateTime.now();

    @Column(name = "update_dt")
    @CreationTimestamp
    private LocalDateTime updateDt =LocalDateTime.now();
}
