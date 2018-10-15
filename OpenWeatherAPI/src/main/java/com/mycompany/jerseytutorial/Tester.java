/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseytutorial;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam; 
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
					
@Path("/weather") 
public class Tester {
					
  @GET
  @Path("/{param}")
  public Response sayHelloWorld(@PathParam("param") String message) {
					
    String output = "Weather in " + message + " :  " +weatherResponse(message);
					
    return Response.status(200).entity(output).build(); 
  }
  
  public String weatherResponse(String city){
        Client client=ClientBuilder.newClient();
        String name = client.target("http://api.openweathermap.org/data/2.5/forecast?q="+city+"&mode=json&appid=3f9c27e7c5fabd691ebf9198721dfdd1")
        .request(MediaType.APPLICATION_JSON)
        .get(String.class);
        System.out.println(name);
        client.close() ;
        return name;
          }

}

