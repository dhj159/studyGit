package org.dhj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * t_client_linkman
 * @author
 */
@Data
@ApiModel(value = "联系人视图")
public class TClientLinkman implements Serializable {
    private Integer tid;
    /**
     * 主键id
     */
    private Integer tlid;
    /**
     * 用户联系人Id与t_client相连
     */
    private Integer cid;

    /**
     * 联系人姓名
     */
    @ApiModelProperty(value = "联系人姓名")
    private String lname;

    /**
     * 联系人手机
     */
    @ApiModelProperty(value = "联系人手机")
    private String ltel;

    /**
     * 办公电话
     */
    @ApiModelProperty(value = "办公电话")
    private String officePhone;

    /**
     * 出生日期
     */

    @ApiModelProperty(value = "出生日期")
    private Date hiredate;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门")
    private String dept;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位")
    private String job;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    private Integer flag;

    private static final long serialVersionUID = 1L;
}
