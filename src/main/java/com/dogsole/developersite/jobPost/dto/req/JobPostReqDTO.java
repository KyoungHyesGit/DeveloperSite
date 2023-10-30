package com.dogsole.developersite.jobPost.dto.req;

import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobPostReqDTO {
    private String ip;
    private String title;
    private String detail;
    private String work;
    private String req;
    private String salary;
    private String zipcode;
    private String streetAddr;
    private String detailAddr;
    private String extraAddr;
    private LocalDateTime postDate;
    private String state;
    private String reqState;
    private LocalDate endTime;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

    private VenderReqDTO venderReqDTO;

    private JobPostTempReqDTO jobPostTempReqDTO;

}
