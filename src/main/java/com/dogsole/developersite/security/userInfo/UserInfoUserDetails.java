package com.dogsole.developersite.security.userInfo;

import com.dogsole.developersite.account.entity.user.UserEntity;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {


    private Long id;
    private Long venderId;
    private String name;
    private String password;
    private String state;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(UserEntity userEntity) {
        name=userEntity.getUserEmail();
        password=userEntity.getPasswd();
        id=userEntity.getUserId();
        venderId=userEntity.getVenderId();
        state=userEntity.getState();
//        authorities= Arrays.stream(userEntity.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public Long getUserId() {
        return id;
    }
    public Long getVenderId() {
        return venderId;
    }

    public String getUserState(){return state;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
