package com.example.tongji.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tongji.entity.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResultMapper extends BaseMapper<Result> {

    int insertBatch(List<Result> resultList);
}
