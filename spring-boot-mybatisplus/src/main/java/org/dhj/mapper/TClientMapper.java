package org.dhj.mapper;

import org.dhj.entity.TClient;
import org.dhj.entity.dto.TClientDTO;

import java.util.List;

public interface TClientMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(TClient record);

    int insertSelective(TClient record);

    TClient selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(TClientDTO record);

    int updateByid(TClient tClient);

    int updateByPrimaryKey(TClient record);

    List<TClient> queryListForTClient(TClient client);

    TClient selectName(TClient client);
    TClient queryTClientById(int id);

    List<TClient> queryUser(TClient client);

    List<TClient> queryNotIdList(TClient client);
    TClient queryListForTClientId(int cid);

    void updateStandUser(TClientDTO client);

    void deleteById(int id);
}
