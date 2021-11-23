<%@ page import="com.koreait.board.model.UserVO" %>
<%@ page import="com.koreait.board.model.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserVO loginUser = (UserVO) session.getAttribute("loginUser");
    String deleteMsg = (String) request.getAttribute("deleteMsg");
    BoardVO detail = (BoardVO) request.getAttribute("detail");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
    <h1>게시물 상세</h1>
    <% if(deleteMsg!=null) { %>
    <div>${requestScope.deleteMsg}</div>
    <% } %>
    <div>
        <a href="/board/detail?iboard=${requestScope.preIboard}"><input type="button" value="이전 글"></a>
        <a href="/board/detail?iboard=${requestScope.nextIboard}"><input type="button" value="다음 글"></a>
    </div>
    <input type="hidden" name="iboard" value="${requestScope.detail.iboard}">
    <div>
        제목 : ${requestScope.detail.title}
    </div>
    <div>
        작성자 : ${requestScope.detail.writerNm}
    </div>
    <div>
        작성일시 : ${requestScope.detail.rdt}
    </div>
    <div>
        내용 : ${requestScope.detail.ctnt}
    </div>
    <div>
        <% if(loginUser!=null && detail.getWriter()== loginUser.getIuser()) { %>
        <a href="/board/upd?iboard=${requestScope.detail.iboard}"><input type="button" value="수정"></a>
        <a href="/board/delete?iboard=${requestScope.detail.iboard}"><input type="button" value="삭제"></a>
        <% } %>
        <a href="/board/list"><input type="button" value="목록"></a>
    </div>

</body>
</html>