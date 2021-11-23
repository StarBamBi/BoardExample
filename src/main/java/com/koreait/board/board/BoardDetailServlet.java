package com.koreait.board.board;

import com.koreait.board.MyUtils;
import com.koreait.board.model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardVO vo = new BoardVO();
        int iboard = MyUtils.getParameterInt(req,"iboard");

        vo.setIboard(iboard);
        BoardVO result = BoardDAO.detail(vo);
        int preIboard = BoardDAO.preboard(vo);
        int nextIboard = BoardDAO.nextboard(vo);

        req.setAttribute("detail",result);
        req.setAttribute("preIboard", preIboard);
        req.setAttribute("nextIboard",nextIboard);

        MyUtils.disForward(req,res,"board/detail");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}