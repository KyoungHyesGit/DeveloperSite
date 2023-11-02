package com.dogsole.developersite.security.userInfo;

import com.dogsole.developersite.account.entity.user.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PrincipalDetails implements UserDetails, OAuth2User {


    private Long id;
    private String name;
    private Long venderId;
    private String password;

    private String state;

    private List<GrantedAuthority> authorities;

    public PrincipalDetails(UserEntity userEntity) {
        name=userEntity.getUserEmail();
        password=userEntity.getPasswd();
        id=userEntity.getUserId();
        venderId=userEntity.getVenderId();
        state=userEntity.getState();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
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

    @Override
    public String getName() {
        return name;
    }
}
