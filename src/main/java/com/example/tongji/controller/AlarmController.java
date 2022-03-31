package com.example.tongji.controller;

import com.example.tongji.dto.AlarmDTO;
import com.example.tongji.dto.GpsDTO;
import com.example.tongji.service.AlarmService;
import com.example.tongji.service.GpsService;
import com.example.tongji.utils.response.ResponseResult;
import com.example.tongji.vo.AlarmVO;
import com.example.tongji.vo.GpsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName   GpsController
 *
 *
 */
@RestController
@RequestMapping("/tongji/alarm")
@Slf4j
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

//    @PostMapping("/save")
//    public ResponseResult<Integer> saveGps(@RequestBody GpsVO gpsVO){
//        return ResponseResult.success(gpsService.saveGps(gpsVO));
//    }

    @PostMapping("/query")
    @CrossOrigin(origins = "*", maxAge = 3600)
    ResponseResult<AlarmDTO> getAlarm(@RequestBody AlarmVO alarmVO){
        return ResponseResult.success(alarmService.queryResult(alarmVO));
    }

//    @PostMapping("/del")
//    public ResponseResult<Integer> deleteGps(Integer id){
//        return ResponseResult.success(alarmService.deleteById(id));
//    }

}
