package bright.zheng.poc.api.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import bright.zheng.poc.api.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/v1/sessions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Session API - it's a service to test sessions", produces = "application/json")
public class SessionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);
	
	@Context private HttpServletRequest request;

	@GET							//JAX-RS Annotation
	@Path("/")					//JAX-RS Annotation
	@ApiOperation(				//Swagger Annotation
			value = "Get all session info", 
			response = Student.class)  
	@ApiResponses(value = {		//Swagger Annotation
		@ApiResponse(code = 200, message = "Success")
	})
	public Response getSessions() {
		
		LOGGER.info("v1/sessions/");
		//this.printExtraInfo(request);
		
		Map<String,String> map = new HashMap<String,String>();
		Enumeration<?> keys = request.getSession().getAttributeNames();
		while (keys.hasMoreElements())
		{
		  String key = (String)keys.nextElement();
		  map.put(key, (String)request.getSession().getAttribute(key));
		}
		
		return Response.status(Status.OK).entity(map).build();
	}

	@GET							//JAX-RS Annotation
	@Path("/{name}")				//JAX-RS Annotation
	@ApiOperation(				//Swagger Annotation
			value = "Get one specific session by name", 
			response = Student.class)  
	@ApiResponses(value = {		//Swagger Annotation
		@ApiResponse(code = 200, message = "Success")
	})
	public Response getSessionByName(@ApiParam @PathParam("name") String name) {
		
		LOGGER.info("GET v1/sessions/{} ...", name);
		
		Map<String,String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		LOGGER.info("GET session id: {}", session.getId());
		String value = (String)request.getSession().getAttribute(name);
		map.put(name, value);
		LOGGER.info("GET v1/sessions/{} -> {}", name, value);
		
		return Response.status(Status.OK).entity(map).build();
	}

	@PUT							//JAX-RS Annotation
	@Path("/{name}")				//JAX-RS Annotation
	@ApiOperation(				//Swagger Annotation
			value = "Set or update one specific session", 
			response = Student.class)  
	@ApiResponses(value = {		//Swagger Annotation
		@ApiResponse(code = 200, message = "Success")
	})
	public Response putSession(
			@ApiParam @PathParam("name") String name, 
			@ApiParam @QueryParam("value") String value) {
		
		LOGGER.info("v1/sessions/{} <- {}", name, value);
		
		HttpSession session = request.getSession();
		LOGGER.info("PUT session id: {}", session.getId());
		request.getSession().setAttribute(name, value);
		
		return Response.status(Status.OK).build();
	}
	
//
//	@Autowired
//	private RedisTemplate redisTemplate;
//	
//	private void printExtraInfo(HttpServletRequest request) {
//		HttpSession session = request.getSession(true);
//		session.setMaxInactiveInterval(60);
//		String sessionId = session.getId();
//		String springSessionExpiresKey = "spring:session:sessions:expires:" + sessionId;
//		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//		connection.info();
//		Properties info = connection.info();
//		Enumeration<?> keys = info.keys();
//		while (keys.hasMoreElements()) {
//			Object key = keys.nextElement();
//			LOGGER.info("Redis Config: {} - {}", key, info.get(key));
//		}
//	}
	
}