package com.bestfurnituredeals.assignment3.service.security;

import com.bestfurnituredeals.assignment3.model.security.UserPrincipal;
import com.bestfurnituredeals.assignment3.service.query.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserQueryService userQueryService;

    @Autowired
    public UserDetailsServiceImpl(@Lazy UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new UserPrincipal(userQueryService.getUserByUsername(s));
    }
}
