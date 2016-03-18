package com.ithejas.service;

import java.util.Arrays;
import java.util.List;
 
import com.ithejas.model.UserModel;
import com.ithejas.repository.UserRepositoryImpl;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
@Service
public class LoginService implements UserDetailsService
{
    private UserRepositoryImpl repository;
 
    @Autowired
    public LoginService( UserRepositoryImpl repository )
    {
        this.repository = repository;
    }
 
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
    {
        UserModel user = repository.findUserByUsername( username );
     //  System.out.println("UserModel "+user.toString());
        if( user == null )
            throw new UsernameNotFoundException( "Oops!" );
 
        List<SimpleGrantedAuthority> authorities = Arrays.asList( new SimpleGrantedAuthority( user.getRole() ) );
        
         
        
        return new User( user.getUsername(), user.getSHA1Password(), authorities );
    }
}
