//package com.travelblog.travelblogspring.model.detail;
//
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.travelblog.travelblogspring.model.User;
//import com.travelblog.travelblogspring.repository.UserRepository;
//
//public class UserDetail implements UserDetailsService{
//	
//	@Autowired    
//	UserRepository userRepository;
//	
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//          if(userOptional.isEmpty()){
//                 new UsernameNotFoundException("User not exists by Username");
//               }
//          
//        User user = userOptional.get();
//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getRole().toString()))
//                .collect(Collectors.toSet());
//        return new org.springframework.security.core.userdetails.User(email,user.getPassword(),authorities);
//    }
//}
