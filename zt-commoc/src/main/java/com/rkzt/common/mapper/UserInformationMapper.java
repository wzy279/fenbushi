package com.rkzt.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rkzt.common.domain.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserInformationMapper extends BaseMapper<UserInformation> {
}