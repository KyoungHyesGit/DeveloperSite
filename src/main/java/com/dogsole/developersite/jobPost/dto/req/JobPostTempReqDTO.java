package com.dogsole.developersite.jobPost.dto.req;

import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.vender.dto.req.VenderReqDTO;
import com.dogsole.developersite.vender.dto.res.VenderResDTO;
import com.dogsole.developersite.vender.entity.VenderEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobPostTempReqDTO {
    private String ip;
    @NotBlank(message = "제목은 필수")
    private String title;
    @NotBlank(message = "세부사항 필수")
    private String detail;
    private String work;
    private String req;
    private String salary;
    @NotBlank(message = "우편번호 필수")
    private String zipcode;
    @NotBlank(message = "주소 필수")
    private String streetAddr;
    private String detailAddr;
    @NotBlank(message = "주소 필수")
    private String extraAddr;
    private LocalDateTime postDate;
    private String state;
    private String reqState;
    @NotNull(message = "공고일은 필수")
    private LocalDate endTime;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

    private VenderReqDTO venderReqDTO;
}
