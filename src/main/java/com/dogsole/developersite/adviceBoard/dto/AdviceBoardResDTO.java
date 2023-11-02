package com.dogsole.developersite.adviceBoard.dto;

import com.dogsole.developersite.adviceBoard.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdviceBoardResDTO {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime regDate;
    private List<Comment> comments;

    private int commentCount; // 댓글수

    private int views;//조회수
    public void incrementViews() {
        this.views++;
    }

    private String category;
}


