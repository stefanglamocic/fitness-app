package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import beans.CategoryBean;
import beans.LoginResult;
import beans.UserBean;
import dao.IUserDAO;
import dao.UserDAO;
import dto.User;
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
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		
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
			return;
		}
		case "remove-user": {
			removeUser(request, response);
			return;
		}
		case "get-user": {
			sendUserData(request, response);
			return;
		}
		case "change-user": {
			changeUser(request, response);
			return;
		}
		case "user-acc": {
			activateAccount(request, response);
			return;
		}
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
		
		CategoryBean categoryBean = new CategoryBean();
		
		LoginResult loginResult = userBean.login(username, password);
		session.setAttribute("notification", loginResult.toString());
		if (loginResult == LoginResult.SUCCESS) {
			session.setAttribute("userBean", userBean);
			session.setAttribute("categoryBean", categoryBean);
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
	
	private UserBean setEnv(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(200);
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		if (userBean == null || !userBean.isLoggedIn())
			response.setStatus(401);
		
		return userBean;
	}
	
	private String readRequestBody(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
		return reader.lines().collect(Collectors.joining());
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		UserBean userBean = setEnv(request, response);
		
		User newUser = getUser(request);
		boolean success = userBean.insert(newUser);
		if (!success) {
			response.setStatus(406);
			return;
		}
		
		JSONObject obj = new JSONObject(newUser);
		PrintWriter writer = response.getWriter();
		writer.println(obj.toString(1));
		writer.flush();
	}
	
	private User getUser(HttpServletRequest request) throws IOException{
		String source = readRequestBody(request);
		JSONObject obj = new JSONObject(source);
		
		return new User(obj.getString("username"), obj.getString("password"), obj.getString("userType"), 
				obj.getString("name"), obj.getString("surname"), obj.getString("city"), obj.getString("mail"), 
					obj.has("activated") ? obj.getBoolean("activated") : true);
	}
	
	private void removeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = setEnv(request, response);
		String username = new JSONObject(
				readRequestBody(request))
				.getString("username");
		boolean success = userBean.delete(username);
		if (!success)
			response.setStatus(406);
	}
	
	private void sendUserData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = setEnv(request, response);
		String username = request.getParameter("username");
		if (username == null)
			return;
		User reqUser = userBean.getUser(username);
		PrintWriter writer = response.getWriter();
		JSONObject json = new JSONObject(reqUser);
		writer.print(json.toString(1));
		writer.flush();
	}
	
	private void changeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = setEnv(request, response);
		User user = getUser(request);
		boolean success = userBean.update(user);
		if (!success)
			response.setStatus(406);
	}
	
	private void activateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = setEnv(request, response);
		Boolean activate = Boolean.valueOf(request.getParameter("activate"));
		String username = request.getParameter("username");
		boolean success = userBean.activateAccount(username, activate);
		if (!success)
			response.setStatus(406);
	}
}
