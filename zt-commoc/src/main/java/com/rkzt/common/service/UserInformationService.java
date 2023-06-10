package com.rkzt.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rkzt.common.domain.UserInformation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserInformationService{

    UserInformation getbyid(String id);
    List<UserInformation> getAll();
}
