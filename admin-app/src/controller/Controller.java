package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import beans.LoginResult;
import beans.UserBean;
import dao.IUserDAO;
import dao.UserDAO;
import util.IPages;

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
		String path = IPages.LOGIN;
		String action = request.getParameter("action") == null ? "" : request.getParameter("action");
		
		switch (action) {
		case "login": {
			path = login(request);
			session.setAttribute("page", "first");
		}
			break;
		case "logout": {
			session.invalidate();
		}
			break;
		case "": 
			break;
		case "add-user": {
			addUser(request, response);
		}
			break;
		default: {
			path = pageSwitch(session, action);
		}
			break;
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		IUserDAO userDAO = new UserDAO();
		UserBean userBean = new UserBean(userDAO);
		
		LoginResult loginResult = userBean.login(username, password);
		session.setAttribute("notification", loginResult.toString());
		if (loginResult == LoginResult.SUCCESS) {
			session.setAttribute("userBean", userBean);
			return IPages.MAIN;
		}
		return IPages.LOGIN;
	}
	
	private String pageSwitch(HttpSession session, String action) {
		String path = IPages.LOGIN;
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		if (userBean != null && userBean.isLoggedIn()) {
			switch (action) {
			case "users": {
				path = IPages.USERS;
				session.setAttribute("page", "second");
			}
				break;
			case "stats": {
				path = IPages.STATS;
				session.setAttribute("page", "third");
			}
				break;
				default: {
					path = IPages.MAIN;
					session.setAttribute("page", "first");
				}
			}
		}
		return path;
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		/*
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		if (userBean == null || !userBean.isLoggedIn())
			return;*/
		
		JSONObject obj = new JSONObject();
		obj.put("test", "hello world");
		
		PrintWriter writer = response.getWriter();
		writer.println(obj.toString(1));
	}
}
