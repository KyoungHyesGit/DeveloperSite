package com.dogsole.developersite.account.repository.user;

import com.dogsole.developersite.account.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //id로 객체조회 테스트용
    public Optional<UserEntity> findByUserId(Long user_id);
    //이메일로 객체조회
    public Optional<UserEntity> findByUserEmail(String user_email);

    //중복처리 및 로그인을 위함
    public boolean existsByUserEmail(String user_email);

    //해당 객체 이메일 띄우기
    public String userEmail(String user_email);

    //비밀번호 확인 메서드(로그인)
    public boolean existsByPasswd(String user_passwd);

    @Query("SELECT DATE_FORMAT(u.createdAt, '%Y-%m-%d') AS LABEL, COUNT(*) AS Y FROM user u WHERE u.createdAt IS NOT NULL GROUP BY LABEL")
    List<Object[]> countUsersByCreatedAt();

    @Query("SELECT SUBSTRING(u.birth, 1, 4) AS LABEL, COUNT(*) AS Y FROM user u WHERE u.birth IS NOT NULL GROUP BY LABEL")
    List<Object[]> countUsersByBirthYear();
}
