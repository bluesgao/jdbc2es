package com.bluesgao.jdbc2es.service.impl;

import com.bluesgao.jdbc2es.Jdbc2esApplication;
import com.bluesgao.jdbc2es.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Jdbc2esApplication.class})
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    public void getUserById() {
        userService.getUserById(null);
    }

    @Test
    public void getUserByIdRange() {
        userService.getUserByIdRange(1L, 200L);
    }

    @Test
    public void getTotalCount() {
        userService.getTotalCount();
    }
}