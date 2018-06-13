package com.bluesgao.jdbc2es.dao;

import com.bluesgao.jdbc2es.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: UserDao
 * Desc: TODO
 * Author: gaoxin11
 * Date: 2018/6/13 11:24
 **/
public interface UserDao {
    User getUserById(Long id);

    List<User> getUserByIdRange(@Param("beginId") Long beginId, @Param("endId") Long endId);

    Long getTotalCount();
}
