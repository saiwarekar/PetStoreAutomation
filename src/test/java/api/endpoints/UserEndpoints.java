package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Create for perform Create, Read, Update, Delete requests to the user API

public class UserEndpoints {
	
	public static Response createUser(User payload) //payload means request body
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Roots.post_url);
		
		return response;
	}
	
	public static Response readUser(String userName)
	{
		Response response = given()
			.pathParam("username", userName) //in first parameter use the same variable name as you declared in "User.java" (e.g. "username" not "userName")
		.when()
			.get(Roots.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload) //payload means request body
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Roots.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
			.pathParam("username", userName)
		.when()
			.delete(Roots.delete_url);
		
		return response;
	}

}
