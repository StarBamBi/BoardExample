<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>

<body>
<h1>로그인</h1>
<form action="/user/login" method="post">
    <table>
        <tr>
            <td colspan="2">아이디 : <input type="text" name="uid" value="h177"></td>
        </tr>
        <tr>
            <td colspan="2">비밀번호 : <input type="password" name="upw" value="1234"></td>
        </tr>
        <tr>
            <td><input type="submit" value="로그인">
            </td>
            <td>
                <a href="/user/join">회원가입</a>
            </td>

        </tr>
    </table>
</form>
</body>
</html>