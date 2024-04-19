package SoapRequests;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SoapMultiplyRequest
{
	@Test
	public static void validateMultiplication() throw IOException
	{
		
		baseURI = "http://dneonline.com/";
		
		File file = new File("./SoapApi/Multiply.xml");
		FileInputStream fis = new FileInputStream(file);
		String req = IOUtils.toString(fis,"UTF-8");
		
		given().
			contentType(ContentType.XML).
			accept(ContentType.XML).
			body(req).
		when().
			post("calculator.asmx").
			then().assertThat().statusCode(200).and().log().all();
	}

}
