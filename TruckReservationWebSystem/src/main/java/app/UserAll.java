package app;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Enterprise;
import entity.User;
import service.Service;


public class UserAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public UserAll() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		User userOnSession = (User)session.getAttribute("user");
		
		if(userOnSession != null) {
			
			int authorityId = userOnSession.getAuthorityId();
			request.setAttribute("authorityId", authorityId);
			
			int enterpriseId = Integer.parseInt(request.getParameter("enterpriseId"));
			
			Service service = new Service();
			List<User> list = service.getUserAll(enterpriseId);
			request.setAttribute("userList", list);
			
			Enterprise enterprise = service.getEnterprise(enterpriseId);
			request.setAttribute("enterprise", enterprise);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userAll.jsp");
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
