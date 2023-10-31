package com.dogsole.developersite.account.entity.vender;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name="venderTemp")
public class VenderTempEntity {

    @Id
    @Column(name = "vender_temp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "vender_name")
    private String name;

    @Column(name ="vender_photo")
    private String photo;

    @Column(name="vender_req_state")
    private String reqState;

    @Column(name="vender_state")
    private String state;

    @Column(name="vender_email")
    private String email;

    @Column(name="vender_phone")
    private String phone;

    @Column(name="vender_b_no")
    private String bNo;


    @Column(name = "vender_zipcode")
    private String zipcode;

    @Column(name = "vender_street_addr")
    private String streetAddr;

    @Column(name = "vender_detail_addr")
    private String detailAddr;

    @Column(name = "vender_extra_add")
    private String extraAddr;

    @Column(name = "create_dt")
    @CreationTimestamp
    private LocalDateTime createDt =LocalDateTime.now();

    @Column(name = "update_dt")
    @CreationTimestamp
    private LocalDateTime updateDt;


}
