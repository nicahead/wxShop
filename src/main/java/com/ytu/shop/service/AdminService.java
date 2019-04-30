package com.ytu.shop.service;

import com.ytu.shop.domain.TbAdmin;

public interface AdminService extends BaseService<TbAdmin> {
    TbAdmin getByCode(String loginCode);
}
