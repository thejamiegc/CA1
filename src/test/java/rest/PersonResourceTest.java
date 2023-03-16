package rest;

import com.google.gson.Gson;
import dtos.HobbyDTO;
import dtos.PersonDTO;
import entities.Hobby;
import entities.Person;
import io.restassured.http.ContentType;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class PersonResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    private static Person person1, person2;


    private static Hobby hobby1, hobby2;

    private static PersonDTO personDTO1, personDTO2;

    private static HobbyDTO hobbyDTO1;

    private static Set<Hobby> hobbySet1, hobbySet2, hobbySet3;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private static Gson gson = new Gson();



    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        hobby1 = new Hobby("bullet journalling","link","sjov","pas");
        hobby2 = new Hobby("Gaming","link","Entertainment","At home");
        Set<Hobby> hobbySet1 = new LinkedHashSet<>();
        Set<Hobby> hobbySet2 = new LinkedHashSet<>();
        hobbySet1.add(hobby1);
        hobbySet2.add(hobby2);

        person1 = new Person("Hans","Oge","mail@mail.dk","male","forever single",hobbySet1);
        person2 = new Person("Molly","Fisk","mail2@mail.dk","female","forever not single",hobbySet2);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.persist(hobby1);
            em.persist(hobby2);
            em.persist(person1);
            em.persist(person2);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/person").then().statusCode(200);
    }


    @Test
    public void personCountTest() throws Exception {
        given()
                .contentType("application/json")
                .get("/person/count").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("count", equalTo(2));
    }

    @Test
    public void createPerson(){
        Person person = new Person("Zachary","Bloblington","Letsbounce@breakfast.com","unknown","singlepringle",hobbySet2);
        System.out.println("PERSON ENTITY: "+ person);
        PersonDTO personDTO = new PersonDTO(person);
        System.out.println("PERSON DTO: "+ personDTO);
        String requestBody = gson.toJson(personDTO);
        System.out.println("REQUEST: " + requestBody);
        given()
                .header("Content-type",ContentType.JSON)
                .body(requestBody)
                .post("/person")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("id", equalTo(person.getId())) //TODO : ASK TEACHERS ABOUT THIS ON FRIDAY ASWELL AS CONSTRUCTOR IN PERSONDTO !!!
                .body("firstname", equalTo(person.getFirstname()));
        System.out.println(gson.toJson(person));
    }

    @Test
    public void editPerson(){
        person2.setGender("Unicorn apache helicopter mix");
        PersonDTO personDTO = new PersonDTO(person2);
        String requestBody = gson.toJson(personDTO);
        given()
                .header("Content-type",ContentType.JSON)
                .body(requestBody)
                .put("/person/{id}",person2.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("firstname", equalTo(person2.getFirstname()));
        System.out.println(gson.toJson(person2));

    }



    @Test
    public void getAllPeople(){
        given()
                .contentType(ContentType.JSON)
                .get("/person")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()",equalTo(2));
    }




}
