package app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		response.setContentType("text/html;charset=UTF-8");
				
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
        	String destPage = "index.jsp";
        	Dao dao = new Dao();
        	if(dao.CheckLogin(username,password))
            {
        	    HttpSession session = request.getSession(true);
		        session.setAttribute("username", username);
		        destPage = "start.jsp";
            }
        	else
            {
        		HttpSession session = request.getSession();
		    	String message = "Invalid username and/or password";
		        session.setAttribute("message", message);
		        destPage = "index.jsp";
            }
        	dao.close();
            response.sendRedirect(destPage);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		
	}

}
