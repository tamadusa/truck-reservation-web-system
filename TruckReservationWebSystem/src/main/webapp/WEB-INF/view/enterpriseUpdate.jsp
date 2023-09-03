<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.Enterprise" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>企業情報更新</title>


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
Enterprise enterprise = (Enterprise)request.getAttribute("enterprise");
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
                    企業情報更新
                </div>
                <div class="card-body">
                    <form action="EnterpriseUpdate" method="post">
                        <div class="form-group">
                            <label for="userId">企業名</label>
                            <input type="text" class="form-control" id="enterpriseName" name="enterpriseName" value="<%=enterprise.getEnterpriseName() %>" placeholder="企業名を入力してください。">
                            <p id="enterpriseNameError" class="text-danger"></p>
                        </div>
                        <div class="form-group">
                            <label for="password">電話番号</label>
                            <input type="number" class="form-control" id="enterpriseNumber" name="enterpriseNumber" value="<%=enterprise.getEnterpriseNumber() %>" placeholder="電話番号を入力してください">
                            <p id="enterpriseNumberError" class="text-danger"></p>
                        </div>
                        <div class="form-group">
                            <label for="password">メールアドレス</label>
                            <input type="email" class="form-control" id="enterpriseEmail" name="enterpriseEmail" value="<%=enterprise.getEnterpriseEmail() %>" placeholder="メールアドレスを入力してください">
                            <p id="enterpriseEmailError" class="text-danger"></p>
                        </div>
                        <br>
                        <input type="hidden" name="enterpriseId" value="<%=enterprise.getEnterpriseId() %>">
                        <button type="submit" class="btn btn-warning btn-block">更新</button>
                        
                    </form>
                    
                </div>
            </div>
        </div>
    </div>
    
    
     <div class="row justify-content-center mt-3">
        <div class="col-md-6 text-center">
            <form action="EnterpriseDelete" method="get">
            	<input type="hidden" name="enterpriseId" value="<%=enterprise.getEnterpriseId() %>">
                <button type="submit" class="btn btn-danger" onclick="return enterpriseDelete();">削除</button>
            </form>
        </div>
    </div>
    
     
</div>




<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.querySelector("form");

        form.addEventListener("submit", function(event) {
            let isValid = true;

            const enterpriseNameInput = document.getElementById("enterpriseName");
            const enterpriseNumberInput = document.getElementById("enterpriseNumber");
            const enterpriseEmailInput = document.getElementById("enterpriseEmail");

            const enterpriseNameValue = enterpriseNameInput.value.trim();
            const enterpriseNumberValue = enterpriseNumberInput.value.trim();
            const enterpriseEmailValue = enterpriseEmailInput.value.trim();

            const enterpriseNameError = document.getElementById("enterpriseNameError");
            const enterpriseNumberError = document.getElementById("enterpriseNumberError");
            const enterpriseEmailError = document.getElementById("enterpriseEmailError");

            
            if (enterpriseNameValue === "") {
                enterpriseNameError.textContent = "企業名を入力してください。";
                isValid = false;
            } else if (enterpriseNameValue.length > 50) {
                enterpriseNameError.textContent = "企業名は50文字以下で入力してください。";
                isValid = false;
            } else {
                enterpriseNameError.textContent = "";
            }

            if (enterpriseNumberValue.length < 9 || enterpriseNumberValue.length > 15) {
                enterpriseNumberError.textContent = "電話番号は9文字以上15文字以下で入力してください。";
                isValid = false;
            } else {
                enterpriseNumberError.textContent = "";
            }

            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(enterpriseEmailValue)) {
                enterpriseEmailError.textContent = "正しいメールアドレスの形式で入力してください。";
                isValid = false;
            } else {
                enterpriseEmailError.textContent = "";
            }

            if (!isValid) {
                event.preventDefault(); 
            }
        });
    });
</script>



<script>
	function enterpriseDelete() {
    	return confirm("本当に削除しますか？");
	}
</script>


</body>
</html>