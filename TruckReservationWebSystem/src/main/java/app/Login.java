package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.Service;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Login() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		User userOnSession = (User)session.getAttribute("user");
		
		if(userOnSession != null) {
			response.sendRedirect("Index");
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		User userOnSession = (User)session.getAttribute("user");
		
		if(userOnSession != null) {
			
			response.sendRedirect("Index");
			
		}else {
			
			try {
			
				int userId = Integer.parseInt(request.getParameter("userId"));
				String password = request.getParameter("password");
			
				Service service = new Service();
				User user = service.getUser(userId, password);
			
				if(user.getUserName() != null) {
					session.setAttribute("user", user);
					response.sendRedirect("Index");
				}else {
					request.setAttribute("loginError", "ログイン認証に失敗しました");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
					dispatcher.forward(request, response);
				}
			
			}catch(NumberFormatException e) {
				request.setAttribute("loginError", "ログイン認証に失敗しました");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				dispatcher.forward(request, response);
			}
			
		}

		
	}

}
