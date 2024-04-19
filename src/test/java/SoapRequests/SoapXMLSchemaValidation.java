package SoapRequests;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import  java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;


public class SoapXMLSchemaValidation 
{
	
	@Test
	public static void xmlSchemaValidation() throws IOException
	{
		baseURI = "http://dneonline.com/";
		
		File file = new File("./SoapApi/Subtract.xml");
		FileInputStream fis = new FileInputStream(file);
		String request = IOUtils.toString(fis,"UTF-8");
		
		given().
			contentType("text/xml").
			accept("text/xml").
			body(request).
		when().
			post("/calculator.asmx").
		then().
			assertThat().body("//*:SubtractResult.text()", equalTo("14")).
			and().assertThat().body(matchesXsdInClasspath("calc.xsd")).
			statusCode(200).
		and().log().all();
	}
}
