package com.dogsole.developersite.adviceBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdviceBoardReqDTO {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private String kind;
    private LocalDateTime regDate;
}


