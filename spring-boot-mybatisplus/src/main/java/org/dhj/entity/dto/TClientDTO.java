package org.dhj.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dhj.entity.TClientLinkman;

import java.sql.Date;
import java.util.List;

@Data
@ApiModel(value = "传参视图")
public class TClientDTO {

    private Integer id;

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

    private List<TClientLinkman> linkmanList;

    public TClientDTO() {
    }
}
