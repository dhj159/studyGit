package org.dhj.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_city
 * @author 
 */
@Data
public class TCity implements Serializable {
    /**
     * 地区代码
     */
    private Integer id;

    /**
     * 当前地区的上一级地区代码
     */
    private Integer pid;

    /**
     * 地区名称
     */
    private String name;

    private static final long serialVersionUID = 1L;
}