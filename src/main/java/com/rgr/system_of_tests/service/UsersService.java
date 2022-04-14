package com.rgr.system_of_tests.service;

import com.rgr.system_of_tests.repo.models.Role;
import com.rgr.system_of_tests.repo.models.User;
import com.rgr.system_of_tests.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MailSender mailSender;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username);
    }
    public boolean addUser(User user){
        User userfromDB =usersRepository.findByUsername(user.getUsername());

        if(userfromDB!=null){
            return false;
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(false);
        user.setActivationCode(UUID.randomUUID().toString());
        usersRepository.save(user);
        String message = String.format(
                "Доброго времени суток,%s \n"+
                        "Пожалуйста, пройдите по ссылке для активации аккаунта: http://localhost:8080/activate/%s",
                user.getFirstname(),
                user.getActivationCode()
        );
        mailSender.send(user.getUsername(),"Код активации",message);
        return true;
    }

    public boolean activateUser(String code) {
        User user = usersRepository.findByActivationCode(code);
        if (user==null){
            return false;
        }
        user.setActive(true);
        user.setActivationCode(null);
        usersRepository.save(user);
        return true;
    }
}
