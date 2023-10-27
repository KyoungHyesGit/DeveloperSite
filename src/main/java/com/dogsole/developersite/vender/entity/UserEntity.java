package com.dogsole.developersite.vender.entity;

import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;


@Entity
@Setter
@Getter
@ToString
@DynamicUpdate
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserResumeEntity> userResumes;
}
