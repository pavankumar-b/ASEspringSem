

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String validLogin;
       
    public String getValidLogin() {
		return validLogin;
	}

	public void setValidLogin(String validLogin) {
		this.validLogin = validLogin;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String re=request.getParameter("isValidUser");
		String log=request.getParameter("log");
		String isValid=request.getParameter("isValidUser");
		
		HttpSession sess = request.getSession(false); //use false to use the existing session
		String user=(String)sess.getAttribute("log");
		System.out.println(isValid);
		System.out.println(user);
		if(user.equals("Success"))
		{
			System.out.println("next jsp");
			//request.getRequestDispatcher("/dashboard.jsp").forward(request,response);
			//processRequest(request, response);
		}
		
		
	}
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
	 }

}
