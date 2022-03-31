package com.example.tongji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//排除掉默认的dataSource配置使用DruidDataSource高效可管理的数据库连接池
// @SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication()
@MapperScan("com.example.tongji.mapper")
@EnableScheduling
public class TongjiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TongjiApplication.class, args);
	}

}