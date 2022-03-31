package com.example.tongji.dto;

import com.example.tongji.entity.Alarm;
import com.example.tongji.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName   GpsDTO
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmDTO extends BaseVO {

    private List<Alarm> alarmList;
}
