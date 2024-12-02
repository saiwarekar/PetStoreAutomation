package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Create for perform Create, Read, Update, Delete requests to the user API

public class UserEndpoints2 {
	
	//Method created for getting URL's from "routes.properties" file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file. No need path. No need extension .properties
		return routes;
	}
	
	public static Response createUser(User payload) //payload means request body
	{
		String post_url = getURL().getString("post_url"); //Change 1 
		//In above .getString("post_url") -> "post_url" is a key that we created in routes.properties file so use same name here also
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url); //change 2
		
		return response;
	}
	
	public static Response readUser(String userName)
	{
		String get_url = getURL().getString("get_url");
		
		Response response = given()
			.pathParam("username", userName) //in first parameter use the same variable name as you declared in "User.java" (e.g. "username" not "userName")
		.when()
			.get(get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload) //payload means request body
	{
		String update_url = getURL().getString("update_url");
		
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
		String delete_url = getURL().getString("delete_url");
		
		Response response = given()
			.pathParam("username", userName)
		.when()
			.delete(Roots.delete_url);
		
		return response;
	}

}
