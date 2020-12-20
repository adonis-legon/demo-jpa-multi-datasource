package com.example.demojpamultidatasource.dao;

import com.example.demojpamultidatasource.exception.UserNotFoundException;
import com.example.demojpamultidatasource.model.User;
import com.example.demojpamultidatasource.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    @Autowired
    UserRepository userRepository;

    private void setUserDbContext(int userMembership) {
        switch (userMembership) {
            case 0:
                UserDbContext.setCurrentDb(UserMembershipType.BASIC);
                break;
            case 1:
                UserDbContext.setCurrentDb(UserMembershipType.PREMIUM);
                break;
            default:
                UserDbContext.setCurrentDb(UserMembershipType.BASIC);
                break;
        }
    }

    public User add(User user) {
        setUserDbContext(user.getMembershipType());
        return userRepository.save(user);
    }

    public User find(String userEmail, int userMembership) {
        setUserDbContext(userMembership);
        return userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));
    }
}
