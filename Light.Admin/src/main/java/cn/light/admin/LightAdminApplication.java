package cn.light.admin;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * 启动文件
 * @author 二胡子
 */
@Slf4j
@EnableCaching
@MapperScan(basePackages = {"cn.light.entity.mapper"})
@EntityScan(basePackages = {"cn.light.entity.entity"})
@EnableJpaRepositories("cn.light.entity.repository")
@EnableRedisRepositories("cn.light.entity.cache")
@ComponentScan(basePackages = {"cn.light.server.*","cn.light.admin.*"})
@SpringBootApplication
public class LightAdminApplication {

	public LightAdminApplication(SpringUtil springUtil) {

	}

	public static void main(String[] args) {
		try {
			SpringApplication.run(LightAdminApplication.class, args);
		}catch (Exception e){
			//加入捕捉错误，启动不了的时候可以看到原因
			log.error("启动失败",e);
		}

	}

}
