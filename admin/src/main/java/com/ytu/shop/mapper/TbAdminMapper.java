package com.ytu.shop.mapper;

import com.ytu.shop.domain.TbAdmin;
import org.springframework.stereotype.Repository;

@Repository
public interface TbAdminMapper extends BaseMapper<TbAdmin> {

    TbAdmin getByCode(String loginCode);

}