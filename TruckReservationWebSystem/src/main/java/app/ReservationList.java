package app;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Enterprise;
import entity.Reservation;
import entity.User;
import service.Service;


public class ReservationList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReservationList() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		User userOnSession = (User)session.getAttribute("user");
		
		if(userOnSession != null) {
			
			int userId = userOnSession.getUserId();
			String userName = userOnSession.getUserName();
			request.setAttribute("userName", userName);
			int authorityId = userOnSession.getAuthorityId();
			request.setAttribute("authorityId", authorityId);
			
			Service service = new Service();
			
			String day = request.getParameter("day");
			int fieldEnterpriseId = Integer.parseInt(request.getParameter("enterpriseId"));
			request.setAttribute("fieldEnterpriseId", fieldEnterpriseId);
			Enterprise enterprise = service.getEnterprise(fieldEnterpriseId);
			String fieldEnterpriseName = enterprise.getEnterpriseName();
			request.setAttribute("fieldEnterpriseName", fieldEnterpriseName);
			
			List<Reservation> myReservationList = null;
			if(authorityId == 3) {
				myReservationList = service.getMyReservation(userId, fieldEnterpriseId, day);
			}
			request.setAttribute("myReservationList", myReservationList);
			
			List<Reservation> reservationAllList = service.getReservationAll(fieldEnterpriseId, day);
			request.setAttribute("reservationAllList", reservationAllList);
			
			day = changeDateFormat(day);
			request.setAttribute("day", day);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reservationList.jsp");
			dispatcher.forward(request, response);
		
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doGet(request,response);
		
		
		
	}
	
	
	private String changeDateFormat(String inputDateStr) {
		
		 SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	     SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
	     String outputDateStr = null;

	     try {
	         Date date = inputFormat.parse(inputDateStr);
	         outputDateStr = outputFormat.format(date);
	     } catch (ParseException e) {
	         e.printStackTrace();
	     }
	     
	     return outputDateStr;
		
	}
	
	


}
