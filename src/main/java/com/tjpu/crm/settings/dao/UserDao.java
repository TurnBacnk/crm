package com.tjpu.crm.settings.dao;

import com.tjpu.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {
    User login(Map<String, String> map);
}
