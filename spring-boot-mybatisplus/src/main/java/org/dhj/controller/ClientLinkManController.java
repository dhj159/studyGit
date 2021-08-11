package org.dhj.controller;

import org.apache.ibatis.annotations.Mapper;
import org.dhj.entity.TClient;
import org.dhj.entity.TClientLinkman;
import org.dhj.entity.dto.TClientDTO;
import org.dhj.mapper.TClientLinkmanMapper;
import org.dhj.mapper.TClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 操纵联系人
 */
@RestController
@RequestMapping("link")
public class ClientLinkManController {
    @Autowired
    private TClientLinkmanMapper linkmanMapper;
    @Autowired
    private TClientMapper mapper;

    /**
     * 添加一个联系人信息
     * @param client
     */
    @PostMapping("addContact")
    public void addContact(TClientDTO client){


//        linkmanMapper.insert(client);
    }

    /**
     * 通过主键tlid删除一个联系人
     * @param tlid
     */
    @RequestMapping("deleteContact")
    public void deleteMan(int tlid){
        linkmanMapper.delete(tlid);
    }

    /**
     * 修改一个联系人
     * @param client
     */
    @RequestMapping("updateContact")
    public void update(TClientLinkman client){
        linkmanMapper.update(client);
    }

    /**
     * 查询所有联系人信息
     * @return
     */
    @GetMapping("queryAllContacts")
    public List<TClientLinkman> queryAllContacts(){
        return linkmanMapper.queryList();
    }
}
