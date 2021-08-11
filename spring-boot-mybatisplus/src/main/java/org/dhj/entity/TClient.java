package org.dhj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;
import java.util.List;

/**
 * t_client
 * @author
 */
@ApiModel(value = "用户视图")
public class TClient{
    private Integer id;
    /**
     * 客户Id
     */
    private Integer cid;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String cname;

    /**
     * 投保意向
     */
    @ApiModelProperty(value = "投保意向")
    private String intention;

    /**
     * 证件类型
     */
    @ApiModelProperty(value = "证件类型")
    private String codeType;

    /**
     * 行业类型
     */
    @ApiModelProperty(value = "行业类型")
    private String industryType;

    /**
     * 单位性质
     */
    @ApiModelProperty(value = "单位性质")
    private String unitType;

    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码")
    private String codeId;

    /**
     * 单位总人数
     */
    @ApiModelProperty(value = "单位总人数")
    private Integer unitCount;

    /**
     * 预计投保人数
     */
    @ApiModelProperty(value = "预计投保人数")
    private Integer insureCount;

    /**
     * 单位地址
     */
    @ApiModelProperty(value = "单位地址")
    private String address;

    /**
     * 星级
     */
    @ApiModelProperty(value = "星级")
    private String starLevel;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "联系人列表")
    private List<TClientLinkman> linkmanList;

    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public Integer getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }

    public Integer getInsureCount() {
        return insureCount;
    }

    public void setInsureCount(Integer insureCount) {
        this.insureCount = insureCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public List<TClientLinkman> getLinkmanList() {
        return linkmanList;
    }

    public void setLinkmanList(List<TClientLinkman> linkmanList) {
        this.linkmanList = linkmanList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TClient{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", intention='" + intention + '\'' +
                ", codeType='" + codeType + '\'' +
                ", industryType='" + industryType + '\'' +
                ", unitType='" + unitType + '\'' +
                ", codeId='" + codeId + '\'' +
                ", unitCount=" + unitCount +
                ", insureCount=" + insureCount +
                ", address='" + address + '\'' +
                ", starLevel='" + starLevel + '\'' +
                ", linkmanList=" + linkmanList +
                '}';
    }

    private static final long serialVersionUID = 1L;
}
