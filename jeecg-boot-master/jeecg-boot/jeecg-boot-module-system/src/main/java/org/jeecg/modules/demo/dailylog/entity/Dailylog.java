package org.jeecg.modules.demo.dailylog.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 日报
 * @Author: jeecg-boot
 * @Date:   2019-10-31
 * @Version: V1.0
 */
@Data
@TableName("dailylog")
public class Dailylog implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    private java.lang.String sysOrgCode;
	/**附件*/
	@Excel(name = "附件", width = 15)
    private java.lang.String accessory;
	/**今日完成工作*/
	@Excel(name = "今日完成工作", width = 15)
    private java.lang.String finishWorkToday;
	/**图片*/
	@Excel(name = "图片", width = 15)
    private java.lang.String image;
	/**备注*/
	@Excel(name = "备注", width = 15)
    private java.lang.String remark;
	/**未完成工作*/
	@Excel(name = "未完成工作", width = 15)
    private java.lang.String unfinishedWork;
	/**需要协调的工作*/
	@Excel(name = "需要协调的工作", width = 15)
    private java.lang.String workToBeCoordinated;
	/**填报人*/
	@Excel(name = "填报人", width = 15)
    private java.lang.String filler;
	/**填报时间*/
	@Excel(name = "填报时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date filldate;
	/**明日工作*/
	@Excel(name = "明日工作", width = 15)
    private java.lang.String workTomorrow;
	/**预留字段*/
	@Excel(name = "预留字段", width = 15)
    private java.lang.String reserve;
}
