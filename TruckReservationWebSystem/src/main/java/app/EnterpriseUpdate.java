package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Enterprise;
import entity.User;
import service.Service;


public class EnterpriseUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EnterpriseUpdate() {
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
				request.setAttribute("enterprise", enterprise);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/enterpriseUpdate.jsp");
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
				
				
				int enterpriseId = Integer.parseInt(request.getParameter("enterpriseId"));
				String enterpriseName = request.getParameter("enterpriseName");
				String enterpriseNumber = request.getParameter("enterpriseNumber");
				String enterpriseEmail = request.getParameter("enterpriseEmail");
				
				Enterprise enterprise = new Enterprise();
				enterprise.setEnterpriseId(enterpriseId);
				enterprise.setEnterpriseName(enterpriseName);
				enterprise.setEnterpriseNumber(enterpriseNumber);
				enterprise.setEnterpriseEmail(enterpriseEmail);
				
				Service service = new Service();
				service.updateEnterprise(enterprise);
				
				response.sendRedirect("EnterpriseAll");
				
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
