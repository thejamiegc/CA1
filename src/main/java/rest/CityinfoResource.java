package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;
import dtos.CityinfoDTO;
import dtos.HobbyDTO;
import dtos.PersonDTO;
import facades.AddressFacade;
import facades.CityinfoFacade;
import facades.HobbyFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("cityinfo")
public class CityinfoResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CityinfoFacade FACADE =  CityinfoFacade.getCityinfoFacade(EMF);
    private static final HobbyFacade HOBBY_FACADE =  HobbyFacade.getHobbyFacade(EMF);
    private static final PersonFacade PERSON_FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Path("address")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<CityinfoDTO> getListOfHobbies() {
        return FACADE.getAllCities();

    }




}


