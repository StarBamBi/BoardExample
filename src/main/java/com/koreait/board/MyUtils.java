package com.koreait.board;

import com.koreait.board.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyUtils {
    public static void disForward(HttpServletRequest req, HttpServletResponse res, String jsp) throws ServletException, IOException {
        String path = "/WEB-INF/view/" + jsp + ".jsp";
        req.getRequestDispatcher(path).forward(req,res);
    }

    public static int parseStringToInt(String str) {
        return parseStringToInt(str, 0);
    }

    public static int parseStringToInt(String str, int defVal) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {}
        return defVal;
    }

    public static int getParameterInt(HttpServletRequest req, String key) {
        return parseStringToInt(req.getParameter(key));
    }

    public static int getParameterInt(HttpServletRequest req, String key, int defVal) {
        return parseStringToInt(req.getParameter(key), defVal);
    }

    public static UserVO getLoginUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (UserVO) session.getAttribute("loginUser");
    }

    public static boolean isLogout(HttpServletRequest req) {
        return getLoginUser(req) == null;
    }

    public static int getLoginUserIboard(HttpServletRequest req) {
        UserVO loginUser = getLoginUser(req);
        return loginUser == null ? 0 : loginUser.getIuser();
    }
}
