package bright.zheng.poc.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * This is a typical boostrap class for Spring Boot based application
 *  
 * @author bright.zheng
 *
 */
@SpringBootApplication
public class ApiApplication 
extends SpringBootServletInitializer {	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	     return builder.sources(ApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
