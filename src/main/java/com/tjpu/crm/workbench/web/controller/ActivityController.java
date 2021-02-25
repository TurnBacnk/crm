package com.tjpu.crm.workbench.web.controller;

import com.tjpu.crm.settings.domain.User;
import com.tjpu.crm.settings.service.UserService;
import com.tjpu.crm.settings.service.impl.UserServiceImpl;
import com.tjpu.crm.utils.*;
import com.tjpu.crm.vo.PagenationVO;
import com.tjpu.crm.workbench.domain.Activity;
import com.tjpu.crm.workbench.service.ActivityService;
import com.tjpu.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");

        String path = request.getServletPath();

        if ("/workbench/activity/getUserList.do".equals(path)){

            getUserList(request,response);


        }else if ("/workbench/activity/save.do".equals(path)){

            save(request,response);

        }else if ("/workbench/activity/pageList.do".equals(path)){

            pageList(request,response);

        }else if ("/workbench/activity/delete.do".equals(path)){

            delete(request,response);

        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行市场活动删除操作");

        String ids[] = request.getParameterValues("id");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.delete(ids);

        PrintJson.printJsonFlag(response,flag);



    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询市场活动信息列表的操作（结合条件查询+分页查询）");

        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);

        int skipCount = (pageNo-1)*pageSize;

        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        PagenationVO<Activity> vo = as.pageList(map);

        PrintJson.printJsonObj(response,vo);


    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行市场活动添加操作");

        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);


        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.save(a);

        PrintJson.printJsonFlag(response,flag);

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("取得用户信息列表");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = us.getUserList();

        PrintJson.printJsonObj(response,uList);

    }
}
