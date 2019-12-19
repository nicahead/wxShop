package com.ytu.shop.service.impl;

import com.ytu.shop.domain.TbUser;
import com.ytu.shop.mapper.TbUserMapper;
import com.ytu.shop.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<TbUser, TbUserMapper> implements UserService {

}
