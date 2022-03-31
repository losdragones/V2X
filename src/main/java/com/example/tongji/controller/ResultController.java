package com.example.tongji.controller;

import com.example.tongji.dto.GpsDTO;
import com.example.tongji.dto.ResultDTO;
import com.example.tongji.service.GpsService;
import com.example.tongji.service.ResultService;
import com.example.tongji.utils.response.ResponseResult;
import com.example.tongji.vo.GpsVO;
import com.example.tongji.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName   GpsController
 *
 *
 */
@RestController
@RequestMapping("/tongji/result")
@Slf4j
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/save")
    public ResponseResult<Integer> saveGps(@RequestBody ResultVO resultVO){
        return ResponseResult.success(resultService.saveResult(resultVO));
    }

    @PostMapping("/query")
    @CrossOrigin(origins = "*", maxAge = 3600)
    ResponseResult<ResultDTO> getGps(@RequestBody ResultVO resultVO){
        return ResponseResult.success(resultService.queryResult(resultVO));
    }

//    @PostMapping("/del")
//    public ResponseResult<Integer> deleteGps(Integer id){
//        return ResponseResult.success(resultService.deleteById(id));
//    }

}
