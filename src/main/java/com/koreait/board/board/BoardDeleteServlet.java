package com.koreait.board.board;

import com.koreait.board.MyUtils;
import com.koreait.board.model.BoardVO;
import com.koreait.board.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");

        int iboard = MyUtils.getParameterInt(req,"iboard");

        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);
        vo.setWriter(loginUser.getIuser());

        int result = BoardDAO.delete(vo);
        switch (result) {
            case 1:
                res.sendRedirect("/board/list");
                break;
            case 0:
                req.setAttribute("deleteMsg", "삭제 실패");
                MyUtils.disForward(req,res,"board/detail");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}