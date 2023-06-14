package com.rkzt.user.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rkzt.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	//通过email查询密码
	String selectOneUserPasswardByUserEmail(@Param("userEmail")String userEmail);
	//通过email查询id
	String selectOneUserIdByUserEmail(@Param("userEmail")String userEmail);
	//添加账号信息
//	int insertSelective(User user);



}