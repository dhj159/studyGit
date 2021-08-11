package org.dhj.mapper;

import org.dhj.entity.TClientLinkman;

import java.util.List;

public interface TClientLinkmanMapper {
    int insert(TClientLinkman record);

    int insertSelective(TClientLinkman record);

    void delete(int id);

    void update(TClientLinkman client);

    List<TClientLinkman> queryList();

    void deleteByCid(int cid);

    void updateList(TClientLinkman linkmanList);

    void updateMan(TClientLinkman item);

    TClientLinkman queryLinkMan(Integer tlid);
}
