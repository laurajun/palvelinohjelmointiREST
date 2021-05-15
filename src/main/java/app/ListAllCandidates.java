package app;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import java.util.List;
import data.*;

/**
 * Servlet implementation class ListAllCandidates
 */
@WebServlet("/ListAllCandidates")
public class ListAllCandidates extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllCandidates() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = "http://127.0.0.1:8080/rest/vaalikoneservice/listAllCandidatesService";

		Client asiakas=ClientBuilder.newClient();
		WebTarget wt=asiakas.target(uri);
		Builder b=wt.request();
		
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
		
		//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<Candidate> returnedList=b.get(genericList);
	    request.setAttribute("candidatelist", returnedList);
	    RequestDispatcher rd=request.getRequestDispatcher("./jsp/listallcandidates.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
