package org.dhj.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import org.dhj.entity.Result;
import org.dhj.entity.TClient;
import org.dhj.entity.TClientLinkman;
import org.dhj.entity.dto.TClientDTO;
import org.dhj.mapper.TClientLinkmanMapper;
import org.dhj.mapper.TClientMapper;
import org.dhj.util.RedisUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/client")
@Api(tags = "测试用户")
public class ClientController {
    @Autowired
    private TClientMapper mapper;
    @Autowired
    private TClientLinkmanMapper linkmanMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AmqpTemplate t;
    @Autowired
    private RabbitAdmin admin;
    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 查询所有用户
     * @return
     */
    @ApiOperation("获取用户列表")
    @GetMapping("queryAllClients")
    public List queryAllClient( TClient client){
        if (client.getCname() != null ){
            return mapper.queryListForTClient(client);
        }else {
            if (redisUtil.exists("queryAllClients")){
                List<Object> queryAllClients = redisUtil.lRange("queryAllClients", 0, -1);
                return redisUtil.lRange("queryAllClients", 0, -1);
            }else {
                redisUtil.lPush("queryAllClients",mapper.queryListForTClient(client));
                return redisUtil.lRange("queryAllClients", 0, -1);
            }
        }
    }

    /**
     * 查询有客户号的用户列表
     * @return
     */
    @ApiOperation("获取有客户号的用户列表")
    @GetMapping("queryLocal")
    public List query(TClient client){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.registerJsonBeanProcessor(java.sql.Date.class,new JsDateJsonBeanProcessor());
        if (client.getCname() != null ){
            return mapper.queryUser(client);
        }else {

            if (redisUtil.exists("queryLocal")){
//                JSONArray queryLocal = JSONArray.fromObject(mapper.queryUser(client));
//                List<Object> queryLocal1 = redisUtil.lRange("queryLocal", 0, -1);
//
//                t.convertAndSend("queryCustomer", queryLocal.toString());
                return redisUtil.lRange("queryLocal", 0, -1);
            }else {
                redisUtil.lPush("queryLocal",mapper.queryUser(client));
                return redisUtil.lRange("queryLocal", 0, -1);
            }
        }
//        return mapper.queryUser(client);
    }

    /**
     * 通过客户号查询单个用户
     * @param
     * @return
     */
    @ApiOperation(value = "通过客户号查询单个用户")
    @GetMapping("queryClientById/{cid}")
    public TClient queryListForTClientById(@PathVariable int cid){
        if (cid == 0){
            return null;
        }else {
            return mapper.queryListForTClientId(cid);
        }
    }

    /**
     * 通过id查询客户信息
     * @param id 客户id
     * @return
     */
    @ApiOperation(value = "通过id查询客户信息")
    @GetMapping("queryClientId")
    public TClient queryListForTClientId(int id){
        return mapper.queryTClientById(id);
    }

    /**
     * 查询本地客户列表
     * @return
     */
    @ApiOperation(value = "查询本地客户列表")
    @GetMapping("queryNoId")
    public List queryNot( TClient client){
        if (client.getCname() != null ){
            return mapper.queryNotIdList(client);
        }else {
            if (redisUtil.exists("NoList")){

                return redisUtil.lRange("NoList", 0, -1);
            }else {
                redisUtil.lPush("NoList",mapper.queryNotIdList(client));
                return redisUtil.lRange("NoList", 0, -1);
            }
        }
    }

    /**
     * 添加一个用户 如果五个值为空
     * 则为一星用户，否则是二星用户
     * @param client 单个用户
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("addClient")
    public Result addClient(TClient client) throws Exception{
        Result result = new Result();
        if (client != null) {
            if (client.getUnitType() == null || client.getUnitCount() == null ||
                    client.getInsureCount() == null || client.getIntention() == null ||
                    client.getIndustryType() == null){
                client.setStarLevel("一星");
                mapper.insertSelective(client);
                TClient tClient = mapper.selectName(client);
                client.getLinkmanList().forEach(item -> {
                    item.setTlid(tClient.getId());
                    linkmanMapper.insert(item);
                });
                result.setCode(200);
                result.setMessage("添加成功");
                redisUtil.remove("NoList");
                redisUtil.remove("queryAllClients");
//                objectMapper.writer
//                JSONObject.
//                JSONObject jsonObject = JSONObject.fromObject(client);
//                System.out.println(jsonObject.toString());
                admin.declareQueue(new Queue("addQueue"));
                String model = JSON.toJSONString(client);
                t.convertAndSend("addQueue",model);
                return result;
            }else {
                int id = (int) ((Math.random() * 9 + 1) * 1000);
                client.setCid(id);
                client.setCreateTime(new Date(System.currentTimeMillis()));
                client.setStarLevel("二星");
                mapper.insertSelective(client);
                TClient tClient = mapper.queryListForTClientId(client.getCid());
                client.getLinkmanList().forEach(item -> {
                    item.setCid(client.getCid());
                    item.setTlid(tClient.getId());
                    linkmanMapper.insert(item);
                });
                admin.declareQueue(new Queue("addQueue"));
                redisUtil.remove("queryLocal");
                redisUtil.remove("queryAllClients");
                String model = JSON.toJSONString(client);
                t.convertAndSend("addQueue",model);
                result.setCode(200);
                result.setMessage("添加成功");
                return result;
            }

        }else {
            result.setCode(400);
            result.setMessage("添加失败");
            return result;
        }
    }
    /**
     * 修改本地客户的客户信息及联系人信息
     * @param
     */
    @ApiOperation(value = "修改本地客户的客户信息及联系人信息")
    @PutMapping("updateClient")
    public Result updateClient(TClient client){
        Result result = new Result();
        if (client == null){
            result.setCode(400);
            result.setMessage("修改失败");
            return result;
        }else {
            if (client.getUnitType() == null || client.getUnitCount() == null ||
                    client.getInsureCount() == null || client.getIntention() == null ||
                    client.getIndustryType() == null){
                mapper.updateByid(client);
                List<TClientLinkman> linkmanList = client.getLinkmanList();
                linkmanList.forEach(item ->{
                    if (item.getTlid() == null) {
                        item.setTlid(client.getId());
                        linkmanMapper.insert(item);
                    }
                    linkmanMapper.updateList(item);
                    redisUtil.remove("NoList");
                    redisUtil.remove("queryAllClients");
                });
            }else if (client.getUnitType() != null || client.getUnitCount() != null ||
                    client.getInsureCount() != null || client.getIntention() != null || client.getIndustryType() != null){
                Integer id = (int) ((Math.random() * 9 + 1) * 1000);
                client.setCreateTime(new Date(System.currentTimeMillis()));
                client.setCid(id);
                client.setStarLevel("二星");
                mapper.updateByid(client);
                TClient tClient = mapper.queryTClientById(client.getId());
                client.getLinkmanList().forEach(item ->{
                    if (item.getTlid() == null) {
                        item.setTlid(client.getId());
                        item.setCid(tClient.getCid());
                        if (client.getLinkmanList().size() > 1) {
                            item.setFlag(0);
                        }else {
                            item.setFlag(1);
                        }
                        linkmanMapper.insert(item);
                    }else {
                        item.setCid(client.getCid());
                        linkmanMapper.updateList(item);
                    }
                });
                redisUtil.remove("queryLocal");
                redisUtil.remove("queryAllClients");
            }
            result.setCode(201);
            result.setMessage("修改成功");
            return result;
        }
    }

    /**
     * 修改准客户的信息及对应联系人的信息
     * 如果没有联系则增加联系人
     * @param client
     */
    @ApiOperation(value = "修改准客户的信息及对应联系人的信息")
    @PutMapping("updateStandUser")
    public Result updateStandUser(TClientDTO client){
        Result result = new Result();
        if (client == null) {
            result.setCode(400);
            result.setMessage("修改失败");
            return result;
        }else {
            if (client.getLinkmanList().size() >= 1){
                mapper.updateByPrimaryKeySelective(client);
                client.getLinkmanList().forEach(item ->{
                    if (item.getTlid() == null){
                        item.setTlid(client.getId());
                        item.setCid(client.getCid());
//                    item.setFlag(1);
                        linkmanMapper.insert(item);
                    }else {
                        linkmanMapper.updateList(item);
                    }

                });
            }else {
                mapper.updateByPrimaryKeySelective(client);
                TClient tClient = mapper.queryTClientById(client.getId());
                List<TClientLinkman> linkmanList = client.getLinkmanList();
                TClientLinkman tClientLinkman = linkmanMapper.queryLinkMan(client.getId());
                if (tClientLinkman == null){
                    linkmanList.forEach(item -> {
                        item.setTlid(client.getId());
                        item.setCid(tClient.getCid());
                        item.setFlag(1);
                        linkmanMapper.insert(item);
                    });
                }
            }
            redisUtil.remove("queryLocal");
            redisUtil.remove("queryAllClients");
            result.setCode(201);
            result.setMessage("修改成功");
            return result;
        }

    }

    /**
     * 通过cid删除客户并删除对应的联系人
     *
     */
    @ApiOperation(value = "通过cid删除客户并删除对应的联系人")
    @DeleteMapping("deleteClient")
//    @RequestMapping(value = "deleteClient",method = RequestMethod.DELETE)
    public Result deleteClient(int cid){
        Result result = new Result();
        if (cid != 0){
            mapper.deleteByPrimaryKey(cid);
            linkmanMapper.deleteByCid(cid);
            result.setCode(200);
            result.setMessage("删除成功");
            redisUtil.remove("NoList");
            redisUtil.remove("queryLocal");
            redisUtil.remove("queryAllClients");
            return result;
        }else {
            result.setCode(400);
            result.setMessage("删除失败");
            return result;
        }
    }

    @ApiOperation(value = "通过id删除客户并删除对应的联系人")
    @DeleteMapping("delete")
    public Result deleteUser(int id){
        Result result = new Result();
        mapper.deleteById(id);
        linkmanMapper.delete(id);
        result.setCode(200);
        result.setMessage("删除成功");
        redisUtil.remove("NoList");
        redisUtil.remove("queryLocal");
        redisUtil.remove("queryAllClients");
        return result;
    }
}
