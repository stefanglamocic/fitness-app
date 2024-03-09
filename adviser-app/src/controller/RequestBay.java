package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
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
	private static final String TEMP_FILES_STORAGE = "/home/keks/Documents/ip/fitness-app/adviser-app/WebContent/WEB-INF/temp";
       
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
        case "get-all-msg":
        	getAllMessages(request, response);
        	break;
        case "open-msg": 
        	openMessage(request, response);
        	break;
        case "get-user": 
        	getUser(request, response);
        	break;
        case "get-msg":
        	getMessage(request, response);
        	break;
        case "reply":
        	reply(request, response);
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
	
	private void getAllMessages(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = setEnv(request, response);
		String username = request.getParameter("username");
		List<Message> messages = userBean.getMessages(username);
		JSONArray msgArray = new JSONArray(messages);
		
		PrintWriter writer = response.getWriter();
		writer.print(msgArray.toString(1));
		writer.flush();
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
	
	private Properties getMailProps() {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "mail.cock.li");
		properties.put("mail.smtp.port", "587");
		
		return properties;
	}
	
	private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
		setEnv(request, response);
		JSONObject obj = new JSONObject(readRequestBody(request));
		String username = obj.getString("from");
		String password = obj.getString("pw");
		String fromMail = username;
		String toMail = obj.getString("to");
		String fileName = obj.getString("attachmentName");
		
		//upload the file to the server into temporary storage
		File file = new File(TEMP_FILES_STORAGE, fileName);
		FileOutputStream out = new FileOutputStream(file);
		out.write(obj.getString("attachment").getBytes(StandardCharsets.ISO_8859_1));
		out.close();
		
		
		Properties props = getMailProps();
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromMail));
			msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toMail));
			msg.setSubject("Odgovor od savjetnika " + obj.getString("name"));
			msg.setText(obj.getString("message"));
			Transport.send(msg);
		} catch (AddressException e) {
			response.setStatus(401);
			e.printStackTrace();
		} catch (MessagingException e) {
			response.setStatus(502);
			e.printStackTrace();
		}
	}

}
