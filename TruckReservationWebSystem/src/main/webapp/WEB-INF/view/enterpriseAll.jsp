<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Enterprise" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>企業一覧</title>

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
int authorityId = (int)request.getAttribute("authorityId");
List<Enterprise> list = (List<Enterprise>)request.getAttribute("enterpriseList");
%>


<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand">
        トラック予約Webシステム
    </a>
    
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/TruckReservationWebSystem/Index">予約一覧</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/TruckReservationWebSystem/EnterpriseAll">企業一覧</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/TruckReservationWebSystem/UserDetail">ログインユーザー詳細</a>
            </li>
        </ul>
    </div>
</nav>

<h1 class="text-center">企業一覧</h1>

<table class="table table-striped table-hover">
 
 	<tr>
		<th>企業ID</th>
		<th>企業名</th>
		<th>電話番号</th>
		<th>メールアドレス</th>
		<th>ユーザー一覧</th>
		<%
		if(authorityId == 1){
		%>
		<th>更新</th>
		<%
		}
		%>
	</tr>
	
	<%
	for(int i = 0; i < list.size(); i++){
		Enterprise enterprise = list.get(i);
		int enterpriseId = enterprise.getEnterpriseId();
		String EnterpriseName = enterprise.getEnterpriseName();
		String EnterpriseNumber = enterprise.getEnterpriseNumber();
		String EnterpriseEmail = enterprise.getEnterpriseEmail();
	%>
	
	<tr>
		<td><%=enterpriseId %></td>
		<td><%=EnterpriseName %></td>
		<td><%=EnterpriseNumber %></td>
		<td><%=EnterpriseEmail %></td>
		
		<td>
			<form action="UserAll" method="get">
				<input type="hidden" name="enterpriseId" value="<%=enterpriseId %>">
				<input type="submit" class="btn btn-warning btn-block" value="ユーザー一覧">
			</form>
		</td>
		
		<%
		if(authorityId == 1){
		%>
		<td>
			<form action="EnterpriseUpdate" method="get">
				<input type="hidden" name="enterpriseId" value="<%=enterpriseId %>">
				<input type="submit" class="btn btn-warning btn-block" value="更新">
			</form>
		</td>
		
		<%
		}
		%>
		
		<td>
		
	</tr>
	
	<%
		}	
	%>
 
</table>

<% 
if(authorityId == 1){
%>
	<div class="container">
    	<div class="row justify-content-center mt-5">
        	<div class="col-md-6 text-center">
            	<form action="EnterpriseInsert" method="get">
                	<button type="submit" class="btn btn-warning btn-block">新規企業登録</button>
            	</form>
        	</div>
    	</div>
	</div>
<% 
}
%>



</body>
</html>