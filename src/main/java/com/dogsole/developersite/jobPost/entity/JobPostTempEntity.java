package com.dogsole.developersite.jobPost.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@DynamicUpdate
@Table(name = "JOB_POST_TEMP")
public class JobPostTempEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "VENDER_IP")
    private Long vender_id;
    @Column(name = "IP")
    private String ip;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "END_DT")
    private LocalDate endDt;

    @Column(name = "CREATE_DT")
    private LocalDateTime createDt;
    @Column(name = "UPDATE_DT")
    private LocalDateTime updateDt;

}
