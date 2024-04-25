package DataDrivenTesting;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesRegex;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.response.*;

import io.restassured.http.ContentType;

public class DataProviderTest 
{
	@DataProvider(name="dataobj")
	public Object[][] dataforPost()
	{
		Object[][] data = new Object[2][2];
		
		data[0][0] = "Indranil Sen";
		data[0][1] = "SDET";
		
		data[1][0] = "AyushMan";
		data[1][1] = "Developer";
		
		return data;
	}
	
	@Test(dataProvider="dataobj")
	public static void testPost(String firstname, String job) 
	{
		baseURI= "https://reqres.in/";
		JSONObject req = new JSONObject();
		req.put("name",firstname);
		req.put("job",job);
		
		
				String id = given().
			contentType(ContentType.JSON). //Giving Out header(Content-Type:application/JSON) 
			accept(ContentType.JSON). //Accepting header(Content-Type:application/JSON) 
			body(req.toJSONString()).
		when().
			post("/api/users").
		then().
			statusCode(201).assertThat().body("id", matchesRegex("\\d+")).
		and().extract().path("id").toString();
				
				System.out.println("Id for "+ firstname + " is : " + id);
			
			
	}

}
