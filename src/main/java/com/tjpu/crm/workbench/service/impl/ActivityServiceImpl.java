package com.tjpu.crm.workbench.service.impl;

import com.tjpu.crm.settings.dao.UserDao;
import com.tjpu.crm.settings.domain.User;
import com.tjpu.crm.utils.SqlSessionUtil;
import com.tjpu.crm.vo.PagenationVO;
import com.tjpu.crm.workbench.dao.ActivityDao;
import com.tjpu.crm.workbench.dao.ActivityRemarkDao;
import com.tjpu.crm.workbench.domain.Activity;
import com.tjpu.crm.workbench.domain.ActivityRemark;
import com.tjpu.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public boolean save(Activity a) {

        boolean flag = true;

        int count = activityDao.save(a);

        if (count!=1){
            flag = false;
        }

        return flag;
    }

    @Override
    public boolean delete(String[] ids) {

        boolean flag =true;

        int count1 = activityRemarkDao.getCountByAids(ids);

        int count2 = activityRemarkDao.deleteByAids(ids);

        if (count1!=count2){
            flag=false;
        }
        int count3 = activityDao.delete(ids);
        if (count3!= ids.length){

            flag = false;

        }

        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {

        List<User> uList = userDao.getUserList();

        Activity a =  activityDao.getById(id);

        Map<String,Object> map = new HashMap<>();
        map.put("uList",uList);
        map.put("a",a);
        return map;
    }

    @Override
    public boolean update(Activity a) {

        boolean flag = true;

        int count = activityDao.update(a);

        if (count!=1){
            flag=false;
        }

        return flag;
    }

    @Override
    public Activity detail(String id) {

        Activity a = activityDao.detail(id);

        return a;
    }

    @Override
    public List<ActivityRemark> getRemarkListByAid(String activityId) {

        List<ActivityRemark> arList = activityRemarkDao.getRemarkListByAid(activityId);

        return arList;
    }

    @Override
    public boolean deleteRemark(String id) {
        boolean flag = true;

        int count = activityRemarkDao.deleteRemark(id);

        if (count!=1){
            flag =false;
        }

        return flag;
    }

    @Override
    public boolean saveRemark(ActivityRemark ar) {

        boolean flag = true;

        int count = activityRemarkDao.saveRemark(ar);

        if (count!=1){
            flag = false;
        }

        return flag;
    }

    @Override
    public boolean updateRemark(ActivityRemark ar) {

        boolean flag = true;
        int count = activityRemarkDao.updateRemark(ar);

        if (count!=1){
            flag = false;
        }


        return flag;
    }

    @Override
    public PagenationVO<Activity> pageList(Map<String, Object> map) {

        //取得total
        int total = activityDao.getTotalByCondition(map);
        //取得datalist
        List<Activity> dataList = activityDao.getActivityListByCondition(map);
        //将total和datalist封装到vo中
        PagenationVO<Activity> vo = new PagenationVO<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        return vo;
    }
}
