package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1 , dataProvider="Data", dataProviderClass=DataProviders.class) //As dataprovider is in another class so we need to add class and import it
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph) //order of parameters should be same as per the excel sheet order, taking data from excel sheet
	{
		User  userPayload = new User();
		
		//Instead of faker we are getting data from excel
		userPayload.setId(Integer.parseInt(userID)); //excel data is in string so we need to convert it into integer
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndpoints.createUser(userPayload); //import from "io.restassured.response.Response;"
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String user_name) 
	{
		Response response = UserEndpoints.deleteUser(user_name);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
