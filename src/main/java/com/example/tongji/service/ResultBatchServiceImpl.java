package com.example.tongji.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tongji.entity.Result;
import com.example.tongji.mapper.ResultMapper;
import com.example.tongji.service.IService.ResultBatchService;
import org.springframework.stereotype.Service;

/**
 * ClassName   ResultBatchServiceImpl
 */
@Service("ResultBatchService")
public class ResultBatchServiceImpl extends ServiceImpl<ResultMapper, Result> implements ResultBatchService {

}
