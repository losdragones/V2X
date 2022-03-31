package com.example.tongji.controller;

import com.example.tongji.dto.GpsDTO;
import com.example.tongji.service.GpsService;
import com.example.tongji.utils.response.ResponseResult;
import com.example.tongji.vo.GpsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName   GpsThymeleafController
 */
@Controller
@RequestMapping("/tongji/gps")
@Slf4j
public class GpsThymeleafController {

    @Autowired
    private GpsService gpsService;

//    @PostMapping("/save")
//    public ResponseResult<Integer> saveGps(@RequestBody GpsVO gpsVO){
//        return ResponseResult.success(gpsService.saveGps(gpsVO));
//    }

    @RequestMapping("/test")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public String test(Model model) {
//        model.addAttribute("gpsVO", new GpsVO());
        return "trajectory_track";
    }

    //获取表单参数
    @PostMapping("/test")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public String testRes(Model model, @ModelAttribute GpsVO gpsVO) {
        model.addAttribute("result", ResponseResult.success(gpsService.queryGps(gpsVO)));
        return "trajectory_track";
    }
    @RequestMapping("/result")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public String test1(Model model) {
//        model.addAttribute("gpsVO", new GpsVO());
        return "pre_warning";
    }

    //获取表单参数
    @PostMapping("/result")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public String testRes2(Model model, @ModelAttribute GpsVO gpsVO) {
        model.addAttribute("result", ResponseResult.success(gpsService.queryGps(gpsVO)));
        return "pre_warning";
    }


//    ResponseResult<GpsDTO> getGps(@RequestBody GpsVO gpsVO){
//        return ResponseResult.success(gpsService.queryGps(gpsVO));
//    }

//    @PostMapping("/del")
//    public ResponseResult<Integer> deleteGps(Integer id){
//        return ResponseResult.success(gpsService.deleteById(id));
//    }

}
