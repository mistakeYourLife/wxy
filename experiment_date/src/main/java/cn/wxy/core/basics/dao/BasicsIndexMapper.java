package cn.wxy.core.basics.dao;

import cn.wxy.core.basics.model.BasicsIndex;

import java.util.List;

public interface BasicsIndexMapper {
    int insert(BasicsIndex record);

    int insertSelective(BasicsIndex record);

    List<BasicsIndex> getAllBasicsIndexs();

}