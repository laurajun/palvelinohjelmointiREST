package app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import data.Candidate;

/**
 * Servlet implementation class NewCandidate
 */
@WebServlet("/NewCandidate")
public class NewCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = "http://127.0.0.1:8080/rest/vaalikoneservice/addCandidateService";
		
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String candidatenumber=request.getParameter("candidatenumber");
		String party=request.getParameter("party");
		String streetaddress=request.getParameter("streetaddress");
		String zipcode=request.getParameter("zipcode");
		String city=request.getParameter("city");
		String why=request.getParameter("why");
		
		Candidate candidate=new Candidate(candidatenumber, party, firstname, lastname, streetaddress, zipcode, city, why);
		
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a DogBreed object as JSON string format
		Entity<Candidate> e=Entity.entity(candidate,MediaType.APPLICATION_JSON);
		
		Candidate returned=b.post(e, Candidate.class); 
		
		response.sendRedirect("/ListAllCandidates");
	}

}
