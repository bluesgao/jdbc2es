package com.bluesgao.jdbc2es.service;

import com.bluesgao.jdbc2es.dto.UserDto;
import com.bluesgao.jdbc2es.model.User;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);

    List<UserDto> getUserByIdRange(Long beginId, Long endId);

    Long getTotalCount();
}
