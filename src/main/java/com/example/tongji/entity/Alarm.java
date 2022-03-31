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
 * ClassName   Alarm 在result基础上增加 label
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm")
public class Alarm {

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

    private String time1;

    private String time2;

    private String velocity;

    private String acceleration;

    private String label;

    private Integer time_step;

}
