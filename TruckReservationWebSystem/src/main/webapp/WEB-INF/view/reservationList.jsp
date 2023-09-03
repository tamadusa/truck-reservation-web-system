<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Reservation" %>
<%@ page import="entity.Enterprise" %> 
<%@ page import="entity.User" %>
<%@ page import="entity.TimePeriod" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約一覧</title>

<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="icon" href="image/icon.ico">
<link rel="stylesheet" href="css/bootstrap.min.css">

<style>
.navbar {
        background-color: #ffc107;
    }
    .navbar-brand {
        font-size: 24px;
        font-weight: bold;
        color: #343a40;
    }
    .nav-link {
        color: #343a40;
    }
    .nav-link:hover {
        color: #212529;
    }
</style>


</head>
<body>


<%
String userName = (String)request.getAttribute("userName");
int authorityId = (int)request.getAttribute("authorityId");
String day = (String)request.getAttribute("day");
int fieldEnterpriseId = (int)request.getAttribute("fieldEnterpriseId");
String fieldEnterpriseName = (String)request.getAttribute("fieldEnterpriseName");
List<Reservation> myReservationList = (List<Reservation>)request.getAttribute("myReservationList");
List<Reservation> reservationAllList = (List<Reservation>)request.getAttribute("reservationAllList");
%>


<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand">
        トラック予約Webシステム
    </a>
    
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/TruckReservationWebSystem/Index">予約一覧</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/TruckReservationWebSystem/EnterpriseAll">企業一覧</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/TruckReservationWebSystem/UserDetail">ログインユーザー詳細</a>
            </li>
        </ul>
    </div>
</nav>




<h1 class="text-center">予約一覧(<%=day %>-<%=fieldEnterpriseName %>)</h1>

<br>
<% if(authorityId == 3){ %>

	<div style="text-align: left;">
    	<h4 style="margin-left: 30px;"><%=userName %>様の予約一覧</h4>
	</div>
	
	<% if(myReservationList.size() == 0){ %>
	
		<br>
		<h2 class="text-center text-danger">予約がありません</h2>
	
	<% }else{ %>
	
		<table class="table table-striped table-hover">
			
			<tr>
				<th>予約ID</th>
				<th>時間帯</th>
				<th>企業名</th>
				<th>ユーザー名</th>
				<th>最終更新日時</th>
				<th>更新</th>
				
			</tr>
			
			<%
			for(int i = 0; i < myReservationList.size(); i++){
				Reservation reservation = myReservationList.get(i);
				int reservationId = reservation.getReservationId();
				TimePeriod timePeriod = reservation.getTimePeriod();
				String timePeriodName = timePeriod.getTimePeriod();
				User user = reservation.getUser();
				String reservationUserName = user.getUserName();
				Enterprise enterprise = user.getEnterprise();
				String enterpriseName = enterprise.getEnterpriseName();
				String registrationTime = reservation.getRegistrationTime();
			%>
			
				<tr>
					<td><%=reservationId %></td>
					<td><%=timePeriodName %></td>
					<td><%=enterpriseName %></td>
					<td><%=reservationUserName %></td>
					<td><%=registrationTime %></td>
					<td>
						<form action="ReservationUpdate" method="get">
							<input type="hidden" name="reservationId" value="<%=reservationId %>">
							<input type="hidden" name="day" value="<%=day %>">
							<input type="submit" class="btn btn-warning btn-block" value="更新">
						</form>
					</td>
				</tr>
			
			<%
			}
			%>
		
		</table>
	
		
	<% } %>


<br>
<br>

	
	<div style="text-align: left;">
    	<h4 style="margin-left: 30px;">予約一覧</h4>
	</div>
	
	<% if(reservationAllList.size() == 0){ %>
		
		<br>
		<h2 class="text-center text-danger">予約がありません</h2>
	
	<% }else{ %>
	
		<table class="table table-striped table-hover">
			
			<tr>
				<th>予約ID</th>
				<th>時間帯</th>
				<th>企業名</th>
				<th>ユーザー名</th>
				<th>最終更新日時</th>
				
			</tr>
			
			<%
			for(int i = 0; i < reservationAllList.size(); i++){
				Reservation reservation = reservationAllList.get(i);
				int reservationId = reservation.getReservationId();
				TimePeriod timePeriod = reservation.getTimePeriod();
				String timePeriodName = timePeriod.getTimePeriod();
				User user = reservation.getUser();
				String reservationUserName = user.getUserName();
				Enterprise enterprise = user.getEnterprise();
				String enterpriseName = enterprise.getEnterpriseName();
				String registrationTime = reservation.getRegistrationTime();
			%>
			
				<tr>
					<td><%=reservationId %></td>
					<td><%=timePeriodName %></td>
					<td><%=enterpriseName %></td>
					<td><%=reservationUserName %></td>
					<td><%=registrationTime %></td>
				</tr>
			
			<%
			}
			%>
		
		</table>
	
	
	
		
	<% } %>
	
	
	
	
	
		<div class="container">
    		<div class="row justify-content-center mt-5">
        		<div class="col-md-6 text-center">
            		<form action="ReservationInsert" method="get">
            			<input type="hidden" name="day" value="<%=day %>">
            			<input type="hidden" name="fieldEnterpriseId" value="<%=fieldEnterpriseId %>">
                		<button type="submit" class="btn btn-warning btn-block">新規予約登録</button>
            		</form>
        		</div>
    		</div>
		</div>
		
		
	

<% }else{ %>

	<div style="text-align: left;">
    	<h4 style="margin-left: 30px;">予約一覧</h4>
	</div>
	
	<% if(reservationAllList.size() == 0){ %>
		
		<br>
		<h2 class="text-center text-danger">予約がありません</h2>
	
	<% }else{ %>
	
		<table class="table table-striped table-hover">
			
			<tr>
				<th>予約ID</th>
				<th>時間帯</th>
				<th>企業名</th>
				<th>ユーザー名</th>
				<th>最終更新日時</th>
				
			</tr>
			
			<%
			for(int i = 0; i < reservationAllList.size(); i++){
				Reservation reservation = reservationAllList.get(i);
				int reservationId = reservation.getReservationId();
				TimePeriod timePeriod = reservation.getTimePeriod();
				String timePeriodName = timePeriod.getTimePeriod();
				User user = reservation.getUser();
				String reservationUserName = user.getUserName();
				Enterprise enterprise = user.getEnterprise();
				String enterpriseName = enterprise.getEnterpriseName();
				String registrationTime = reservation.getRegistrationTime();
			%>
			
				<tr>
					<td><%=reservationId %></td>
					<td><%=timePeriodName %></td>
					<td><%=enterpriseName %></td>
					<td><%=reservationUserName %></td>
					<td><%=registrationTime %></td>
				</tr>
			
			<%
			}
			%>
		
		</table>
	
		
	<% } %>
	

<% } %>

</body>
</html>