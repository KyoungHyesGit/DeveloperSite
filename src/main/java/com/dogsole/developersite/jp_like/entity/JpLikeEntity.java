package com.dogsole.developersite.jp_like.entity;

import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
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
@Table(name="jp_like")
public class JpLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jp_like_id")
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

    @Column(name = "like_date")
    private LocalDateTime like_date = LocalDateTime.now();;
}