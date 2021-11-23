package com.koreait.board.user;

import com.koreait.board.MyUtils;
import com.koreait.board.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.disForward(req,res,"user/login");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        UserVO vo = new UserVO();
        vo.setUid(uid);
        vo.setUpw(upw);

        int result = UserDAO.login(vo);
        if(result==1) {
            vo.setUpw(null);

            HttpSession session = req.getSession();
            session.setAttribute("loginUser", vo);
            res.sendRedirect("/board/list");
            return;
        }
        String err = null;
        switch (result) {
            case 0:
                err = "로그인에 실패하였습니다.";
                break;
            case 2:
                err = "아이디를 확인해 주세요.";
                break;
            case 3:
                err = "비밀번호를 확인해 주세요.";
                break;
        }
        req.setAttribute("err", err);
        doGet(req, res); //로그인 페이지로 이동
    }
}