package com.example.tongji.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * ClassName   ImusVO
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImusVO extends BaseVO{

    private Integer id;

    private BigInteger timeStamp;

    private BigInteger beginTimeStamp;
    private BigInteger endTimeStamp;

    private Integer imUid;

    private String accX;
    private String accY;
    private String accZ;
}
