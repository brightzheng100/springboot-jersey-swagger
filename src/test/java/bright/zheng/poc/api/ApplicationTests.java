package bright.zheng.poc.api;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

/**
 * Application level testing
 * 
 * @author bright.zheng
 *
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTests {
	
	@Value("${local.server.port}")
    private int port;
	
	@Before
    public void setupURL() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
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
    		get("/info").then().assertThat()
    			.body("app.name", equalTo("springboot-jersey-swagger"))
    			.body("build.version", equalTo("1.0.0-SNAPSHOT"));
    }
    
    /**
     * {"msg":"Hello Bright - application/json"}
     * 
     * @throws Exception
     */
    @Test
    public void testSayHello() {
		get("/api/v1/hello/Bright").then().assertThat()
			.body("msg", containsString("Hello Bright"));
    }
    
    /**
     * {
		"id": 10001,
		"name": "Ranga",
		"passportNumber": "E1234567"
		}
     * 
     * @throws Exception
     */
    @Test
    public void testFindStudentById() {
		get("/api/v1/student/10001").then().assertThat()
		.body("id", equalTo(10001))
		.body("name", equalTo("Ranga"))
		.body("passportNumber", equalTo("E1234567"));
    }

}
