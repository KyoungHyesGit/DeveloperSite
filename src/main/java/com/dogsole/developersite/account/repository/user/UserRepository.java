package com.dogsole.developersite.account.repository.user;

import com.dogsole.developersite.account.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //이메일로 객체조회
    public Optional<UserEntity> findByUserEmail(String user_email);

    //중복처리를 위함
    public boolean existsByUserEmail(String user_email);

    //해당 객체 이메일 띄우기
    public String userEmail(String email);
}
