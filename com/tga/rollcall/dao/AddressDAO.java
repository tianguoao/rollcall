package com.tga.rollcall.dao;

import com.tga.rollcall.entity.Address;
import com.tga.rollcall.entity.AddressExample;
import org.springframework.stereotype.Repository;

/**
 * AddressDAO继承基类
 */
@Repository
public interface AddressDAO extends MyBatisBaseDao<Address, Long, AddressExample> {
}