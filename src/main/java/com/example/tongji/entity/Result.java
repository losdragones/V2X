package com.example.tongji.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * ClassName   Result 在gps基础上增加 distance velocity acceleration
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("result")
public class Result {

    @TableId(type = IdType.AUTO)
    private Integer id;

    //解决与mysql关键字冲突,字段名加上反斜杠
    @TableField("`time_stamp`")
    private BigInteger timeStamp;

    private Integer gpsId;

    //解决与mysql关键字冲突,字段名加上反斜杠
    @TableField("`utc_time`")
    private String utcTime;

    private String latitude;

    private String ns;

    private String longitude;

    private String ew;

//    private String gpsStatus;

    private String distance;

    private String velocity;

    private String acceleration;

    private String time1;

}
