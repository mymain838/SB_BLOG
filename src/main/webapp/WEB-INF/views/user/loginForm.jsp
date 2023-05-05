<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form action="/auto/loginProc" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" name="username" placeholder="Enter username" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" name="password" placeholder="Enter password" id="password">
        </div>
      <%--  <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" name="remember" type="checkbox"> Remember me
            </label>
        </div>--%>
        <button id="btn-login" class="btn btn-primary"> 로그인 </button>
        <%!
            final String CLIENT_ID = "7b8954aeef8a543f6db2c9aadfa30d18";
            final String REDIRECT_URI = "http://localhost:8000/auth/kakao/callback";
        %>

        <a href="https://kauth.kakao.com/oauth/authorize?client_id=<%=CLIENT_ID%>&redirect_uri=<%=REDIRECT_URI%>&response_type=code"><img height="35px" src="/image/kakao_login_button.png"/></a>
    </form>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>




