package com.tjpu.crm.workbench.service.impl;

import com.tjpu.crm.utils.SqlSessionUtil;
import com.tjpu.crm.vo.PagenationVO;
import com.tjpu.crm.workbench.dao.ActivityDao;
import com.tjpu.crm.workbench.domain.Activity;
import com.tjpu.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

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
