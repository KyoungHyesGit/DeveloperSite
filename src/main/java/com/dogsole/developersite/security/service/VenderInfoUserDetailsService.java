package com.dogsole.developersite.security.service;

import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.account.repository.user.UserRepository;
import com.dogsole.developersite.account.repository.vender.VenderRepository;
import com.dogsole.developersite.security.userInfo.UserInfoUserDetails;
import com.dogsole.developersite.security.userInfo.VenderInfoUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VenderInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private VenderRepository repository;

    @Override
    public UserDetails loadUserByUsername(String venderEmail) throws UsernameNotFoundException {
        Optional<VenderEntity> venderInfo = repository.findByVenderEmail(venderEmail);
        return venderInfo.map(VenderInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + venderEmail));

    }
}
