package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.ConnectDB;
import entity.DiaChi;

public class DiaChi_DAO {
	public static ArrayList<DiaChi> getAllDiaChi() {
		ArrayList<DiaChi> dsDiaChi = new ArrayList<DiaChi>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from DiaChi";
		
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String maDiaChi = rs.getString(1);
				String tinhThanh = rs.getString(2);
				String quanHuyen = rs.getString(3);
				String phuongXa = rs.getString(4);
				dsDiaChi.add(new DiaChi(maDiaChi, tinhThanh, quanHuyen, phuongXa));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDiaChi;
	}
	public static ArrayList<String> getAllTinh() {
		ArrayList<String> dsTinh = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select distinct tinhThanh from DiaChi";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String diaChi = rs.getString(1);
				if(!(diaChi == null))
					dsTinh.add(diaChi);
				else
					dsTinh.add("Trống");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTinh;
	}
	public static DiaChi getDiaChi(String tinhThanh, String quanHuyen, String phuongXa) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt =null;
		ResultSet rs = null;
		DiaChi diaChi = null;
		String sql = "select * from DiaChi\r\n"
				+ "where tinhThanh like N'%"+ tinhThanh +"%'\r\n"
				+ "and quanHuyen like N'%"+ quanHuyen +"%'\r\n"
				+ "and phuongXa like N'%"+ phuongXa +"%'";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				diaChi = new DiaChi(rs.getString("maDiaChi"), rs.getString("tinhThanh"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return diaChi;
	}
	public static DiaChi getDiaChi(String maDiaChi) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt =null;
		ResultSet rs = null;
		DiaChi diaChi = null;
		String sql = "select * from DiaChi\r\n"
				+ "where maDiaChi like N'%" + maDiaChi + "%'";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				diaChi = new DiaChi(rs.getString("maDiaChi"), rs.getString("tinhThanh"), rs.getString("quanHuyen"), rs.getString("phuongXa"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return diaChi;
	}
	public static ArrayList<String> getQuanHuyenTheoTinh(String tinhThanh) {
		ArrayList<String> dsQuanHuyen = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select distinct quanHuyen from DiaChi\r\n"
				+ "where tinhThanh like N'%"+ tinhThanh +"%'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dsQuanHuyen.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dsQuanHuyen;
	}
	public static ArrayList<String> getPhuongXaTheoQuanHuyenVaTinh(String tinhThanh, String quanHuyen) {
		ArrayList<String> dsQuanHuyen = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select phuongXa from DiaChi\r\n"
				+ "where tinhThanh like N'%"+ tinhThanh +"%'\r\n"
				+ "and quanHuyen like N'"+ quanHuyen +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dsQuanHuyen.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dsQuanHuyen;
	}
}
