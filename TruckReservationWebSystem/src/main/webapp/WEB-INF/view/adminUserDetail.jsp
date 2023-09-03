<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者用ユーザー詳細</title>

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
int userId = (int)request.getAttribute("userId");
String userName = (String)request.getAttribute("userName");
String password = (String)request.getAttribute("password");
String userNumber = (String)request.getAttribute("userNumber");
String userEmail = (String)request.getAttribute("userEmail");
int enterpriseId = (int)request.getAttribute("enterpriseId");
String enterpriseName = (String)request.getAttribute("enterpriseName");
String authorityName = (String)request.getAttribute("authorityName");
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



<div class="container mt-4">
    <div class="card user-card">
        <div class="card-body">
            <h2 class="card-title">ユーザー詳細</h2>
            <h4 class="user-label">ユーザーID:<%=userId %></h4>
            <h4 class="user-label">名前:<%=userName %></h4>
            <h4 class="user-label">パスワード:<%=password %></h4>
            <h4 class="user-label">電話番号:<%=userNumber %></h4>
            <h4 class="user-label">メールアドレス:<%=userEmail %></h4>
            <h4 class="user-label">所属企業ID:<%=enterpriseId %></h4>
            <h4 class="user-label">所属企業:<%=enterpriseName %></h4>
            <h4 class="user-label">権限:<%=authorityName %></h4>
        </div>
    </div>
</div>


</body>
</html>