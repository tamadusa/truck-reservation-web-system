<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Enterprise" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約検索</title>

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
List<Enterprise> enterpriseList = (List<Enterprise>)request.getAttribute("enterpriseList");
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
                    予約検索
                </div>
                <div class="card-body">
                    <form action="ReservationList" method="get">
                    
                        <div class="form-group">
                            <label for="day">日付</label>
                            <input type="date" class="form-control" id="day" name="day" value="<%= java.time.LocalDate.now() %>">
                            
                        </div>
                        
                        
                        <div class="form-group">
                            <label for="enterpriseId">会社名</label>
                            <select class="form-control" id="enterpriseId" name="enterpriseId">
                            
                            <%
                            for(int i = 0; i < enterpriseList.size(); i++){
                            	Enterprise enterprise = enterpriseList.get(i);
                            	int enterpriseId = enterprise.getEnterpriseId();
                            	String enterpriseName = enterprise.getEnterpriseName();
                            %>
                          		<option value="<%=enterpriseId %>"><%=enterpriseName %></option>  	
                            <%	
                            }
                            %>
                            	
                            </select>
                            
                        </div>
                        
        
                        
                        <button type="submit" class="btn btn-warning btn-block">検索</button>
                        
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



</body>
</html>