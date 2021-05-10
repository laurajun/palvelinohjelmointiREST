package app;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;

import data.Candidate;

/**
 * Servlet implementation class ShowSingleCandidate
 */
@WebServlet("/ShowSingleCandidate")
public class ShowSingleCandidate extends HttpServlet implements Servlet {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikoneapp");
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSingleCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/vaalikoneservice/getonecandidate/"+id;
			
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Candidate candidate=b.get(Candidate.class);
		String s=b.get(String.class);
	    request.setAttribute("candidate", candidate);
	    RequestDispatcher rd=request.getRequestDispatcher("./jsp/showsinglecandidate.jsp");
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
