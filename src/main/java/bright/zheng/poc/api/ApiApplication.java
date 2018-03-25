package bright.zheng.poc.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * This is a typical boostrap class for Spring Boot based application
 *  
 * @author bright.zheng
 *
 */
@SpringBootApplication
@EnableRedisHttpSession
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
