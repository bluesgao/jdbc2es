package com.bluesgao.jdbc2es.model;

import lombok.Data;

import java.util.Date;

/**
 * ClassName: User
 * Desc: TODO
 * Author: gaoxin11
 * Date: 2018/6/13 11:19
 **/
@Data
public class User {
    Long id;
    String userName;
    String nickName;
    Integer userAge;
    Integer userSex;
    Date modifyTime;
}
