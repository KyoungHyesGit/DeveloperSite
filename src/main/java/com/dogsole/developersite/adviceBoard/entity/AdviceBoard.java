package com.dogsole.developersite.adviceBoard.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@DynamicUpdate
@Table(name = "adviceboard")
public class AdviceBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "제목을 입력해주세요")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "작성자 입력해주세요")
    @Column(nullable = false)
    private String writer;

    @NotBlank(message = "내용을 입력해주세요")
    @Column(nullable = false)
    private String content;

//    @Column(nullable = true)
//    private String kind;

    @Column
    @CreationTimestamp
    private LocalDateTime regDate = LocalDateTime.now();

    @OneToMany(mappedBy = "adviceboards", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Comment> comments;

    private int commentCount; // 댓글수

    private int views;//조회수
    public void incrementViews() {
        this.views++;
    }
}
