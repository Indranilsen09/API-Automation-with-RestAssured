package httpRequests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class DeleteRequestExample 
{
	@Test
	public static void Delete() 
	{
		
		//-------------------------------------------------------------------------
		//Deleting User-2 and getting Output status code :204
		//-------------------------------------------------------------------------
		
		
		baseURI = "https://reqres.in/";
		
		when().
			delete("/api/users/2").
		then().
			assertThat().
			statusCode(204).
		and().
			log().all();
	}

}
