package com.example.tongji.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tongji.entity.Alarm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlarmMapper extends BaseMapper<Alarm> {

    int insertBatch(List<Alarm> alarmList);
}
