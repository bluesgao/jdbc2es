package com.bluesgao.jdbc2es.service.impl;

import com.bluesgao.jdbc2es.dao.UserDao;
import com.bluesgao.jdbc2es.dto.UserDto;
import com.bluesgao.jdbc2es.model.User;
import com.bluesgao.jdbc2es.service.UserService;
import com.bluesgao.jdbc2es.util.BeanMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Desc: TODO
 * Author: gaoxin11
 * Date: 2018/6/13 12:40
 **/
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDto getUserById(Long id) {
        LOGGER.info("getUserById start: input:{}", id);
        UserDto userDto = new UserDto();
        User user = userDao.getUserById(id);
        if (user != null) {
            BeanMapperUtils.copyProperties(user, userDto);
        }
        LOGGER.info("getUserById end: input:{};output:{}", id, userDto);
        return userDto;
    }

    @Override
    public List<UserDto> getUserByIdRange(Long beginId, Long endId) {
        LOGGER.info("getUserByIdRange start: input:{},{}", beginId, endId);
        List<User> userList = userDao.getUserByIdRange(beginId, endId);

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = new UserDto();
            BeanMapperUtils.copyProperties(user, userDto);
            userDtoList.add(userDto);
        }
        LOGGER.info("getUserByIdRange end: input:{},{};output:{}", beginId, endId, userDtoList);
        return CollectionUtils.isEmpty(userDtoList) ? null : userDtoList;
    }

    @Override
    public Long getTotalCount() {
        LOGGER.info("getTotalCount start");
        Long count = userDao.getTotalCount();
        LOGGER.info("getTotalCount end: output:{}", count);
        return count;
    }
}
