package com.example.tongji.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * ResultVO
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmVO extends BaseVO{

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

    private String distance;

    private String velocity;

    private String acceleration;
}
