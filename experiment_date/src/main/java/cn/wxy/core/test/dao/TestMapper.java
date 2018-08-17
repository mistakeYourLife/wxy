package cn.wxy.core.test.dao;

import cn.wxy.common.pageable.Page;
import cn.wxy.core.test.model.Test;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    Page findByPage(Page pageRequest, @Param("map") Map map);

}