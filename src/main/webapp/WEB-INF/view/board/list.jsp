<%@ page import="com.koreait.board.model.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.koreait.board.model.UserVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
    UserVO loginUser = (UserVO) session.getAttribute("loginUser");
    int maxPage = (int) request.getAttribute("maxPage");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<style>
    .msg {
        color: red;
    }
</style>
<body>
<h1>게시판</h1>
<% if(loginUser!=null) { %>
<div class="msg"><%=loginUser.getNm()%>(<%=loginUser.getUid()%>)님 반갑읍니다! <a href="/user/logout"><input type="button" value="로그아웃"></a></div>
<% } else { %>
<div><a href="/user/login"><input type="button" value="로그인"></a></div>
<% } %>
    <table>
        <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일시</th>
        </tr>
        <% for(BoardVO vo : list) { %>
        <tr>
            <td><%=vo.getIboard()%></td>
            <td><a href="/board/detail?iboard=<%=vo.getIboard()%>"><%=vo.getTitle()%></a></td>
            <td><%=vo.getWriterNm()%></td>
            <td><%=vo.getRdt()%></td>
        </tr>
        <% } %>

    </table>
    <div>
        <% if(loginUser!=null) { %>
        <a href="/board/write"><input type="button" value="글쓰기"></a>
        <% } %>
    </div>

    <div>
        <% for(int i=1;i<=maxPage;i++) { %>
        <span><a href="/board/list?page=<%=i%>"><%=i %></a></span>&nbsp
        <% } %>
    </div>

</body>
</html>