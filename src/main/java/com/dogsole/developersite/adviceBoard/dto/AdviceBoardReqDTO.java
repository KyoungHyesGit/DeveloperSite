package com.dogsole.developersite.adviceBoard.dto;

import com.dogsole.developersite.adviceBoard.entity.Comment;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdviceBoardReqDTO {

    private Long id;

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "작성자를 입력해주세요")
    private String writer;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    private String kind;

    private LocalDateTime regDate;
    private List<Comment> comments;

    private int commentCount; // 댓글수

    private int views;//조회수
    public void incrementViews() {
        this.views++;
    }

    private String category;

}



