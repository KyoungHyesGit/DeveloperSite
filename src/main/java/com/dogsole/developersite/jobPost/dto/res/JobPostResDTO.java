package com.dogsole.developersite.jobPost.dto.res;


import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobPostResDTO {
    private Long id;
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
    private LocalDate postDate;
    private String state;
    private String reqState;
    private LocalDateTime endTime;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;


    private VenderResDTO venderResDTO;

    private JobPostTempResDTO jobPostTempReqDTO;
}