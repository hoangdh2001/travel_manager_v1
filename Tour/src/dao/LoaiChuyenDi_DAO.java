package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.ConnectDB;
import entity.LoaiChuyenDi;

public class LoaiChuyenDi_DAO {
	public static LoaiChuyenDi getLoaiChuyenDi(String maLoai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LoaiChuyenDi loaiChuyenDi = null;
		String sql = "select * from LoaiChuyenDi\r\n"
				+ "where maLoai like N'%" + maLoai + "%'";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				loaiChuyenDi = new LoaiChuyenDi(rs.getString("maLoai"), rs.getString("tenLoai"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loaiChuyenDi;
	}
	public static LoaiChuyenDi getLoaiChuyenDiTheoTen(String tenLoai) {
		ConnectDB.getInstance();
		Connection con =ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LoaiChuyenDi loaiChuyenDi = null;
		String sql = "select * from LoaiChuyenDi\r\n"
				+ "where tenLoai like N'%"+ tenLoai +"%'";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				loaiChuyenDi = new LoaiChuyenDi(rs.getString("maLoai"), rs.getString("tenLoai"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loaiChuyenDi;
	}
	public static ArrayList<String> getAllLoaiChuyenDi() {
		ArrayList<String> dsTenLoai = new ArrayList<String>();
 		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select tenLoai from LoaiChuyenDi";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				dsTenLoai.add(rs.getString("tenLoai"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTenLoai;
	}
}
