package com.tga.rollcall.service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.google.common.collect.Lists;
import com.tga.rollcall.dao.AddressMapper;
import com.tga.rollcall.dto.AddressDto;
import com.tga.rollcall.dto.AddressParam;
import com.tga.rollcall.entity.Address;
import com.tga.rollcall.entity.AddressExample;
import com.tga.rollcall.enums.AddressTypeEnum;
import com.tga.rollcall.enums.RollcallServerEnum;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年11月7日 下午6:04:25
 * Class: CommonService.java
 */
@Service
@Slf4j
public class CommonService {
    @Autowired
    AddressMapper addressMapper;
    
    /**
     * 查询基础地址信息
     * @param param
     * @return
     */
    public ResultBase<List<AddressDto>> queryBaseAddress(AddressParam param) {
        List<AddressDto> list = Lists.newArrayList();
        try {
            if (Objects.isNull(param)) {
                return ResultBase.Builder.error(RollcallServerEnum.PARAM_NOT_NULL_ERROR);
            }
            if (StringUtils.isEmpty(param.getAddressType())) {
                return ResultBase.Builder.initError("地址类型不能为空");
            }
            if (AddressTypeEnum.CITY.getCode().equals(param.getAddressType())
                    || AddressTypeEnum.DISTRICT.getCode().equals(param.getAddressType())) {
                if (StringUtils.isEmpty(param.getParentId())) {
                    return ResultBase.Builder.initError("地址父级id不能为空");
                }
            }
            AddressExample example = new AddressExample();
            AddressExample.Criteria criteria = example.createCriteria();
            criteria.andAddressTypeEqualTo(param.getAddressType());
            if (!StringUtils.isEmpty(param.getParentId())) {
                criteria.andParentIdEqualTo(param.getParentId());
            }
            List<Address> addresses = addressMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(addresses)) {
                return ResultBase.Builder.success(list);
            }
            addresses.forEach(data -> {
                list.add(new AddressDto(data.getCityId(), data.getParentId(), data.getAddressName(),
                        AddressTypeEnum.getTypeStr(data.getAddressType())));
            });
            return ResultBase.Builder.success(list);
        } catch (Exception e) {
            log.error("queryBaseAddress   error:{}", e);
            return ResultBase.Builder.error();
        }
    }
}
