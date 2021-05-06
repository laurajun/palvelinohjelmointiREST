package app;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import data.Candidate;

/**
 * Servlet implementation class UpdateCandidate
 */
@WebServlet("/UpdateCandidate")
public class UpdateCandidate extends HttpServlet {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikoneapp");
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
	    Candidate candidate=em.find(Candidate.class, id);
	    em.getTransaction().commit();
	    request.setAttribute("candidate", candidate);
	    RequestDispatcher rd=request.getRequestDispatcher("./jsp/updatecandidate.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = "http://127.0.0.1:8080/rest/vaalikoneservice/updateCandidateService";
		String id=request.getParameter("id");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String candidatenumber=request.getParameter("candidatenumber");
		String party=request.getParameter("party");
		String streetaddress=request.getParameter("streetaddress");
		String zipcode=request.getParameter("zipcode");
		String city=request.getParameter("city");
		String why=request.getParameter("why");
		
		Candidate candidate=new Candidate(id, candidatenumber, party, firstname, lastname, streetaddress, zipcode, city, why);
		
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a DogBreed object as JSON string format
		Entity<Candidate> e=Entity.entity(candidate,MediaType.APPLICATION_JSON);
		
		Candidate returned=b.post(e, Candidate.class);//We get the response as a DogBreed

		
		response.sendRedirect("/ShowSingleCandidate?id="+id);
	}

}
