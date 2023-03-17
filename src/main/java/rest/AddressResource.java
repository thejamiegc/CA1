package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;
import facades.AddressFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("address")
public class AddressResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final AddressFacade FACADE =  AddressFacade.getAddressFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //TODO : DOES NOT WORK CURRENTLY DUE TO DATABASE BEING FAULTY - PERSON DOESNT HAVE ADDRESS.

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createAddress(String input){
        AddressDTO addressDTO = GSON.fromJson(input, AddressDTO.class);
        System.out.println(addressDTO);
        FACADE.createAddress(addressDTO);
        return Response.ok().entity(addressDTO).build();
    }
}
