package com.tjpu.crm.settings.service;

import com.tjpu.crm.exception.LoginException;
import com.tjpu.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
