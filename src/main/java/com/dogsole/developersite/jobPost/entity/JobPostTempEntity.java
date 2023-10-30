package com.dogsole.developersite.jobPost.entity;

import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqDTO;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqFormDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@ToString
@DynamicUpdate
@Table(name = "JOB_POST_TEMP")
public class JobPostTempEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_temp_id")
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
    private LocalDateTime endTime;
    @Column(name = "CREATE_DT")
    private LocalDateTime createDt = LocalDateTime.now();
    @Column(name = "UPDATE_DT")
    private LocalDateTime updateDt =  LocalDateTime.now();;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VENDER_ID")
    private VenderEntity venderEntity;

    public void setDTOsValToEntity(JobPostTempReqFormDTO jobPostTempReqDTO){
        this.title = jobPostTempReqDTO.getTitle();
        this.detail = jobPostTempReqDTO.getDetail();
        this.reqState = jobPostTempReqDTO.getReqState();
        this.state = jobPostTempReqDTO.getState();
        this.work = jobPostTempReqDTO.getWork().stream().collect(Collectors.joining(","));
        this.req = jobPostTempReqDTO.getReq().stream().collect(Collectors.joining(","));
        this.salary = jobPostTempReqDTO.getSalary();
        this.zipcode = jobPostTempReqDTO.getZipcode();
        this.streetAddr = jobPostTempReqDTO.getStreetAddr();
        this.detailAddr = jobPostTempReqDTO.getDetailAddr();
        this.extraAddr = jobPostTempReqDTO.getExtraAddr();
        this.endTime = jobPostTempReqDTO.getEndTime();
    }

    public void setDTOsValToEntity(JobPostTempReqDTO jobPostTempReqDTO){
        this.title = jobPostTempReqDTO.getTitle();
        this.detail = jobPostTempReqDTO.getDetail();
        this.reqState = jobPostTempReqDTO.getReqState();
        this.state = jobPostTempReqDTO.getState();
        this.work = jobPostTempReqDTO.getWork().stream().collect(Collectors.joining(","));
        this.req = jobPostTempReqDTO.getReq().stream().collect(Collectors.joining(","));
        this.salary = jobPostTempReqDTO.getSalary();
        this.zipcode = jobPostTempReqDTO.getZipcode();
        this.streetAddr = jobPostTempReqDTO.getStreetAddr();
        this.detailAddr = jobPostTempReqDTO.getDetailAddr();
        this.extraAddr = jobPostTempReqDTO.getExtraAddr();
        this.endTime = jobPostTempReqDTO.getEndTime();
    }
}
