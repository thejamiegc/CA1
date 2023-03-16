package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HobbyDTO;
import dtos.PersonDTO;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<PersonDTO> getListOfPeople() {
       return FACADE.getAll();

    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public long PersonCount(){
//        return FACADE.getPersonCount();
//    }



    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPerson(String input){
        PersonDTO personDTO = GSON.fromJson(input, PersonDTO.class);
        System.out.println(personDTO);
        FACADE.create(personDTO);
        return Response.ok().entity(personDTO).build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updatePerson(String input,@PathParam("id")long id){
        PersonDTO personDTO = GSON.fromJson(input, PersonDTO.class);
        System.out.println(personDTO);
        PersonDTO newPerson = FACADE.updatePersonById(id,personDTO);
        return Response.ok().entity(newPerson).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deletePerson(@PathParam("id")long id){
        FACADE.deletePersonByID(id);
        return Response.ok().build();
    }

}
