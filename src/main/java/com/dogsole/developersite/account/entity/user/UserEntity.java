package com.dogsole.developersite.account.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name="user")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name="user_name")
    private String userName;

    @Column(name="passwd")
    private String passwd;

    @Column(name="phone")
    private String phone;

    @Column(name="birth")
    private String birth;

    @Column(name="privacy_state")
    private String privacyState;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt =LocalDateTime.now();

    @Column(name="state")
    private String state;

    @Column(name = "photo")
    private String photo;

    @Column(name="photourl")
    private String photoUrl;
}
