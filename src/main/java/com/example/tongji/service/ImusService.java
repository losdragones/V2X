package com.example.tongji.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tongji.dto.ImusDTO;
import com.example.tongji.entity.Imus;
import com.example.tongji.mapper.ImusMapper;
import com.example.tongji.vo.ImusVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName   ImusService
 *
 */
@Service
public class ImusService {

    @Autowired
    private ImusMapper imusMapper;

    public Integer saveImus(ImusVO imusVO){
        Imus imus =new Imus();
        BeanUtils.copyProperties(imusVO,imus);
        return imusMapper.insert(imus);
    }

    public ImusDTO queryImus(ImusVO imusVO){
        QueryWrapper<Imus> wrapper = new QueryWrapper<Imus>()
                .between("time_stamp",imusVO.getBeginTimeStamp(),imusVO.getEndTimeStamp());
        Page<Imus> page = new Page<>(imusVO.getPageNo(),imusVO.getPageSize());
        Page<Imus> imusPage = imusMapper.selectPage(page,wrapper);

        List<Imus> records = imusPage.getRecords();
        long total = imusPage.getTotal();
        ImusDTO imusDTO = new ImusDTO();
        imusDTO.setImusList(records);
        imusDTO.setPageNo(imusVO.getPageNo());
        imusDTO.setPageSize(imusVO.getPageSize());
        imusDTO.setTotal(total);

        return imusDTO;
    }
}
