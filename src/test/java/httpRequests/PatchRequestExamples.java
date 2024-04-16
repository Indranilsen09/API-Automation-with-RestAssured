package httpRequests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.time.LocalDate;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PatchRequestExamples 
{
	@Test
	public static void patch() 
	{
		//--------------------------------------------------------------------------------------
		// Patch Request for Updating user-2 with new Job and Expected Output: { updatedAt : TodaysDate and Time}
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
			patch("/api/users/2").
		then().
			assertThat().
			statusCode(200).
		and().
			body("updatedAt", containsString(LocalDate.now().toString())).// Verifying LocalDate
		and().
			log().all();
	}


}
