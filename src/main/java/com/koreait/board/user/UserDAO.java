package com.koreait.board.user;

import com.koreait.board.DbUtils;
import com.koreait.board.model.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public static int join(UserVO vo) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_user (uid, upw, nm, gender) VALUES (?,?,?,?) ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,vo.getUid());
            ps.setString(2,vo.getUpw());
            ps.setString(3,vo.getNm());
            ps.setInt(4,vo.getGender());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int login(UserVO vo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iuser, upw, nm, gender FROM t_user WHERE uid=? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,vo.getUid());
            rs = ps.executeQuery();
            if(rs.next()) {
                vo.setIuser(rs.getInt("iuser"));
                vo.setNm(rs.getString("nm"));
                vo.setGender(rs.getInt("gender"));

                String dbpw = rs.getString("upw");
                return dbpw.equals(vo.getUpw()) ? 1 : 3;
            }
            return 2;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }
}
