package com.dogsole.developersite.jobPost.entity;

import com.dogsole.developersite.account.entity.vender.VenderEntity;
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
@Table(name = "JOB_POST")
public class JobPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_id")
    private Long id;
    @Column(name = "Ip")
    private String ip;
    @Column(name = "job_post_temp_title")
    private String title;
    @Column(name = "job_post_temp_detail")
    private String detail;
    @Column(name = "job_post_temp_work")
    private String work;
    @Column(name = "job_post_temp_req")
    private String req;
    @Column(name = "job_post_temp_salary")
    private String salary;
    @Column(name = "job_post_temp_zipcode")
    private String zipcode;
    @Column(name = "job_post_temp_street_addr")
    private String streetAddr;
    @Column(name = "job_post_temp_detail_addr")
    private String detailAddr;
    @Column(name = "job_post_temp_extra_add")
    private String extraAddr;
    @Column(name = "job_post_temp_postdate")
    private LocalDateTime postDate;
    @Column(name = "job_post_temp_state")
    private String state;
    @Column(name = "job_post_temp_reqstate")
    private String reqState;
    @Column(name = "job_post_temp_endtime")
    private LocalDate endTime;
    @Column(name = "CREATE_DT")
    private LocalDateTime createDt;
    @Column(name = "UPDATE_DT")
    private LocalDateTime updateDt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VENDER_ID")
    private VenderEntity venderEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_post_temp_id")
    private JobPostTempEntity jobPostTempEntity;

}
