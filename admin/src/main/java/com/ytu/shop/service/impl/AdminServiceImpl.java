package com.ytu.shop.service.impl;

import com.ytu.shop.domain.TbAdmin;
import com.ytu.shop.mapper.TbAdminMapper;
import com.ytu.shop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbAdmin, TbAdminMapper> implements AdminService {
    @Autowired
    private TbAdminMapper tbAdminMapper;

    public TbAdmin getByCode(String loginCode){
        return tbAdminMapper.getByCode(loginCode);
    }
}
