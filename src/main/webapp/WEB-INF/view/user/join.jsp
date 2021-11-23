<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String err = (String) request.getAttribute("err");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
<h1>회원가입</h1>
<% if(err!=null) { %>
<div>${requestScope.err}</div>
<% } %>
<form action="/user/join" method="post">
    <div>
        아이디 : <input type="text" name="uid">
    </div>
    <div>
        비밀번호 : <input type="password" name="upw">
    </div>
    <div>
        별명 : <input type="text" name="nm">
    </div>
    <div>
        성별 : <label>남자 <input type="radio" name="gender" value="1" checked></label>
        <label>여자 <input type="radio" name="gender" value="0"></label>
    </div>
    <div>
        <input type="submit" value="회원가입">
        <input type="button" value="로그인 화면으로">
    </div>
</form>
</body>
</html>