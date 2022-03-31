package com.example.tongji.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tongji.dto.ResultDTO;
import com.example.tongji.entity.Result;
import com.example.tongji.mapper.ResultMapper;
import com.example.tongji.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName   ResultService
 */
@Service
public class ResultService {

    @Autowired
    private ResultMapper resultMapper;

    public Integer saveResult(ResultVO resultVO){
        Result result = new Result();
        BeanUtils.copyProperties(resultVO,result);
        return resultMapper.insert(result);
    }

    public ResultDTO queryResult(ResultVO resultVO){
        //升序排列 增加查询车牌id
        QueryWrapper<Result> wrapper = new QueryWrapper<Result>()
                .eq("gps_id",resultVO.getGpsId())
                .between("time_stamp",resultVO.getBeginTimeStamp(),resultVO.getEndTimeStamp())
                .orderByAsc("time_stamp");

        Page<Result> page = new Page<>(resultVO.getPageNo(), resultVO.getPageSize());
        Page<Result> resultPage = resultMapper.selectPage(page, wrapper);

        List<Result> records = resultPage.getRecords();
        long total = resultPage.getTotal();
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResultList(records);
        resultDTO.setPageNo(resultVO.getPageNo());
        resultDTO.setPageSize(resultVO.getPageSize());
        resultDTO.setTotal(total);

        return resultDTO;
    }
}
