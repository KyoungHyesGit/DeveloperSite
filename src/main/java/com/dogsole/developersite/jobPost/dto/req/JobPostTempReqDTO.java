package com.dogsole.developersite.jobPost.dto.req;

import com.dogsole.developersite.account.dto.vender.VenderReqDTO;

import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.entity.vender.VenderEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobPostTempReqDTO {
    public JobPostTempReqDTO(Long id, String ip, String title, String detail, String work, String req, String salary, String zipcode, String streetAddr, String detailAddr, String extraAddr, LocalDateTime postDate, String state, String reqState, LocalDateTime endTime, LocalDateTime createDt, LocalDateTime updateDt, VenderReqDTO venderReqDTO) {
        this.id = id;
        this.ip = ip;
        this.title = title;
        this.detail = detail;
        this.work = Arrays.stream(work.split(",")).toList();
        this.req = Arrays.stream(req.split(",")).toList();
        this.salary = salary;
        this.zipcode = zipcode;
        this.streetAddr = streetAddr;
        this.detailAddr = detailAddr;
        this.extraAddr = extraAddr;
        this.postDate = postDate;
        this.state = state;
        this.reqState = reqState;
        this.endTime = endTime;
        this.createDt = createDt;
        this.updateDt = updateDt;
        this.venderReqDTO = venderReqDTO;
    }

    private Long id;
    private String ip;
    @NotBlank(message = "제목은 필수")
    private String title;
    @NotBlank(message = "세부사항 필수")
    private String detail;
    private List<String> work;
    private List<String> req;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "공고일은 필수")
    private LocalDateTime endTime;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

    private VenderReqDTO venderReqDTO;
}
