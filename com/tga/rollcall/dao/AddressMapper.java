package com.tga.rollcall.dao;

import com.tga.rollcall.entity.Address;
import com.tga.rollcall.entity.AddressExample;
import org.springframework.stereotype.Repository;

/**
 * AddressMapper继承基类
 */
@Repository
public interface AddressMapper extends MyBatisBaseDao<Address, Long, AddressExample> {
}