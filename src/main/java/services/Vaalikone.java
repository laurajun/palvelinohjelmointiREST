package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Candidate;

/*
 * URI to this service's Root Resource class is /laptopservice
 * The whole URL is http://some.server.someplace/rest/laptopservice
 * where some.server.someplace is the address to the server's web server (e.g. localhost:8080)
 * rest comes from the project web.xml file (element url-pattern)
 * laptopservice is the value of @Path below
 */
@Path("/vaalikoneservice")
public class Vaalikone {
	
	//Reading all the rows from table prey.
		@GET
		@Path("/listAllCandidates")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Candidate> readAllCandidate() {
		//Create an EntityManagerFactory with the settings from persistence.xml file
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikoneapp");
			//And then EntityManager, which can manage the entities.
			EntityManager em=emf.createEntityManager();
			
			//Read all the rows from table prey. Here the Prey must start with capital, 
			//because class's name starts. This returns a List of Prey objects.
			List<Candidate> list=em.createQuery("select a from Candidate a").getResultList();
			return list;
		}
		
		//This method uses FormParams, but does the same as previous	
		@POST
		@Path("/addCandidate")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Candidate addCandidateByParams(@FormParam("firstname") String firstname, @FormParam("lastname") String lastname, @FormParam("candidatenumber") String candidateNr, @FormParam("party") String party, @FormParam("streetaddress") String streetAddr, @FormParam("zipcode") String zipcode, @FormParam("city") String city, @FormParam("why") String notes) {
			Candidate candidate=new Candidate(candidateNr, party, firstname, lastname, streetAddr, zipcode, city, notes);
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikoneapp");
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(candidate);
			em.getTransaction().commit();
			return candidate;
		}
	
	 /*
	 * This method reveives values id, breed and weight from an html form which sends a POST type request.
	 */
//		@POST
//		@Produces(MediaType.APPLICATION_JSON)//Method returns object as a JSON string
//		@Consumes("application/x-www-form-urlencoded") //Method can receive POSTed data from a html form
//		@Path("/addcandidate")
//		public ArrayList<Candidate> addCandidateByPost(@FormParam("firstname") String firstname, @FormParam("lastname") String lastname, @FormParam("candidatenumber") String candidateNr, @FormParam("party") String party, @FormParam("streetaddress") String streetAddr, @FormParam("zipcode") String zipcode, @FormParam("city") String city, @FormParam("why") String notes) {
//			//Create a new Candidate object using parameters
//			Candidate ca=new Candidate(candidateNr, party, firstname, lastname, streetAddr, zipcode, city, notes);
//			//Read a list of Candidates (see section Example: PathParams)
//			ArrayList<Candidate> list=getCandidates();
//			//Add the new object into the beginning of the list
//			list.add(0,ca);
//			//and return the list
//			return list;
//		}
	
	/*
	 * This method can be reached with a GET (annotation @GET) type request to the URL
	 * http://some.server.someplace/rest/laptopservice/servicename
	 * ...just adding value of the @Path to the previously mentioned address
	 * Method can return plain text (@Produces...) 
	 */
	@GET
	@Path("/firstget")
	@Produces(MediaType.TEXT_PLAIN)
	public String serviceName() {
		return "This is a Vaalikone service";
	}
	
	/*
	 * This method can be reached with a GET (annotation @GET) type request to the URL
	 * http://some.server.someplace/rest/laptopservice/getonelaptop/someid
	 * where someid must be an integer.
	 * The method receives the path parameter by {laptopid} and is cast
	 * to int in method's actual parameter list @PathParam("laptopid") int id.
	 * 
	 * The implementation of this method is just to get one string from the method getLaptop,
	 * which cannot be reached directly by http URL.
	 */
	@GET
	@Path("/getonecandidate/{candidateid}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String getOneCandidate(@PathParam("candidateid") int id) {
		String s=getCandidate(id);
		return s;
	}
	
	/*
	 * Just a method for testing purposes
	 */
	public String getCandidate(int id) {
		ArrayList<String> list=new ArrayList<>();
		list.add("Paavo Väyrynen");
		list.add("Hilkka Ahde");
		list.add("Karl Marx");
		list.add("Aku Ankka");
		try {
			return list.get(id);
		}
		catch (IndexOutOfBoundsException e) {
			return "No such candidate";
		}
	}
	
	private ArrayList<Candidate> getCandidates() {
		// TODO Auto-generated method stub
		ArrayList<Candidate> list=new ArrayList<>();
		return list;
	}
}
