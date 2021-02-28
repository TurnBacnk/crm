package com.tjpu.crm.workbench.service;

import com.tjpu.crm.vo.PagenationVO;
import com.tjpu.crm.workbench.domain.Activity;
import com.tjpu.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    boolean save(Activity a);

    PagenationVO<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);


    Map<String, Object> getUserListAndActivity(String id);

    boolean update(Activity a );

    Activity detail(String id);

    List<ActivityRemark> getRemarkListByAid(String activityId);

    boolean deleteRemark(String id);

    boolean saveRemark(ActivityRemark ar);

    boolean updateRemark(ActivityRemark ar);
}
