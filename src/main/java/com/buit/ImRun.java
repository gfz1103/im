package com.buit;

import com.buit.config.EnableLocked;
import com.buit.config.EnableMessage;
import com.buit.utill.SpringContextUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName: HisRun
 * @Description: 启动方法
 * @author 神算子
 * @date 2020年4月26日 下午3:31:56
 */
@EnableDubbo
@EnableScheduling
@SpringBootApplication
@EnableLocked
@EnableMessage
@EnableRabbit
public class ImRun {
	public static void main(String[] args) {
		ConfigurableApplicationContext con=SpringApplication.run(ImRun.class, args);
		SpringContextUtil.setApplicationContext(con);
	}
}