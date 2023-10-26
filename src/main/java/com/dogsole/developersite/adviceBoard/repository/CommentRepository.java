package com.dogsole.developersite.adviceBoard.repository;

import com.dogsole.developersite.adviceBoard.entity.AdviceBoard;
import com.dogsole.developersite.adviceBoard.entity.Comment;
//import com.dogsole.developersite.adviceBoard.entity.UserEntity;
//import com.dogsole.developersite.adviceBoard.entity.UserEntity;
//import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByContentContaining(String searchKeyword, Pageable pageable);

//    void deleteBySeq(Long seq);

//    UserEntity findUserByUserId(Long id);


}