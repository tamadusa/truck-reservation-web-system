package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Authority;
import entity.Enterprise;
import entity.User;


public class UserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UserDetail() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		User userOnSession = (User)session.getAttribute("user");
		
		if(userOnSession != null) {
			
			int userId = userOnSession.getUserId();
			request.setAttribute("userId", userId);
			
			String userName = userOnSession.getUserName();
			request.setAttribute("userName", userName);
			
			String userNumber = userOnSession.getUserNumber();
			request.setAttribute("userNumber", userNumber);
			
			String userEmail = userOnSession.getUserEmail();
			request.setAttribute("userEmail", userEmail);
			
			int enterpriseId = userOnSession.getEnterpriseId();
			request.setAttribute("enterpriseId", enterpriseId);
			
			Enterprise enterprise = userOnSession.getEnterprise();
			String enterpriseName = enterprise.getEnterpriseName();
			request.setAttribute("enterpriseName", enterpriseName);
			
			Authority authority = userOnSession.getAuthority();
			String authorityName = authority.getAuthorityName();
			request.setAttribute("authorityName", authorityName);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userDetail.jsp");
			dispatcher.forward(request, response);
			
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
