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
<title>予約更新</title>
</head>


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


<body>

<%
Reservation reservation = (Reservation)request.getAttribute("reservation");
List<TimePeriod> timePeriodList = (List<TimePeriod>)request.getAttribute("timePeriodList");
User user = reservation.getUser();
Enterprise fieldEnterprise = (Enterprise)request.getAttribute("fieldEnterprise");
String day = (String)request.getAttribute("day");
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





<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-warning text-white">
                    予約更新
                </div>
                <div class="card-body">
                    <form action="ReservationUpdate" method="post">
                        
                        
                        <div class="form-group">
                            <label for="timePeriodId">時間帯</label>
                            <select class="form-control" id="timePeriodId" name="timePeriodId">
                            
                            <%
                            for(int i = 0; i < timePeriodList.size(); i++){
                            	TimePeriod timePeriod = timePeriodList.get(i);
                            	int timePeriodId = timePeriod.getTimePeriodId();
                            	String timePeriodName = timePeriod.getTimePeriod();
                            %>
                          		<option value="<%=timePeriodId %>"><%=timePeriodName %></option>  	
                            <%	
                            }
                            %>
                            	
                            </select>
                            <p id="timePeriodIdError" class="text-danger"></p>
                        </div>
                        
                        
                        <div class="form-group">
                            <label for="userName">ユーザー</label>
                            <p><%=user.getUserName() %></p>
                        </div>
                        <br>
                        
                        <div class="form-group">
                            <label for="fieldEnterpriseName">行先企業</label>
                            <p><%=fieldEnterprise.getEnterpriseName() %></p>
                        </div>
                        <br>
                        
                        <div class="form-group">
                            <label for="day">予約日</label>
                            <p><%=day %></p>
                        </div>
                        <br>
                        
                        <input type="hidden" name="reservationId" value="<%=reservation.getReservationId() %>">
                        <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                        <input type="hidden" name="fieldEnterpriseId" value="<%=fieldEnterprise.getEnterpriseId() %>">
                        <input type="hidden" name="day" value="<%=day %>">
                        <button type="submit" class="btn btn-warning btn-block">更新</button>
                        
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    
    <div class="row justify-content-center mt-3">
        <div class="col-md-6 text-center">
            <form action="ReservationDelete" method="get">
				<input type="hidden" name="fieldEnterpriseId" value="<%=fieldEnterprise.getEnterpriseId() %>">
                <input type="hidden" name="day" value="<%=day %>">
            	<input type="hidden" name="reservationId" value="<%=reservation.getReservationId() %>">
                <button type="submit" class="btn btn-danger" onclick="return reservationDelete();">削除</button>
            </form>
        </div>
    </div>
    
</div>





<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.querySelector("form");

        form.addEventListener("submit", function(event) {
            let isValid = true;

            const timePeriodIdInput = document.getElementById("timePeriodId");
            

            const timePeriodIdValue = timePeriodIdInput.value.trim();
            

            const timePeriodIdError = document.getElementById("timePeriodIdError");
            
            


            if (timePeriodIdValue.length < 1) {
            	timePeriodIdError.textContent = "時間帯を選択してください。";
                isValid = false;
            } else {
            	timePeriodIdError.textContent = "";
            }

            if (!isValid) {
                event.preventDefault(); 
            }

            
            
        });
    });
</script>


<script>
	function reservationDelete() {
    	return confirm("本当に削除しますか？");
	}
</script>


</body>
</html>