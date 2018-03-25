package bright.zheng.poc.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bright.zheng.poc.api.model.Student;
import bright.zheng.poc.api.repository.CacheRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/v1/cache")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Cache API - it's a service to test cache", produces = "application/json")
public class CacheService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);
	
	@Autowired
	private CacheRepository cacheRepository;

	@GET							//JAX-RS Annotation
	@Path("/{id}")				//JAX-RS Annotation
	@ApiOperation(				//Swagger Annotation
			value = "Get one specific cache by id", 
			response = Student.class)  
	@ApiResponses(value = {		//Swagger Annotation
		@ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 404, message = "Not found")
	})
	public Response getCacheByName(@ApiParam @PathParam("id") String id) {
		
		LOGGER.info("GET v1/cache/{} ...", id);
		
		Student student = cacheRepository.findOne(id);
		if (student == null) {
			LOGGER.info("no student is found in cache");
			
			return Response.status(Status.NOT_FOUND).entity("NOT FOUND").build();
			
		} else {
			
			LOGGER.info("GET cached student: {} - {}", student.getId(), student.getName());
			return Response.status(Status.OK).entity(student).build();
		}
	}

	@POST						//JAX-RS Annotation
	@Path("/")					//JAX-RS Annotation
	@ApiOperation(				//Swagger Annotation
			value = "Set or update one specific cache", 
			response = Student.class)  
	@ApiResponses(value = {		//Swagger Annotation
		@ApiResponse(code = 200, message = "Success")
	})
	public Response putSession(
			@ApiParam Student student) {
		
		LOGGER.info("POST v1/cache: {} - {}", student.getId(), student.getName());
		cacheRepository.save(student);
		
		return Response.status(Status.OK).build();
	}
//
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//	    return new JedisConnectionFactory();
//	}
//	
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//	    RedisTemplate<String, Object> template = new RedisTemplate<>();
//	    template.setConnectionFactory(jedisConnectionFactory());
//	    return template;
//	}
}
