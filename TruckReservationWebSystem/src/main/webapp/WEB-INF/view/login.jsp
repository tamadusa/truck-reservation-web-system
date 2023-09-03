<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>

<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="icon" href="image/icon.ico">
<link rel="stylesheet" href="css/bootstrap.min.css">

<style>
    body {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        margin: 0;
        background-color: #f8f9fa;
    }
    
    .navbar {
        position: absolute;
        top: 0;
        width: 100%;
    }
    
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
String loginError = (String)request.getAttribute("loginError");
%>

<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand">
        トラック予約Webシステム
    </a>
</nav>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
        <%
        if(loginError != null){
        %>
        <p class="text-center text-danger"><%=loginError %></p>
        <%
        }
        %>
            <div class="card">
                <div class="card-header bg-warning text-white">
                    ログイン
                </div>
                <div class="card-body">
                    <form action="Login" method="post">
                        <div class="form-group">
                            <label for="userId">ユーザーID</label>
                            <input type="number" class="form-control" id="userId" name="userId" placeholder="ユーザーIDを入力してください">
                            <p id="userIdError" class="text-danger"></p>
                        </div>
                        <div class="form-group">
                            <label for="password">パスワード</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="パスワードを入力してください">
                            <p id="passwordError" class="text-danger"></p>
                        </div>
                        <button type="submit" class="btn btn-warning btn-block">ログイン</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.querySelector("form");

        form.addEventListener("submit", function(event) {
            let isValid = true;

            const userIdInput = document.getElementById("userId");
            const passwordInput = document.getElementById("password");

            const userIdValue = userIdInput.value.trim();
            const passwordValue = passwordInput.value.trim();
   
            const userIdError = document.getElementById("userIdError");
            const passwordError = document.getElementById("passwordError");


            if (userIdValue.length < 1) {
            	userIdError.textContent = "ユーザーIDを入力してください。";
                isValid = false;
            } else {
            	userIdError.textContent = "";
            }

            if (!isValid) {
                event.preventDefault(); 
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

           
            

            
            
        });
    });
</script>




</body>
</html>