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

@WebServlet("/board/upd")
public class BoardUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(MyUtils.isLogout(req)) {
            res.sendRedirect("/user/login");
            return;
        }

        if(req.getAttribute("detail")==null) {
            int iboard = MyUtils.getParameterInt(req, "iboard");
            BoardVO param = new BoardVO();
            param.setIboard(iboard);
            req.setAttribute("detail", BoardDAO.detail(param));
        }

        MyUtils.disForward(req, res, "board/update");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        int iboard = MyUtils.getParameterInt(req,"iboard");

        HttpSession session = req.getSession();
        UserVO loginUser = (UserVO)session.getAttribute("loginUser");

        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setCtnt(ctnt);
        vo.setIboard(iboard);
        vo.setWriter(MyUtils.getLoginUserIboard(req)); // 여기 iuser 들어가있는건가요 ?

        int result = BoardDAO.update(vo);
        switch (result) {
            case 1:
                res.sendRedirect("/board/detail?iboard=" + vo.getIboard());
                break;
            case 0:
                req.setAttribute("updErr","수정 실패");
                req.setAttribute("data", vo);
                doGet(req,res);
                break;
        }
    }
}