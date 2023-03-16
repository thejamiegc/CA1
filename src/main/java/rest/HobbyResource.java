package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HobbyDTO;
import dtos.PersonDTO;
import entities.Hobby;
import facades.HobbyFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("hobby")
public class HobbyResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final HobbyFacade FACADE =  HobbyFacade.getHobbyFacade(EMF);
    private static final PersonFacade PERSON_FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<HobbyDTO> getListOfHobbies() {
        return FACADE.getAllHobbies();

    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PersonDTO> getPeopleByHobby(@PathParam("id")long id){
        return PERSON_FACADE.getPeopleByHobby(id);
    }

    @GET
    @Path("count/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int getAmountOfPeopleByHobby(@PathParam("id")long id){
        return PERSON_FACADE.getAmountOfPeopleWithHobby(id);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createHobby(String input){
        HobbyDTO hobbyDTO = GSON.fromJson(input, HobbyDTO.class);
        System.out.println(hobbyDTO);
        FACADE.create(hobbyDTO);
        return Response.ok().entity(hobbyDTO).build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateHobby(String input,@PathParam("id")long id){
        HobbyDTO hobbyDTO = GSON.fromJson(input, HobbyDTO.class);
        System.out.println(hobbyDTO);
        HobbyDTO newHobby = FACADE.updateHobbyById(id,hobbyDTO);
        return Response.ok().entity(newHobby).build();
    }

    @PATCH
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response patchExample(String input,@PathParam("id")long id){
        HobbyDTO hobbyDTO = GSON.fromJson(input, HobbyDTO.class);
        System.out.println(hobbyDTO);
        HobbyDTO newHobby = FACADE.updateHobbyById(id,hobbyDTO);
        return Response.ok().entity(newHobby).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteHobby(@PathParam("id")long id){
        FACADE.deleteHobbyById(id);
        return Response.ok().build();
    }
}
