package com.tjpu.crm.settings.service;

import com.tjpu.crm.exception.LoginException;
import com.tjpu.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
