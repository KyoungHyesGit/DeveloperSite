package com.dogsole.developersite.adviceBoard.service;

import com.dogsole.developersite.adviceBoard.dto.CommentReqDTO;
import com.dogsole.developersite.adviceBoard.dto.CommentResDTO;
import com.dogsole.developersite.adviceBoard.entity.Comment;
//import com.dogsole.developersite.adviceBoard.entity.UserEntity;
import com.dogsole.developersite.adviceBoard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

//    //댓글 작성 처리
//    public void write(CommentReqDTO commentReqDTO) {
//        Comment comment = modelMapper.map(commentReqDTO, Comment.class);
//        commentRepository.save(comment);
//    }
//
//    //댓글 리스트 처리
//    public Page<Comment> list(Pageable pageable) {
//        return commentRepository.findAll(pageable);
//    }
//
//
//    public Page<Comment> searchList(String searchKeyword, Pageable pageable) {
//        return commentRepository.findByContentContaining(searchKeyword, pageable);
//    }
//
//    public void deleteBySeq(Long seq) {
//        commentRepository.deleteBySeq(seq);
//    }
//
//    public CommentResDTO update(Long seq, CommentReqDTO commentReqDTO) {
//        Comment comment = commentRepository.findBySeq(seq)
//                .orElseThrow(() -> new RuntimeException("없는 seq입니다."));
//
//        comment.setContent(commentReqDTO.getContent());
//
//        commentRepository.save(comment);
//
//        return modelMapper.map(comment, CommentResDTO.class);
//    }
//------------------------------------------------------------------------------------------------------------
//    public List<CommentResDTO> getAllComments() {
//        List<Comment> comments = commentRepository.findAll();
//        return comments.stream()
//                .map(comment -> modelMapper.map(comment, CommentResDTO.class))
//                .collect(Collectors.toList());
//    }

    public CommentResDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        return modelMapper.map(comment, CommentResDTO.class);
    }

    public CommentResDTO addComment(Comment commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setCreatedDate(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentResDTO.class);
    }

    public CommentResDTO updateComment(Comment comment) {
        Comment existingComment = commentRepository.findById(comment.getId())
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + comment.getId()));
        existingComment.setWriter(comment.getWriter());
        existingComment.setContent(comment.getContent());
        existingComment.setUpdatedDate(LocalDateTime.now());
        Comment updatedComment = commentRepository.save(existingComment);
        return modelMapper.map(updatedComment, CommentResDTO.class);
    }

    public List<CommentResDTO> getComment(Long id) {
        Optional<Comment> comments = commentRepository.findById(id);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentResDTO.class))
                .collect(Collectors.toList());
    }

//     AdviceId로 댓글 가져오기
    public List<CommentResDTO> getCommendByUserId(Long userId) {
        List<CommentResDTO> list = new ArrayList<>();
//        UserEntity user = commentRepository.findUserByUserId(userId);
//        return user.stream()
//                .map(comments -> modelMapper.map(user, CommentResDTO.class))
//                .collect(Collectors.toList());
        return list;
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


}