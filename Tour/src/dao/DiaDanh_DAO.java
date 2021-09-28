package dao;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import connect.ConnectDB;
import entity.DiaDanh;

public class DiaDanh_DAO {
	public static ArrayList<DiaDanh> getAllDiaDanh() {
		ArrayList<DiaDanh> dsDiaDanh = new ArrayList<DiaDanh>();
		ConnectDB.getInstance();
		String sql = "select * from DiaDanh";
		Connection con = ConnectDB.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				DiaDanh diaDanh = new DiaDanh();
				diaDanh.setMaDiaDanh(rs.getString("maDiaDanh"));
				diaDanh.setTenDiaDanh(rs.getString("tenDiaDanh"));
				diaDanh.setThuocTinh(rs.getString("thuocTinh"));
				diaDanh.setAnhDiaDanh(rs.getBytes("anhDiaDanh"));
				dsDiaDanh.add(diaDanh);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDiaDanh;
	}
	public static ArrayList<DiaDanh> themVaoBangDiaDanh(int page, String ttDiaDanh, String thuocTinh) {
		ArrayList<DiaDanh> dsDiaDanh = new ArrayList<DiaDanh>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		int number = page * 20;
		String sql = "select * from DiaDanh\r\n"
				+ "where (maDiaDanh like N'%"+ ttDiaDanh +"%' or tenDiaDanh like N'%"+ ttDiaDanh +"%'\r\n"
				+ "or dbo.ufn_removeMark(tenDiaDanh) like N'%"+ ttDiaDanh +"%')\r\n"
				+ "and thuocTinh like N'%"+ thuocTinh +"%'\r\n"
				+ "order by maDiaDanh offset "+ number +" rows fetch next 20 rows only";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				DiaDanh diaDanh = new DiaDanh();
				diaDanh.setMaDiaDanh(rs.getString("maDiaDanh"));
				diaDanh.setTenDiaDanh(rs.getString("tenDiaDanh"));
				diaDanh.setThuocTinh(rs.getString("thuocTinh"));
				diaDanh.setAnhDiaDanh(rs.getBytes("anhDiaDanh"));
				dsDiaDanh.add(diaDanh);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return dsDiaDanh;
	}
	public static int soLuongDiaDanh(String ttDiaDanh, String thuocTinh) {
		int soLuongDiaDanh = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select COUNT(*) from DiaDanh\r\n"
				+ "where (maDiaDanh like N'%"+ ttDiaDanh +"%' or tenDiaDanh like N'%"+ ttDiaDanh +"%'\r\n"
				+ "or dbo.ufn_removeMark(tenDiaDanh) like N'%"+ ttDiaDanh +"%')\r\n"
				+ "and thuocTinh like N'%"+ thuocTinh +"%'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				soLuongDiaDanh = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return soLuongDiaDanh;
	}
	public static ArrayList<String> getAllTinh() {
		ArrayList<String> dsTinh = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select distinct thuocTinh from DiaDanh";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dsTinh.add(rs.getString("thuocTinh"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTinh;
	}
	public static String phatSinhMaDiaDanh() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select max(maDiaDanh) from DiaDanh";
		String ID = "";
		try {
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			if(rs.next()) {
				ID = PhatSinhMaTuDong.getMa(rs.getString(1).substring(0, 2), rs.getString(1).substring(2, 6), 4);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ID;
	}
	public static boolean themDiaDanh(DiaDanh diaDanh, String filePath) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert Diadanh\r\n"
				+ "select '"+ diaDanh.getMaDiaDanh() +"', N'"+ diaDanh.getTenDiaDanh() +"', N'" + diaDanh.getThuocTinh() +"',\r\n"
				+ "BulkColumn from openrowset (bulk '"+ filePath +"',Single_Clob) as Picture";
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	public static byte[] getAnh(String maDiaDanh) {
		byte[] data = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select anhDiaDanh from DiaDanh\r\n"
				+ "where maDiaDanh like N'%"+ maDiaDanh +"%'";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				data = rs.getBytes(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
	public static boolean deleteDiaDanh(String maDiaDanh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "delete DiaDanh where maDiaDanh = '"+ maDiaDanh +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	public static boolean updateDiaDanh(DiaDanh diaDanh, String filePath) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = null;
		if(filePath.equals("")) {
			sql = "update DiaDanh\r\n"
					+ "set tenDiaDanh = N'"+ diaDanh.getTenDiaDanh() +"', thuocTinh = N'" + diaDanh.getThuocTinh() + "'\r\n"
					+ "where maDiaDanh = '"+ diaDanh.getMaDiaDanh() +"'";
		}
		else {
			sql = "update DiaDanh\r\n"
					+ "set tenDiaDanh = N'"+ diaDanh.getTenDiaDanh() +"', thuocTinh = N'"+ diaDanh.getThuocTinh() +"', anhDiaDanh = BulkColumn from openrowset (bulk '"+ filePath +"',Single_Clob) as Picture\r\n"
					+ "where maDiaDanh = '"+ diaDanh.getMaDiaDanh() +"'";
		}
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
//			return false;
			e.printStackTrace();
		}
		return true;
	}
	public static DiaDanh getDiaDanh(String maDiaDanh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		DiaDanh diaDanh = null;
		String sql = "select * from DiaDanh where maDiaDanh = '"+ maDiaDanh +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				diaDanh = new DiaDanh();
				diaDanh.setMaDiaDanh(rs.getString("maDiaDanh"));
				diaDanh.setTenDiaDanh(rs.getString("tenDiaDanh"));
				diaDanh.setThuocTinh(rs.getString("thuocTinh"));
				diaDanh.setAnhDiaDanh(rs.getBytes("anhDiaDanh"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return diaDanh;
	}
	
}
