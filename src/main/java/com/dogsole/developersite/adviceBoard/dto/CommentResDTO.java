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
public class CommentResDTO {

    private Long id;
    private String writer;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long adviceboardId;
}