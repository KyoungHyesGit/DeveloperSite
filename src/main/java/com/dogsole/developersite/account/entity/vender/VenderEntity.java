package com.dogsole.developersite.account.entity.vender;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name="vender")
public class VenderEntity {

    @Id
    @Column(name = "vender_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venderId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "vender_temp_id")
    private Long tempId;

    @Column(name = "vender_email", unique = true)
    private String venderEmail;

    @Column(name = "vender_name")
    private String venderName;

    @Column(name = "vender_passwd")
    private String venderPasswd;

    @Column(name = "create_dt")
    private LocalDateTime createDt =LocalDateTime.now();

    @Column(name = "update_dt")
    @CreationTimestamp
    private LocalDateTime updateDt;

    @Column(name ="photo")
    private String photo;

    @Column(name="photourl")
    private String photoUrl;

    @Column(name="state")
    private String state;

    @Column(name="vender_phone")
    private String phone;

    @Column(name="vender_phone_b_no")
    private String bNo;

    @Column(name = "vender_phone_zipcode")
    private String zipcode;

    @Column(name = "vender_phone_street_addr")
    private String streetAddr;

    @Column(name = "vender_phone_detail_addr")
    private String detailAddr;

    @Column(name = "vender_phone_extra_add")
    private String extraAddr;

    public void setTempToReal(VenderTempEntity venderTempEntity){
        this.userId = venderTempEntity.getUserId();
        this.venderId = venderTempEntity.getId();
        this.venderName = venderTempEntity.getName();
        this.photo = venderTempEntity.getPhoto();
        this.venderEmail = venderTempEntity.getEmail();
        this.phone = venderTempEntity.getPhone();
        this.bNo = venderTempEntity.getBNo();
        this.zipcode = venderTempEntity.getZipcode();
        this.streetAddr = venderTempEntity.getStreetAddr();
        this.detailAddr = venderTempEntity.getDetailAddr();
        this.extraAddr = venderTempEntity.getExtraAddr();

    }
}
