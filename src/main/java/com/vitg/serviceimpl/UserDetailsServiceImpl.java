package com.vitg.serviceimpl;


import java.util.List;
import java.util.stream.Collectors;

import com.vitg.dto.UserPrinciple;
import com.vitg.entity.User;
import com.vitg.repository.RoleRepository;
import com.vitg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        User user = userRepository.findByPhoneNumber(phoneNumber);

        if(user==null) {
            throw new UsernameNotFoundException("User Not Found with ->email : " + phoneNumber);
        }

        return new UserPrinciple(
                user.getId(), user.getPhoneNumber(), new BCryptPasswordEncoder().encode(user.getPassword())
        );

//        if(getAuthorities(user)==null ||getAuthorities(user).isEmpty() ||getAuthorities(user).size()==0) {
//
//            List<GrantedAuthority> authorities=assignRole(user);
//
//            return new UserPrinciple(
//                    user.getId(), user.getPhoneNumber(),
//                    authorities
//            );
//
//        }else {
//            List<GrantedAuthority> authorities=getAuthorities(user);
//
//            return new UserPrinciple(
//                    user.getId(), user.getPhoneNumber(),
//                    authorities
//            );
//
//        }
    }

//    public List<GrantedAuthority> getAuthorities(User user){
//
//        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
//                new SimpleGrantedAuthority(role.getRoleName())
//        ).collect(Collectors.toList());
//
//
//        return authorities;
//    }

//    public List<GrantedAuthority> assignRole(User user) {
//
//
//        Role role=roleRepository.findByroleName("VIEWER");
//
//        user.addRole(role);
//
//        userRepository.save(user);
//
//        User updatedUser=userRepository.findByEmailId(user.getEmailId());
//
//
//        return getAuthorities(updatedUser);
//    }

}
