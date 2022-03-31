package com.example.tongji.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tongji.dto.AlarmDTO;
import com.example.tongji.dto.ResultDTO;
import com.example.tongji.entity.Alarm;
import com.example.tongji.entity.Result;
import com.example.tongji.mapper.AlarmMapper;
import com.example.tongji.mapper.ResultMapper;
import com.example.tongji.vo.AlarmVO;
import com.example.tongji.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName   ResultService
 */
@Service
public class AlarmService {

    @Autowired
    private AlarmMapper alarmMapper;

//    public Integer saveResult(ResultVO resultVO){
//        Result result = new Result();
//        BeanUtils.copyProperties(resultVO,result);
//        return resultMapper.insert(result);
//    }

    public AlarmDTO queryResult(AlarmVO alarmVO){
        //升序排列 增加查询车牌id
        QueryWrapper<Alarm> wrapper = new QueryWrapper<Alarm>()
                .eq("gps_id",alarmVO.getGpsId())
                .between("time_stamp",alarmVO.getBeginTimeStamp(),alarmVO.getEndTimeStamp())
                .orderByAsc("time_stamp");

        Page<Alarm> page = new Page<>(alarmVO.getPageNo(), alarmVO.getPageSize());
        Page<Alarm> resultPage = alarmMapper.selectPage(page, wrapper);

        List<Alarm> records = resultPage.getRecords();
        long total = resultPage.getTotal();
        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmList(records);
        alarmDTO.setPageNo(alarmVO.getPageNo());
        alarmDTO.setPageSize(alarmVO.getPageSize());
        alarmDTO.setTotal(total);

        return alarmDTO;
    }
}
