package com.process;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.Gson;

@Path("/processOrder")
public class Orchestrator {

	@GET
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{custId}/{custCardNo}")
	
	public Response processOrder(@PathParam("custId") String custId, @PathParam("custCardNo") String cardNo) throws JSONException{
		WebTarget webTarget = ClientBuilder.newClient().target("sumita's service" + custId + "/" + cardNo);
		//Response result = webTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(""), Response.class);
		String result = webTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(""), String.class);
		
		Gson gson = new Gson();
        Validation validationStatus = gson.fromJson(result, Validation.class);
        return Response.status(200).entity(validationStatus.getMessage()).build();
		
	}
	
}
