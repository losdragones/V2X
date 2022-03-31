package com.example.tongji.utils.math;

import com.example.tongji.entity.Alarm;
import com.example.tongji.entity.Gps;
import com.example.tongji.entity.Result;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName   CalculateSpeed
 */
public class CalculateGps {

    public List<Result> getResult(List<Gps> gpsList) {
        List<Result> resultList = new ArrayList<>();
        int count1 = 0;
        for (int i = 0; i < gpsList.size() - 1; i++) {
            double lat1, long1, lat2, long2;
            //经纬度、时间不规整的都删掉，不放进resultList中，避免后续计算出bug
            try {
                lat1 = Double.parseDouble(gpsList.get(i).getLatitude());
                long1 = Double.parseDouble(gpsList.get(i).getLongitude());
                lat2 = Double.parseDouble(gpsList.get(i + 1).getLatitude());
                long2 = Double.parseDouble(gpsList.get(i + 1).getLongitude());
                double utcTime = Double.parseDouble(gpsList.get(i).getUtcTime());
            } catch (Exception e) {
                count1++;
                continue;
            }
            double distance = getDistance(lat1, long1, lat2, long2);
            Result tmp = new Result();
            BeanUtils.copyProperties(gpsList.get(i), tmp);
            tmp.setDistance(String.valueOf(distance));
            resultList.add(tmp);
        }
        //添加最后一个gps数据
        Result lastResult = new Result();
        BeanUtils.copyProperties(gpsList.get(gpsList.size() - 1), lastResult);
        resultList.add(lastResult);

        if (resultList.size() < 3) {
            return resultList;
        }
        // resultList判断大小 避免空指针
        for (int i = 0; i < resultList.size() - 2; i++) {
            double distance1, distance2, utcTime1, utcTime2, utcTime3;
//            try {
            distance1 = Double.parseDouble(resultList.get(i).getDistance());
            distance2 = Double.parseDouble(resultList.get(i + 1).getDistance());
            utcTime1 = Double.parseDouble(resultList.get(i).getUtcTime());
            utcTime2 = Double.parseDouble(resultList.get(i + 1).getUtcTime());
            utcTime3 = Double.parseDouble(resultList.get(i + 2).getUtcTime());
//            } catch (Exception e) {
//                count1++;
//                continue;
//            }

            double velocity = getVelocity(distance1, distance2, utcTime1, utcTime2, utcTime3);
            Result tmp = new Result();
            BeanUtils.copyProperties(resultList.get(i), tmp);
            tmp.setVelocity(String.valueOf(velocity));
            resultList.set(i, tmp);
        }

        if (resultList.size() < 4) {
            return resultList;
        }
        // resultList判断大小 避免空指针
        for (int i = 0; i < resultList.size() - 3; i++) {
            double velocity1 = Double.parseDouble(resultList.get(i).getVelocity());
            double velocity2 = Double.parseDouble(resultList.get(i + 1).getVelocity());
            double utcTime1 = Double.parseDouble(resultList.get(i).getUtcTime());
            double utcTime2 = Double.parseDouble(resultList.get(i + 1).getUtcTime());
            double utcTime3 = Double.parseDouble(resultList.get(i + 2).getUtcTime());

            double acceleration = getAcceleration(velocity1, velocity2, utcTime1, utcTime2, utcTime3);
            Result tmp = new Result();
            BeanUtils.copyProperties(resultList.get(i), tmp);
            tmp.setAcceleration(String.valueOf(acceleration));
            resultList.set(i, tmp);
        }

        System.out.println("删掉不规整数据个数: " + count1);
        if (resultList.size() < 4) {
            return resultList;
        }
        //删除最后三列的数值，有null
        resultList.remove(resultList.size() - 1);
        resultList.remove(resultList.size() - 2);
        resultList.remove(resultList.size() - 3);

        if (resultList.size() > 1) {
            resultList = filter(resultList);
        }

        return resultList;
    }

    private double getDistance(double lat1, double long1, double lat2, double long2) {

        lat1 = (int) lat1 / 100 + (lat1 % 100) / 60;
        lat2 = (int) lat2 / 100 + (lat2 % 100) / 60;
        long1 = (int) long1 / 100 + (long1 % 100) / 60;
        long2 = (int) long2 / 100 + (long2 % 100) / 60;

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        long1 = Math.toRadians(long1);
        long2 = Math.toRadians(long2);

        double dlat = Math.abs(lat1 - lat2);
        double dlong = Math.abs(long1 - long2);
        //计算地球两点距离的公式 1
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(dlat / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlong / 2), 2)));
        distance = distance * 6378137.0;//弧长乘地球半径（半径为米）
        distance = Math.round(distance * 10000d) / 10000d;//精确距离的数值

        //计算地球两点距离的公式 2
//        double distance=

        return distance;
    }

    private double getVelocity(double distance1, double distance2, double utcTime1, double utcTime2, double utcTime3) {

        double velocity;
        if (distance1 < 400) {
            if ((utcTime2 - utcTime1) < 10) {
                velocity = 3.6 * distance1 / (utcTime2 - utcTime1);
            } else {
                velocity = 3.6 * distance1 / (utcTime3 - utcTime2);
            }
        } else {
            velocity = 3.6 * distance2 / (utcTime3 - utcTime2);
        }
        return velocity;
    }

    private double getAcceleration(double velocity1, double velocity2, double utcTime1, double utcTime2, double utcTime3) {
        double acceleration;
        if ((velocity2 * velocity1) != 0 && (utcTime2 - utcTime1) < 10 && (utcTime3 - utcTime2) < 10) {
            acceleration = (velocity2 - velocity1) / 3.6 / (utcTime2 - utcTime1);
        } else {
            acceleration = 0;
        }
        return acceleration;
    }

    private List<Result> filter(List<Result> resultList) {

        //距离小于50m  时间连续  速度也连续 好数据

        //速度大于150 或加速度大于5  异常删掉
        //删除list元素 倒序遍历避免索引左移错误
        int count2 = 0;
        int count3 = 0;
        for (int i = resultList.size() - 1; i >= 0; i--) {

            //暂时把少部分空值都设定为0，后续在之前算法中寻找为什么有null的速度和加速度
            if (resultList.get(i).getVelocity() == null || resultList.get(i).getAcceleration() == null) {
                Result tmp = new Result();
                BeanUtils.copyProperties(resultList.get(i), tmp);
                tmp.setVelocity(String.valueOf(0));
                tmp.setAcceleration(String.valueOf(0));
                resultList.set(i, tmp);
                continue;
            }

            double velocity = Double.parseDouble(resultList.get(i).getVelocity());
            double acceleration = Math.abs(Double.parseDouble(resultList.get(i).getAcceleration()));

            if (velocity > 150 || acceleration > 5) {
                resultList.remove(i);
                count2++;
            }
        }

        if (resultList.size() < 2) {
            return resultList;
        }
        //时间不相邻，速度、加速度写为0
        for (int i = resultList.size() - 1; i > 0; i--) {
            BigInteger divide = resultList.get(i).getTimeStamp().subtract(resultList.get(i - 1).getTimeStamp());
            if (divide.longValue() != 1) {
                Result tmp = new Result();
                BeanUtils.copyProperties(resultList.get(i), tmp);
                tmp.setVelocity(String.valueOf(0));
                tmp.setAcceleration(String.valueOf(0));
                resultList.set(i, tmp);
                count3++;
            }
        }
        System.out.println("删掉异常值个数: " + count2);
        System.out.println("时间不相邻个数: " + count3);
        return resultList;
    }

    //急加减速报警
    public List<Alarm> alarmACC(List<Result> resultList) {
        List<Alarm> alarmList = new ArrayList<>();

        double velocity = 0;
        double acceleration = 0;
        for (int i = 0; i < resultList.size(); i++) {
            velocity = Double.parseDouble(resultList.get(i).getVelocity());
            acceleration = Math.abs(Double.parseDouble(resultList.get(i).getAcceleration()));
            if ((velocity < 30 && acceleration >= 2.1)
                    || (velocity >= 30 && velocity < 40 && acceleration >= 1.86)
                    || (velocity >= 40 && velocity < 60 && acceleration >= 1.74)
                    || (velocity >= 60 && velocity < 80 && acceleration >= 1.62)
                    || (velocity >= 80 && velocity < 100 && acceleration >= 1.38)
                    || (velocity >= 100 && velocity < 120 && acceleration >= 1.14)
                    || (velocity >= 120 && acceleration >= 1.14)) {
                Alarm tmp = new Alarm();
                BeanUtils.copyProperties(resultList.get(i), tmp);
                tmp.setLabel("dangerous");
                alarmList.add(tmp);
            }
        }
        return alarmList;
    }
}

