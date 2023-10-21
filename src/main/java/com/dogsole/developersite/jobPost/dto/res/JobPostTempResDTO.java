package com.dogsole.developersite.jobPost.dto.res;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JobPostTempResDTO {
    private Long id;
    private Long vender_id;
    private String ip;
    private String title;
    private String content;
    private LocalDate endDt;

    private LocalDateTime createDt;
    private LocalDateTime updateDt;

}
