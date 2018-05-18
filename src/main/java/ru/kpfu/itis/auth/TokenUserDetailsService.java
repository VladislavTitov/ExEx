package ru.kpfu.itis.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repo.UserRepo;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class TokenUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> mayBeUser = userRepo.findByLogin(s);
        User user = mayBeUser.orElseThrow(() -> new UsernameNotFoundException("This user doesn't exist!"));
        TokenUserDetails userDetails = new TokenUserDetails(user.getLogin(),
                user.getPassword(),
                new LinkedList<SimpleGrantedAuthority>(){{ add(new SimpleGrantedAuthority("USER")); }});
        return userDetails;
    }
}
