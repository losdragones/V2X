package com.example.tongji.scheduled;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tongji.entity.Alarm;
import com.example.tongji.entity.Gps;
import com.example.tongji.entity.Result;
import com.example.tongji.mapper.AlarmMapper;
import com.example.tongji.mapper.GpsMapper;
import com.example.tongji.mapper.ResultMapper;
import com.example.tongji.service.IService.ResultBatchService;
import com.example.tongji.utils.math.CalculateGps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;

/**
 * ScheduledGps
 */
@Component
public class ScheduledGps {

    @Autowired
    private GpsMapper gpsMapper;

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private AlarmMapper alarmMapper;

    @Autowired
    private ResultBatchService resultBatchService;

    //    private List<Integer> gps_id = new ArrayList<>();
    private BigInteger beginTimeStamp = new BigInteger("1640554955");
    private BigInteger endTimeStamp = new BigInteger("1640558558");//加3603

//    @Scheduled(cron = "*/20 * * * * ?")
    public void ScheduledGpsTask() {
        long start1 = System.currentTimeMillis();

        //升序排列 增加查询车牌id
        QueryWrapper<Gps> wrapper = new QueryWrapper<Gps>()
                //.eq("gps_id", gps_id)
                .between("time_stamp", beginTimeStamp, endTimeStamp)
                .orderByAsc("time_stamp");
        List<Gps> gpsResult = gpsMapper.selectList(wrapper);

        //查询结果车牌id分开
        Map<Integer, List<Gps>> map = new HashMap<>();
        int[] gps_id = new int[]{3, 8, 10, 11, 12, 13, 15, 17, 18,
                19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};

        for (int i = 0; i < gps_id.length; i++) {
            List<Gps> gpsList = new ArrayList<>();
            map.put(gps_id[i], gpsList);
        }
        for (int i = 0; i < gpsResult.size(); i++) {
            map.get(gpsResult.get(i).getGpsId()).add(gpsResult.get(i));
        }

        //计算每辆车的速度、加速度，结果都存在resultList中
        CalculateGps calculateGps = new CalculateGps();
        List<Result> resultList = new ArrayList<>();
        List<Alarm> alarmList = new ArrayList<>();

        for (int i = 0; i < gps_id.length; i++) {
            if (map.get(gps_id[i]).size() > 3) {
                List<Result> resultList_tmp = calculateGps.getResult(map.get(gps_id[i]));
                resultList.addAll(resultList_tmp);

                //每辆车输出报警信息
                if (resultList_tmp.size() > 0) {
                    List<Alarm> alarmList_tmp = calculateGps.alarmACC(resultList_tmp);
                    alarmList.addAll(alarmList_tmp);
                }
            }
        }
        long end1 = System.currentTimeMillis();

        long start = System.currentTimeMillis();
        int count = 0;
        if (resultList.size() != 0) {
            //批量插入result表，batch设置每次插入块的大小
            int batch = 2000;
            for (int i = 0; i < Math.floorDiv(resultList.size(), batch); i++) {
                resultMapper.insertBatch(resultList.subList(i * batch, (i + 1) * batch));
                count++;
            }
            if ((resultList.size() % batch) != 0) {
                resultMapper.insertBatch(resultList.subList(resultList.size() - (resultList.size() % batch), resultList.size()));
                count++;
            }
//        resultBatchService.saveBatch(resultList,1000);//主键重复插入错误
        }

        if (alarmList.size() != 0) {
            alarmMapper.insertBatch(alarmList);
        }
        long end = System.currentTimeMillis();

        System.out.println("查询与计算时间: " + (end1 - start1));
        System.out.println("插入次数: " + count);
        System.out.println("插入执行时间: " + (end - start));
        beginTimeStamp = beginTimeStamp.add(new BigInteger("3600"));
        endTimeStamp = endTimeStamp.add(new BigInteger("3600"));
    }

}
