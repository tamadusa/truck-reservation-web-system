package app;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Authority;
import entity.User;
import service.Service;


public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		User userOnSession = (User)session.getAttribute("user");
		
		if(userOnSession != null) {
			
			int authorityId = userOnSession.getAuthorityId();
			
			if(authorityId == 1) {
				
				int userId = Integer.parseInt(request.getParameter("userId"));
				
				Service service = new Service();
				User user = service.getUserAdmin(userId);
				request.setAttribute("user", user);
				
				List<Authority> authorityList = service.getAuthorityAll();
				request.setAttribute("authorityList", authorityList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userUpdate.jsp");
				dispatcher.forward(request, response);
				
			}else{
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/authorityError.jsp");
				dispatcher.forward(request, response);
				
			}
			
			
			
			
			
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
			
			int authorityId = userOnSession.getAuthorityId();
			
			if(authorityId == 1) {
				
				int userId = Integer.parseInt(request.getParameter("userId"));
				int enterpriseId = Integer.parseInt(request.getParameter("enterpriseId"));
				String password = request.getParameter("password");
				String userName = request.getParameter("userName");
				String userNumber = request.getParameter("userNumber");
				String userEmail = request.getParameter("userEmail");
				int authorityId2 = Integer.parseInt(request.getParameter("authorityId"));
				
				User user = new User();
				user.setUserId(userId);
				user.setEnterpriseId(enterpriseId);
				user.setAuthorityId(authorityId2);
				user.setUserName(userName);
				user.setPassword(password);
				user.setUserNumber(userNumber);
				user.setUserEmail(userEmail);
				
				String urlPattern = "UserAll?enterpriseId=" + enterpriseId;
				
				Service service = new Service();
				service.updateUser(user);
				
				response.sendRedirect(urlPattern);
				
			}else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/authorityError.jsp");
				dispatcher.forward(request, response);
				
			}
			
			
		}else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
	}

}
