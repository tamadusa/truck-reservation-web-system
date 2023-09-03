<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Enterprise" %>
<%@ page import="entity.Authority" %>
<%@ page import="entity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報更新</title>

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
User user = (User)request.getAttribute("user");
Enterprise enterprise = user.getEnterprise();
int enterpriseId = enterprise.getEnterpriseId();
String entepriseName = enterprise.getEnterpriseName();
List<Authority> authorityList = (List<Authority>)request.getAttribute("authorityList");
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



<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-warning text-white">
                    ユーザー情報更新(<%=entepriseName %>)
                </div>
                <div class="card-body">
                    <form action="UserUpdate" method="post">
                        <div class="form-group">
                            <label for="userId">ユーザー名</label>
                            <input type="text" class="form-control" id="userName" name="userName" value="<%=user.getUserName() %>" placeholder="ユーザー名を入力してください。">
                            <p id="userNameError" class="text-danger"></p>
                        </div>
                        
                        <div class="form-group">
                            <label for="password">パスワード</label>
                            <input type="password" class="form-control" id="password" name="password" value="<%=user.getPassword() %>" placeholder="パスワードを入力してください。">
                            <p id="passwordError" class="text-danger"></p>
                        </div>
                        
                        <div class="form-group">
                            <label for="userNumber">電話番号</label>
                            <input type="number" class="form-control" id="userNumber" name="userNumber" value="<%=user.getUserNumber() %>" placeholder="電話番号を入力してください">
                            <p id="userNumberError" class="text-danger"></p>
                        </div>
                        
                        <div class="form-group">
                            <label for="userEmail">メールアドレス</label>
                            <input type="email" class="form-control" id="userEmail" name="userEmail" value="<%=user.getUserEmail() %>" placeholder="メールアドレスを入力してください">
                            <p id="userEmailError" class="text-danger"></p>
                        </div>
                        
                        <div class="form-group">
                            <label for="authorityId">権限</label>
                            <select class="form-control" id="authorityId" name="authorityId">
                            
                            <%
                            for(int i = 0; i < authorityList.size(); i++){
                            	Authority authority = authorityList.get(i);
                            	int authorityId = authority.getAuthorityId();
                            	String authorityName = authority.getAuthorityName();
                            %>
                          		<option value="<%=authorityId %>"><%=authorityName %></option>  	
                            <%	
                            }
                            %>
                            	
                            </select>
                            <p id="authorityIdError" class="text-danger"></p>
                        </div>
                        
                        <div class="form-group">
                            <label for="enterpriseName">企業名</label>
                            <p><%=entepriseName %></p>
                        </div>
                        
                        <br>
                        
                        <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                        <input type="hidden" name="enterpriseId" value="<%=enterpriseId %>">
                        <button type="submit" class="btn btn-warning btn-block">更新</button>
                        
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    
    
     <div class="row justify-content-center mt-3">
        <div class="col-md-6 text-center">
            <form action="UserDelete" method="get">
            	<input type="hidden" name="enterpriseId" value="<%=enterpriseId %>">
            	<input type="hidden" name="userId" value="<%=user.getUserId() %>">
                <button type="submit" class="btn btn-danger" onclick="return userDelete();">削除</button>
            </form>
        </div>
    </div>
    
    
    
    
</div>



<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.querySelector("form");

        form.addEventListener("submit", function(event) {
            let isValid = true;

            const userNameInput = document.getElementById("userName");
            const passwordInput = document.getElementById("password");
            const userNumberInput = document.getElementById("userNumber");
            const userEmailInput = document.getElementById("userEmail");
            const authorityIdInput = document.getElementById("authorityId");

            const userNameValue = userNameInput.value.trim();
            const passwordValue = passwordInput.value.trim();
            const userNumberValue = userNumberInput.value.trim();
            const userEmailValue = userEmailInput.value.trim();
            const authorityIdValue = authorityIdInput.value.trim();

            const userNameError = document.getElementById("userNameError");
            const passwordError = document.getElementById("passwordError");
            const userNumberError = document.getElementById("userNumberError");
            const userEmailError = document.getElementById("userEmailError");
            const authorityIdError = document.getElementById("authorityIdError");

            
            if (userNameValue === "") {
            	userNameError.textContent = "ユーザー名を入力してください。";
                isValid = false;
            } else if (userNameValue.length > 50) {
            	userNameError.textContent = "ユーザー名は50文字以下で入力してください。";
                isValid = false;
            } else {
            	userNameError.textContent = "";
            }

            if (passwordValue === "") {
            	passwordError.textContent = "パスワードを入力してください。";
                isValid = false;
            } else if (passwordValue.length > 50) {
            	passwordError.textContent = "パスワードは50文字以下で入力してください。";
                isValid = false;
            } else {
            	passwordError.textContent = "";
            }

            if (userNumberValue.length < 9 || userNumberValue.length > 15) {
            	userNumberError.textContent = "電話番号は9文字以上15文字以下で入力してください。";
                isValid = false;
            } else {
            	userNumberError.textContent = "";
            }

            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(userEmailValue)) {
            	userEmailError.textContent = "正しいメールアドレスの形式で入力してください。";
                isValid = false;
            } else {
            	userEmailError.textContent = "";
            }


            if (authorityIdValue.length < 1) {
            	authorityIdError.textContent = "権限を入力してください。";
                isValid = false;
            } else {
            	authorityIdError.textContent = "";
            }

            if (!isValid) {
                event.preventDefault(); 
            }

            
            
        });
    });
</script>


<script>
	function userDelete() {
    	return confirm("本当に削除しますか？");
	}
</script>



</body>
</html>