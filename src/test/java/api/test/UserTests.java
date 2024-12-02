package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	public Logger logger; //change 1 for log4j, import from package "import org.apache.logging.log4j.Logger;"
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode()); //we are generating our own ID. API is not creating ID here
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		//logs //change 2 for log4j
		logger = LogManager.getLogger(this.getClass());
		logger.debug("debugging.......");
	}
	
	@Test(priority=1)
	public void testPostUser() 
	{
		logger.info("********** Creating User **********");
		Response response = UserEndpoints.createUser(userPayload); //import from "io.restassured.response.Response;"
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User Created **********");
	}
	
	@Test(priority=2)
	public void testGetUserByName() 
	{
		logger.info("********** Reading User Info **********");
		Response response = UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); //Check in postman first and then add validations
		logger.info("********** User Info Displayed **********");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() 
	{
		logger.info("********** Updating User **********");
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		//First parameter: we use "this.userPayload.getUsername()" to get username which was created in "testPostUser" method.
		//Second parameter: we use just userPayload so we will get the updated firstName, lastName and email in current method
		response.then().log().all();
		response.then().log().body().statusCode(200); //direct validation (chai assertion)
		Assert.assertEquals(response.getStatusCode(), 200); //TestNG Validation (TestNG Assertion)
		
		logger.info("********** User is Updated **********");
		//Checking data after update
		Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() 
	{
		logger.info("********** Deleting User **********");
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User Deleted **********");
	}

}
