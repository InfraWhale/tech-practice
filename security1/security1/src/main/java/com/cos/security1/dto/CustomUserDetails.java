package com.cos.security1.dto;

import com.cos.security1.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }
    
    // 사용자의 특정한 권한에 대해 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //// 아직 db에 등록을 안해줬다.

    // 사용자 아이디 만료되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
        //return UserDetails.super.isAccountNonExpired();
    }
    // 사용자 아이디 잠겼는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
        // return UserDetails.super.isAccountNonLocked();
    }

    // 자격증명
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //return UserDetails.super.isCredentialsNonExpired();
    }

    // 사용가능한지
    @Override
    public boolean isEnabled() {
        return true;
        //return UserDetails.super.isEnabled();
    }
}
