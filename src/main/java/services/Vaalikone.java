package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
	// EntityManagerFactory emf create to be used later in all methods
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikoneapp");
	
		//Reading all the rows from table candidate.
		@GET
		@Path("/listAllCandidatesService")
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
		
		//This method adds new Candidate to database
		@POST
		@Path("/addCandidate")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Candidate addCandidateByParams(@FormParam("firstname") String firstname, @FormParam("lastname") String lastname, @FormParam("candidatenumber") String candidateNr, @FormParam("party") String party, @FormParam("streetaddress") String streetAddr, @FormParam("zipcode") String zipcode, @FormParam("city") String city, @FormParam("why") String notes) {
			Candidate candidate=new Candidate(candidateNr, party, firstname, lastname, streetAddr, zipcode, city, notes);
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(candidate);
			em.getTransaction().commit();
			return candidate;
		}
		
		//This method adds new Candidate to database
		@POST
		@Path("/addCandidateService")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Candidate NewCandidate(Candidate candidate) {
			//Candidate can=new Candidate(candidateNr, party, firstname, lastname, streetAddr, zipcode, city, notes);
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(candidate);
			em.getTransaction().commit();
			return candidate;
		}
		
		//This method returns the Candidate object as a JSON string
		@GET
		@Path("/getonecandidate/{candidateid}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Candidate getCandidate(@PathParam("candidateid") int id) {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			Candidate c=em.find(Candidate.class, id);
			em.getTransaction().commit();
			return c;
		}
		
		//This method returns the Candidate object as a JSON string
		@GET
		@Path("/deletecandidateservice/{candidateid}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Candidate deleteCandidate(@PathParam("candidateid") int id) {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			Candidate c=em.find(Candidate.class, id);
			if (c!=null) {
				em.remove(c);//The actual insertion line
			}
			em.getTransaction().commit();
			return c;
		}
		
		//This method updates the candidate
		@POST
		@Path("/updateCandidateService")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Candidate UpdateCandidate(Candidate candidate) {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			Candidate c=em.find(Candidate.class, candidate.getId());
			if (c!=null) {
				em.merge(candidate);//The actual update line
			}
			em.getTransaction().commit();
			return c;
		}
		
		//This method does the file upload
		@POST
		@Path("/fileupload")
		@Consumes({MediaType.MULTIPART_FORM_DATA})
		public Response uploadFile( @FormDataParam("file") InputStream fileInputStream,
	            @FormDataParam("file") FormDataContentDisposition fileMetaData,
	            @FormDataParam("id") String id,
	            @Context ServletContext sc) 
	            		throws Exception
		{
			String UPLOAD_PATH=sc.getRealPath("/images");
			String uploadfilename = fileMetaData.getFileName();
			String filetype = FilenameUtils.getExtension(uploadfilename);
			String filename = id+"."+filetype;
			System.out.println(UPLOAD_PATH);
		    try{
		        int read = 0;
		        byte[] bytes = new byte[1024];
		 
		        //OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + "/"+fileMetaData.getFileName()));
		        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + "/"+filename));
		        while ((read = fileInputStream.read(bytes)) != -1) 
		        {
		            out.write(bytes, 0, read);
		        }
		        out.flush();
		        out.close();
		        
		    } 
		    catch (IOException e){
		        throw new WebApplicationException("Error while uploading file. Please try again !!");
		    }
		    return Response.ok("Data uploaded successfully !!").build();
		}
}
