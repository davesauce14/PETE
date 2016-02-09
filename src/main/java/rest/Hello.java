package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello/")
public class Hello {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayWhattup(){
		return "yo what up";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayWhattupHTML(){
		return "yo what up";
	}
	
	@GET
	@Path("stuff")
	@Produces(MediaType.TEXT_HTML)
	public String sayWhattupN(){
		return "what up and stuff";
	}

}
