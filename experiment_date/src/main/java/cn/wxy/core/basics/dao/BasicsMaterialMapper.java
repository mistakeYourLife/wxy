package cn.wxy.core.basics.dao;

import cn.wxy.core.basics.model.BasicsMaterial;

import java.util.List;

public interface BasicsMaterialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BasicsMaterial record);

    int insertSelective(BasicsMaterial record);

    BasicsMaterial selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BasicsMaterial record);

    int updateByPrimaryKey(BasicsMaterial record);

    List<BasicsMaterial> getAllBasicsMaterials();
}