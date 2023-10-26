package com.dogsole.developersite.jobPost.dto.res;

import com.dogsole.developersite.vender.dto.req.VenderReqDTO;
import com.dogsole.developersite.vender.dto.res.VenderResDTO;
import com.dogsole.developersite.vender.entity.VenderEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobPostTempResDTO {
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
    private LocalDateTime postDate;
    private String state;
    private String reqState;
    private LocalDate endTime;
    private LocalDateTime createDt = LocalDateTime.now();
    private LocalDateTime updateDt =  LocalDateTime.now();;

    private VenderResDTO venderResDTO;

}
