package bright.zheng.poc.api;

import static org.hamcrest.Matchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Application level testing
 * 
 * @author bright.zheng
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiApplication.class)
@WebIntegrationTest({"server.port=5555"})
public class ApplicationTests {
	
	@BeforeClass
    public static void setupURL() {
        RestAssured.baseURI = "http://localhost:5555";
        RestAssured.basePath = "/";
    }
  
    /**
     * 
     * {"app":{"name":"springboot-jersey-swagger"},"build":{"version":"1.0.0-SNAPSHOT"}}
     * 
     * @throws Exception
     */
    @Test
    public void springroot_info() throws Exception {
    	expect().
	    	body("app.name", equalTo("springboot-jersey-swagger")).
	    	body("build.version", equalTo("1.0.0-SNAPSHOT")).
	    when().
	    	get("/info");
    }
    
    /**
     * {"msg":"Hello Bright - application/json"}
     * 
     * @throws Exception
     */
    @Test
    public void hello_get() {
    	expect().
    		body("msg", containsString("Hello Bright")).
        when().
        	get("/api/v1/hello/Bright");
    }

}
