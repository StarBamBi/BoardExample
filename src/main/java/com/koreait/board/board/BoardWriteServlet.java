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

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.disForward(req,res,"board/write");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");

        HttpSession session = req.getSession();
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");

        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setCtnt(ctnt);
        vo.setWriter(loginUser.getIuser());

        int result = BoardDAO.write(vo);
        switch (result) {
            case 1:
                res.sendRedirect("/board/list");
                break;
            case 0:
                req.setAttribute("writeErr","글 작성에 실패하였습니다.");
                req.setAttribute("data", vo);
                doGet(req,res);
                break;
        }

    }
}