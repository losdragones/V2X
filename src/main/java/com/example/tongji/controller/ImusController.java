package com.example.tongji.controller;

import com.example.tongji.dto.ImusDTO;
import com.example.tongji.service.ImusService;
import com.example.tongji.utils.response.ResponseResult;
import com.example.tongji.vo.ImusVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName   ImusController
 *
 */
@RestController
@RequestMapping("/tongji/imus")
@Slf4j
public class ImusController {

    @Autowired
    private ImusService imusService;

    @PostMapping("/save")
    public ResponseResult<Integer> saveImus(@RequestBody ImusVO imusVO){
        return ResponseResult.success(imusService.saveImus(imusVO));
    }

    @PostMapping("/query")
    public ResponseResult<ImusDTO> getImus(@RequestBody ImusVO imusVO){
        return ResponseResult.success(imusService.queryImus(imusVO));
    }
}
