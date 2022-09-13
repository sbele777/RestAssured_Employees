package com.employee.TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC004_PUT_UpdateRecord extends TestBase {

	@BeforeClass
	void postEmployeeRecord()
	{
		logger.info("---TC004 Update Employee Record---");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/";
		request = RestAssured.given();
		
		JSONObject reqPar = new JSONObject();
		reqPar.put("name","Ismail");
		reqPar.put("salary","5000");
		reqPar.put("age","24");
		
		request.header("Content-type","application/json");
		request.body(reqPar.toJSONString());
		
		response = request.request(Method.PUT,"update/21");
		
	}
	
	@Test 
	void checkStatusCode()
	{
		logger.info("---Checking Status Code---");
		int code = response.getStatusCode();
		logger.info("Status code: "+code);
		Assert.assertEquals(code, 200);
	}
	
	@Test 
	void checkStatusLine()
	{
		logger.info("---Checking Status Code---");
		String statusLine = response.getStatusLine();
		logger.info("Status Line: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test 
	void checkResponseBody()
	{
		logger.info("---Checking Status body---");
		String body = response.getBody().asString();
		logger.info("Body: "+body);
		Assert.assertEquals(body.contains("Ismail"), true);
		Assert.assertEquals(body.contains("5000"), true);
		Assert.assertEquals(body.contains("24"), true);
	}
	
	@Test
	void checkServerType()
	{
		logger.info("---Checking Server Type---");
		String server_type = response.header("Server");
		logger.info("Server Type: "+server_type);
		Assert.assertEquals(server_type, "nginx");
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("---Checking Content Encoding---");
		String encoding = response.header("Content-Encoding");
		logger.info("Content Encoding: "+encoding);
		Assert.assertEquals(encoding, "gzip");
		
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("---Checking Response Time---");
		long time = response.getTime();
		logger.info("Response Time: "+time);
		Assert.assertTrue(time<5000);
		
	}
	
	@Test
	void tearDown()
	{
		logger.info("---Finished TC004 ---");
	}
}
