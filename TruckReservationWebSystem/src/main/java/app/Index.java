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


public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Index() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		User userOnSession = (User)session.getAttribute("user");
		
		if(userOnSession != null) {
			
			Service service = new Service();
			List<Enterprise> enterpriseList = service.getEenterpriseAll();
			request.setAttribute("enterpriseList", enterpriseList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
			dispatcher.forward(request, response);
		
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doGet(request,response);
		
		
		
	}
	
	
	

}
