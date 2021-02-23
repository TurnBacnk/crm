package com.tjpu.crm.settings.service.impl;

import com.tjpu.crm.settings.dao.UserDao;
import com.tjpu.crm.settings.service.UserService;
import com.tjpu.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
}
