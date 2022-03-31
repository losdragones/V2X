package com.example.tongji.controller;

import com.example.tongji.dto.GpsDTO;
import com.example.tongji.service.GpsService;
import com.example.tongji.utils.response.ResponseResult;
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
@RequestMapping("/tongji/gps")
@Slf4j
public class GpsController {

    @Autowired
    private GpsService gpsService;

    @PostMapping("/save")
    public ResponseResult<Integer> saveGps(@RequestBody GpsVO gpsVO){
        return ResponseResult.success(gpsService.saveGps(gpsVO));
    }

    @PostMapping("/query")
    @CrossOrigin(origins = "*", maxAge = 3600)
    ResponseResult<GpsDTO> getGps(@RequestBody GpsVO gpsVO){
        return ResponseResult.success(gpsService.queryGps(gpsVO));
    }

    @PostMapping("/del")
    public ResponseResult<Integer> deleteGps(Integer id){
        return ResponseResult.success(gpsService.deleteById(id));
    }

}
