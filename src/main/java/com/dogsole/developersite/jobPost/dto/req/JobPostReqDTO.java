package com.dogsole.developersite.jobPost.dto.req;


import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime endTime;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

    private VenderReqDTO venderReqDTO;

    private JobPostTempReqDTO jobPostTempReqDTO;

}
