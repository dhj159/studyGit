package org.dhj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dhj.entity.TCity;

import java.util.List;


public interface TCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCity record);

    int insertSelective(TCity record);

    TCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCity record);

    int updateByPrimaryKey(TCity record);

    List<TCity> queryListForPid(TCity city);
}
