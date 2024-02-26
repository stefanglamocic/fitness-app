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

import beans.UserBean;
import dto.Message;
import dto.User;

/**
 * Servlet implementation class RequestBay
 */
@WebServlet("/RequestBay")
public class RequestBay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestBay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        
        /*if(request.getHeader("Referer") == null || !request.getHeader("Referer").endsWith("messages.jsp"))
        	return;*/
		
        String action = request.getParameter("action");
        
        switch (action) {
        case "open-msg": 
        	openMessage(request, response);
        	break;
        case "get-user": 
        	getUser(request, response);
        	break;
        case "get-msg":
        	getMessage(request, response);
        	break;
        	default:
        		break;
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private UserBean setEnv(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(200);
		
		HttpSession session =  request.getSession();
		return (UserBean) session.getAttribute("userBean");
	}
	
	private String readRequestBody(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
		return reader.lines().collect(Collectors.joining());
	}
	
	private void openMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = setEnv(request, response);
		String source = readRequestBody(request);
		JSONObject obj = new JSONObject(source);
		userBean.openMessage(obj);
	}
	
	private void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = setEnv(request, response);
		String username = request.getParameter("username");
		User user = userBean.getUser(username);
		JSONObject obj = new JSONObject(user);
		PrintWriter writer = response.getWriter();
		writer.print(obj.toString(1));
		writer.flush();
	}
	
	private void getMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = setEnv(request, response);
		String source = readRequestBody(request);
		Message msg = userBean.getMessage(new JSONObject(source));
		JSONObject obj = new JSONObject(msg);
		PrintWriter writer = response.getWriter();
		writer.print(obj.toString(1));
		writer.flush();
	}

}
