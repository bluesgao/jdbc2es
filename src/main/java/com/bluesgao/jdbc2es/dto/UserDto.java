package com.bluesgao.jdbc2es.dto;

import lombok.Data;

import java.util.Date;

/**
 * ClassName: UserDto
 * Desc: TODO
 * Author: gaoxin11
 * Date: 2018/6/13 12:39
 **/
@Data
public class UserDto {
    Long id;
    String userName;
    String nickName;
    Integer userAge;
    Integer userSex;
    Date modifyTime;
}
