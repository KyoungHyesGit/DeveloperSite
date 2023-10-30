package com.dogsole.developersite.security.userInfo;

import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PrincipalVenderDetails implements UserDetails, OAuth2User {


    private Long id;
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public PrincipalVenderDetails(VenderEntity venderEntity) {
        name=venderEntity.getVenderEmail();
        password=venderEntity.getVenderPasswd();
        id=venderEntity.getVenderId();
//        authorities= Arrays.stream(userEntity.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
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
