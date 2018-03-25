package bright.zheng.poc.api.config;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import bright.zheng.poc.api.service.CacheService;
import bright.zheng.poc.api.service.HelloService;
import bright.zheng.poc.api.service.SessionService;
import bright.zheng.poc.api.service.StudentService;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * Jersey Configuration
 * 
 * <p>
 * 
 * The endpoint will expose not only the real APPs,
 * but also two important documents:
 * <li> - swagger spec: /swagger.json</li>
 * <li> - WADL spec: /application.wadl</li>
 * 
 * </p>
 * 
 * @author bright.zheng
 *
 */
@Component
public class JerseyConfig extends ResourceConfig {

	@Value("${spring.jersey.application-path:/api}")
	private String apiPath;

	public JerseyConfig() {
		this.registerEndpoints();
	}
	
	@PostConstruct
	public void init() {
		this.configureSwagger();
	}

	private void registerEndpoints() {
		this.register(HelloService.class);
		this.register(StudentService.class);
		this.register(SessionService.class);
		this.register(CacheService.class);
		
		this.register(WadlResource.class);
	}

	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);

		BeanConfig config = new BeanConfig();
		config.setTitle("POC - Restful API by Spring Boot, Jersey, Swagger");
		config.setVersion("v1");
		config.setContact("Bright Zheng");
		config.setSchemes(new String[] { "http", "https" });
		config.setBasePath(this.apiPath);
		config.setResourcePackage("bright.zheng.poc.api.service");
		config.setPrettyPrint(true);
		config.setScan(true);
	}

}
