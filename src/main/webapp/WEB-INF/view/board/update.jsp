<%@ page import="com.koreait.board.model.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String updErr = (String) request.getAttribute("updErr");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
<h1>글 수정</h1>
<% if(updErr!=null) { %>
<div>${requestScope.updErr}</div>
<% } %>
<form action="/board/upd?iboard=${requestScope.detail.iboard}" method="post">
    <div>
        제목 : <input type="text" name="title" value="${requestScope.detail.title}"  placeholder="제목을 입력하세요.">
    </div>
    <div>
        내용 : <textarea name="ctnt" cols="30" rows="10">${requestScope.detail.ctnt}</textarea>
    </div>
    <div>
        <input type="submit" value="작성">
        <a href="/board/list"><input type="button" value="취소"></a>
    </div>

</form>
</body>
</html>
