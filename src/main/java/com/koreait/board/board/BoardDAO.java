package com.koreait.board.board;

import com.koreait.board.DbUtils;
import com.koreait.board.model.BoardParamVO;
import com.koreait.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    public static List<BoardVO> list(BoardParamVO param) {
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT A.iboard, A.title, A.rdt, B.nm AS writerNm FROM t_board A " +
                " INNER JOIN t_user B ON A.writer = B.iuser ORDER BY A.iboard DESC LIMIT ?,? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getsIdx());  // 몇 페이지까지인지 알려주는 sIdx
            ps.setInt(2,param.getRecordCnt()); // 한 페이지에 보여지는 글 수
            rs = ps.executeQuery();

            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setRdt(rs.getString("rdt"));
                vo.setWriterNm(rs.getString("writerNm"));
                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con ,ps, rs);
        }
        return list;
    }

    public static int selMaxPage(BoardParamVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT CEIL(COUNT(*) / ?) FROM t_board ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getRecordCnt());
            rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    public static BoardVO detail(BoardVO vo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT A.ctnt, A.title, A.rdt, A.writer, B.nm AS writerNm FROM t_board A INNER JOIN t_user B " +
                " ON A.writer = B.iuser WHERE A.iboard=? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,vo.getIboard());
            rs = ps.executeQuery();

            if(rs.next()) {
                BoardVO param = new BoardVO();
                param.setIboard(vo.getIboard());
                param.setCtnt(rs.getString("ctnt"));
                param.setTitle(rs.getString("title"));
                param.setRdt(rs.getString("rdt"));
                param.setWriter(rs.getInt("writer"));
                param.setWriterNm(rs.getString("writerNm"));
                return param;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }

    public static int write(BoardVO vo) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board (title, ctnt, writer) VALUES (?,?,?) ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,vo.getTitle());
            ps.setString(2,vo.getCtnt());
            ps.setInt(3,vo.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delete(BoardVO vo) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_board WHERE iboard=? AND writer=? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,vo.getIboard());
            ps.setInt(2,vo.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int update(BoardVO vo) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " UPDATE t_board SET title=?, ctnt=?, mdt=now() WHERE iboard=? AND writer=? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,vo.getTitle());
            ps.setString(2,vo.getCtnt());
            ps.setInt(3,vo.getIboard());
            ps.setInt(4,vo.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    // 이전글 : 제일 최신 글을 의미(제일 최신 글은 iboard가 높다)

    public static int preboard(BoardVO vo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT * FROM t_board WHERE iboard>? ORDER BY iboard LIMIT 1 ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,vo.getIboard());
            rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("iboard");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    public static int nextboard(BoardVO vo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT * FROM t_board WHERE iboard<? ORDER BY iboard DESC LIMIT 1 ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,vo.getIboard());
            rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("iboard");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }
}
