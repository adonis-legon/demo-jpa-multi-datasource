package com.example.demojpamultidatasource.business;

import com.example.demojpamultidatasource.dao.UserDao;
import com.example.demojpamultidatasource.dao.UserMembershipType;
import com.example.demojpamultidatasource.dto.UserDto;
import com.example.demojpamultidatasource.model.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public UserDto createUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDao.add(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    public UserDto getUser(String userEmail, String membership) {
        User storedUser = userDao.find(userEmail,
                membership.equalsIgnoreCase("basic") ? UserMembershipType.BASIC.ordinal()
                        : UserMembershipType.PREMIUM.ordinal());

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(storedUser, UserDto.class);
    }
}
