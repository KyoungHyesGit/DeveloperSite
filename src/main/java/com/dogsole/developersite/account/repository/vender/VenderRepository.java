package com.dogsole.developersite.account.repository.vender;

import com.dogsole.developersite.account.entity.vender.VenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VenderRepository extends JpaRepository<VenderEntity, Long> {

    //이메일로 회사 객체 조회
    public Optional<VenderEntity> findByVenderEmail(String vender_email);

    //중복 회원 방지
    public boolean existsByVenderEmail(String vender_eamil);

    //해당 회사 회원 객체 띄우기
    public String venderEmail(String vender_eamil);

    //비밀번호 확인 메서드(로그인)
    public boolean existsByVenderPasswd(String vender_passwd);

    public VenderEntity findByTempId(Long tempId);

}
