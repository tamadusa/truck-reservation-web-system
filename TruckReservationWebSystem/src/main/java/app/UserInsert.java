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
import entity.Enterprise;
import entity.User;
import service.Service;


public class UserInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserInsert() {
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
				
				int enterpriseId = Integer.parseInt(request.getParameter("enterpriseId"));
				request.setAttribute("enterpriseId", enterpriseId);
				
				Service service = new Service();
				Enterprise enterprise = service.getEnterprise(enterpriseId);
				String enterpriseName = enterprise.getEnterpriseName();
				request.setAttribute("entepriseName", enterpriseName);
				
				List<Authority> authorityList = service.getAuthorityAll();
				request.setAttribute("authorityList", authorityList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userInsert.jsp");
				dispatcher.forward(request, response);
				
			}else{
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/authorityError.jsp");
				dispatcher.forward(request, response);
				
			}
			
			
		}else{
			
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
				
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				String userNumber = request.getParameter("userNumber");
				String userEmail = request.getParameter("userEmail");
				int authorityId2 = Integer.parseInt(request.getParameter("authorityId"));
				int enterpriseId = Integer.parseInt(request.getParameter("enterpriseId"));
				
				User user = new User();
				user.setUserName(userName);
				user.setPassword(password);
				user.setUserNumber(userNumber);
				user.setUserEmail(userEmail);
				user.setEnterpriseId(enterpriseId);
				user.setAuthorityId(authorityId2);
				
				String urlPattern = "UserAll?enterpriseId=" + enterpriseId;
				
				Service service = new Service();
				service.insertUser(user);
				
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
