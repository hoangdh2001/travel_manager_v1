package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.ConnectDB;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	public static TaiKhoan getTaiKhoan(String soDienThoai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		TaiKhoan taiKhoan =null;
		String sql = "select * from TaiKhoan where soDienThoai = '"+ soDienThoai +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				taiKhoan = new TaiKhoan();
				taiKhoan.setSoDienThoai(rs.getString("soDienThoai"));
				taiKhoan.setEmail(rs.getString("email"));
				taiKhoan.setMatKhau(rs.getString("matKhau"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return taiKhoan;
	}
}
