package com.rgr.system_of_tests.service;

import com.rgr.system_of_tests.repo.models.Role;
import com.rgr.system_of_tests.repo.models.User;
import com.rgr.system_of_tests.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MailSender mailSender;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
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
    public void deleteUser(long id) {
        User user = usersRepository.findId(id);
        usersRepository.delete(user);
    }

    public Iterable<User> getAllUsers() {
        Iterable<User> users = usersRepository.findAll();
        return users;
    }

    public User getUserById(long id) {
        User user = usersRepository.findId(id);
        return user;
    }
    public void editUserRoles(Map<String, String> form,long id){
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        User user = usersRepository.findId(id);
        user.getRoles().clear();

        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        usersRepository.save(user);
    }
}
