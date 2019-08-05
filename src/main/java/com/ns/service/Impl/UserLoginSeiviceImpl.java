package com.ns.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ns.dao.IUserLoginDao;
import com.ns.entity.User;
import com.ns.service.IUserLoginSeivice;

@Service
public class UserLoginSeiviceImpl implements IUserLoginSeivice{

	@Autowired
	private IUserLoginDao userLoginDao;

	@Override
	public User ChecLoginkUser(User user) {
		return userLoginDao.ChecLoginkUser(user);
	}
	
	
}
