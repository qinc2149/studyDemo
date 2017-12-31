package qinc.demo.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author qinc
 * @version V1.0
 * @Description:
 * @Date 2017/9/26 17:16
 */
@ComponentScan(basePackages = "qinc.demo.com")
@EnableAutoConfiguration
@EnableCaching //开启缓存注解
@EnableScheduling//开启定时任务注解
@EnableAsync//开启异步调用注解
public class App {
    public static  void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}

