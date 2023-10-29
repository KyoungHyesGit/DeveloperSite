package com.dogsole.developersite.jp_apply.entity;

import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
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
@Table(name="jp_apply")
public class JpApplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jp_apply_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VENDER_ID")
    private VenderEntity venderEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_ID")
    private UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_post_id")
    private JobPostEntity jobPostEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_resume_id")
    private UserResumeEntity userResumeEntity;
    @Column(name = "apply_date")
    private LocalDateTime apply_date = LocalDateTime.now();;
}