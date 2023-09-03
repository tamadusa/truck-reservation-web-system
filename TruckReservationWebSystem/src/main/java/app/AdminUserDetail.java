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
import service.Service;

public class AdminUserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminUserDetail() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		User userOnSession = (User)session.getAttribute("user");
		
		if(userOnSession != null) {
			
			try{
				
				int authorityId = userOnSession.getAuthorityId();
				
				if(authorityId == 1) {
					
					int userId = Integer.parseInt(request.getParameter("userId"));
					
					Service service = new Service();
					User user = service.getUserAdmin(userId);
					
					request.setAttribute("userId", userId);
					
					String userName = user.getUserName();
					request.setAttribute("userName", userName);
					
					String password = user.getPassword();
					request.setAttribute("password", password);
					
					String userNumber = user.getUserNumber();
					request.setAttribute("userNumber", userNumber);
					
					String userEmail = user.getUserEmail();
					request.setAttribute("userEmail", userEmail);
					
					int enterpriseId = user.getEnterpriseId();
					request.setAttribute("enterpriseId", enterpriseId);
					
					Enterprise enterprise = user.getEnterprise();
					String enterpriseName = enterprise.getEnterpriseName();
					request.setAttribute("enterpriseName", enterpriseName);
					
					Authority authority = user.getAuthority();
					String authorityName = authority.getAuthorityName();
					request.setAttribute("authorityName", authorityName);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/adminUserDetail.jsp");
					dispatcher.forward(request, response);
					
				}else{
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/authorityError.jsp");
					dispatcher.forward(request, response);
					
				}

			}catch(NumberFormatException e) {

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/404.jsp");
				dispatcher.forward(request, response);

			}
			
			
		}else{
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
