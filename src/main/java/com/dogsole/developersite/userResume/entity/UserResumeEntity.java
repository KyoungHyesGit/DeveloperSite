package com.dogsole.developersite.userResume.entity;

import com.dogsole.developersite.vender.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicUpdate
@ToString
@Table(name="user_resume")
public class UserResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_resume_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userEntity;

    @Column(name = "addr_num")
    private String addr_num;
    @Column(name = "addr_basic")
    private String addr_basic;
    @Column(name = "addr_detail")
    private String addr_detail;

    @Column(name = "skill" ,nullable = true)
    private String skill;

    @Column(name = "final_education")
    private String final_education;
    @Column(name = "state_resume")
    private String state_resume;
    @Column(name = "state_contact")
    private String state_contact;
    @Column(name = "certification",nullable = true)
    private String certification;

    @Column(name = "work_exp",nullable = true)
    private String work_exp;
    @Column(name = "overseas_exp",nullable = true)
    private String overseas_exp;
    @Column(name = "language_skill",nullable = true)
    private String language_skill;
    @Column(name = "portfolio",nullable = true)
    private String portfolio;
    @Column(name = "git_addr",nullable = true)
    private String git_addr;
    @Column(name = "military_service",nullable = true)
    private String military_service;

    @Column(name = "resume_title_1")
    private String resume_title_1;
    @Column(name = "resume_content_1")
    private String resume_content_1;
    @Column(name = "photo",nullable = true)
    private String photo;
    @Column(name = "photoUrl",nullable = true)
    private String photoUrl;

}
