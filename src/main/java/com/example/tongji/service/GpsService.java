package com.example.tongji.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tongji.dto.GpsDTO;
import com.example.tongji.entity.Gps;
import com.example.tongji.mapper.GpsMapper;
import com.example.tongji.vo.GpsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName   GpsService
 *
 */
@Service
public class GpsService {

    @Autowired
    private GpsMapper gpsMapper;

    public Integer saveGps(GpsVO gpsVO){
        Gps gps = new Gps();
        BeanUtils.copyProperties(gpsVO, gps);
        return  gpsMapper.insert(gps);
    }

    public GpsDTO queryGps(GpsVO gpsVO){

        //升序排列 增加查询车牌id
        QueryWrapper<Gps> wrapper = new QueryWrapper<Gps>()
                .eq("gps_id",gpsVO.getGpsId())
                .between("time_stamp",gpsVO.getBeginTimeStamp(),gpsVO.getEndTimeStamp())
                .orderByAsc("time_stamp");

        Page<Gps> page = new Page<>(gpsVO.getPageNo(), gpsVO.getPageSize());
        Page<Gps> gpsPage = gpsMapper.selectPage(page, wrapper);

        List<Gps> records = gpsPage.getRecords();
        long total = gpsPage.getTotal();
        GpsDTO gpsDTO = new GpsDTO();
        gpsDTO.setGpsList(records);
        gpsDTO.setPageNo(gpsVO.getPageNo());
        gpsDTO.setPageSize(gpsVO.getPageSize());
        gpsDTO.setTotal(total);

        return gpsDTO;
    }

    public Integer deleteById(Integer id){
        return gpsMapper.deleteById(id);
    }

}
