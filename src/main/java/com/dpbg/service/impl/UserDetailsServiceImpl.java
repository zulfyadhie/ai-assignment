package com.dpbg.service.impl;

import com.dpbg.entity.Role;
import com.dpbg.entity.User;
import com.dpbg.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zulfy on 9/12/16.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class, timeout = 30)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Empty username");
        }

        User appUser = userDao.findOneByUsername(username);
        if(appUser == null || appUser.getActive() != true){
            throw new UsernameNotFoundException("User not found or inactive");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Role role : appUser.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getCode());
            grantedAuthorities.add(grantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(username, appUser.getPassword(), grantedAuthorities);
    }

}