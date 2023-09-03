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
import entity.TimePeriod;
import entity.User;
import service.Service;


public class ReservationUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReservationUpdate() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		User userOnSession = (User)session.getAttribute("user");
		
		if(userOnSession != null) {
			
			int authorityId = userOnSession.getAuthorityId();
			
			if(authorityId == 3) {
				
				
				int reservationId = Integer.parseInt(request.getParameter("reservationId"));
				
				Service service = new Service();
				Reservation reservation = service.getReservation(reservationId);
				int fieldEnterpriseId = reservation.getFieldEnterpriseId();
				Enterprise fieldEnterprise = service.getEnterprise(fieldEnterpriseId);
				request.setAttribute("fieldEnterprise", fieldEnterprise);
				
				request.setAttribute("reservation", reservation);
				
				String day = request.getParameter("day");
				request.setAttribute("day", day);
				
				List<TimePeriod> timePeriodList = service.getTimePeriodAll();
				request.setAttribute("timePeriodList", timePeriodList);
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reservationUpdate.jsp");
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
			
			if(authorityId == 3) {
				
				int reservationId = Integer.parseInt(request.getParameter("reservationId"));
				int timePeriodId = Integer.parseInt(request.getParameter("timePeriodId"));
				int userId = Integer.parseInt(request.getParameter("userId"));
				int fieldEnterpriseId = Integer.parseInt(request.getParameter("fieldEnterpriseId"));
				String day = request.getParameter("day");
				day = changeDateFormat(day);
				
				Reservation reservation = new Reservation();
				reservation.setReservationId(reservationId);
				reservation.setTimePeriodId(timePeriodId);
				reservation.setUserId(userId);
				reservation.setFieldEnterpriseId(fieldEnterpriseId);
				reservation.setReservationDay(day);
				
				String urlPattern = "ReservationList?day=" + day + "&enterpriseId=" + fieldEnterpriseId;
				
				Service service = new Service();
				service.updateReservation(reservation);
				
				response.sendRedirect(urlPattern);
				
				
			}else{
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/authorityError.jsp");
				dispatcher.forward(request, response);
				
			}
			
			
		}else{
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}
	
	
	private String changeDateFormat(String inputDateStr) {
		
		 SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
	     SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
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
