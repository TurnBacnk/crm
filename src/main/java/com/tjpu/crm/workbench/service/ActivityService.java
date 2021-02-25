package com.tjpu.crm.workbench.service;

import com.tjpu.crm.vo.PagenationVO;
import com.tjpu.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity a);

    PagenationVO<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);
}
