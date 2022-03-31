package com.example.tongji.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * GpsVO
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpsVO extends BaseVO{

    private Integer id;

    private BigInteger timeStamp;

    private BigInteger beginTimeStamp;
    private BigInteger endTimeStamp;

    private Integer gpsId;

    private String utcTime;

    private String latitude;

    private String ns;

    private String longitude;

    private String ew;

    private String gpsStatus;
}
