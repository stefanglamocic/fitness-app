package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LoginResult;
import beans.UserBean;
import dao.IUserDAO;
import dao.UserDAO;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String path = "/WEB-INF/login.jsp";
		String action = request.getParameter("action");
		
		if("login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			IUserDAO userDAO = new UserDAO();
			UserBean userBean = new UserBean(userDAO);
			
			LoginResult loginResult = userBean.login(username, password);
			session.setAttribute("notification", loginResult.toString());
			if (loginResult == LoginResult.SUCCESS) {
				session.setAttribute("userBean", userBean);
				path = "/WEB-INF/main.jsp";
			}
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
}
