package httpRequests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.json.simple.*;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class PutRequestExamples 
{
	@Test
	public static void put() 
	{
		//--------------------------------------------------------------------------------------
		// Put Request for Updating user-2 with new Job and Expected Output: { updatedAt : TodaysDate and Time}
		//--------------------------------------------------------------------------------------
		
		baseURI = "https://reqres.in/";
		JSONObject request = new JSONObject();
		request.put("name", "morpheus");
		request.put("job","zion resident");
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/api/users/2").
		then().
			assertThat().
			statusCode(200).
		and().
			body("updatedAt", containsString(LocalDate.now().toString())).// Verifying LocalDate
		and().
			log().all();
	}

}
