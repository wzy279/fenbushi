package com.rkzt.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rkzt.common.domain.UserInformation;
import com.rkzt.common.mapper.UserInformationMapper;
import com.rkzt.common.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

@Service
@Component
public class UserInformationServiceImpl implements UserInformationService{

    @Autowired
    UserInformationMapper userInformationMapper;
    @Override
    public UserInformation getbyid(String id) {
        return userInformationMapper.selectById(id);
    }

    @Override
    public List<UserInformation> getAll() {
        QueryWrapper<UserInformation> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("user_email","09");//模糊查询
        queryWrapper.getSqlSelect();
        return userInformationMapper.selectList(queryWrapper);
    }
}
