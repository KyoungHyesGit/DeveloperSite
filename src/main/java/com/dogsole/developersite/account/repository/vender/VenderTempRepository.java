package com.dogsole.developersite.account.repository.vender;

import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.account.entity.vender.VenderTempEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenderTempRepository extends JpaRepository<VenderTempEntity, Long> {

    public Optional<VenderTempEntity> findByName(String vender_email);

    //중복 회원 방지
    public boolean existsByName(String name);


}
