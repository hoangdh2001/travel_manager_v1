package dao;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import connect.ConnectDB;
import entity.CT_ThamQuan;
import entity.ChuyenDi;
import entity.DiaDanh;

public class CT_ThamQuan_DAO {
	public static ArrayList<String> getTenDiaTheoChuyenDi(String maChuyenDi) {
		ArrayList<String> dsTenDiaDanh = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select STT, tenDiaDanh from CT_ThamQuan as tq join\r\n"
				+ "DiaDanh as dd on tq.maDiaDanh = dd.maDiaDanh\r\n"
				+ "where maChuyenDi = N'"+ maChuyenDi +"'\r\n"
				+ "order by STT";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dsTenDiaDanh.add(rs.getString("tenDiaDanh"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTenDiaDanh;
	}
	public static ArrayList<Image> getAnhChuyenDiTheoChuyenDi(String maChuyenDi) {
		ArrayList<Image> dsAnhChuyenDi = new ArrayList<Image>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select STT, anhDiaDanh from CT_ThamQuan as tq join\r\n"
				+ "DiaDanh as dd on tq.maDiaDanh = dd.maDiaDanh\r\n"
				+ "where maChuyenDi = N'"+ maChuyenDi +"'\r\n"
				+ "order by STT";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				byte[] imageData = rs.getBytes("anhDiaDanh");
				ImageIcon icon = new ImageIcon(imageData);
				Image anhDiaDanh = icon.getImage();
				dsAnhChuyenDi.add(anhDiaDanh);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsAnhChuyenDi;
	}
	public static boolean insertCT_ThamQuan(String maDiaDanh, String maChuyenDi) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		String sql = "insert into CT_ThamQuan (maDiaDanh, maChuyenDi)\r\n"
				+ "values ('"+ maDiaDanh +"', '"+ maChuyenDi +"')";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public static boolean deleteCT_ThamQuan(String maChuyenDi) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		String sql = "delete CT_ThamQuan\r\n"
				+ "where maChuyenDi = '"+ maChuyenDi +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public static int soLuongDiaDanhCuaChuyenDi(String maChuyenDi) {
		int count = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select COUNT(*) from CT_ThamQuan\r\n"
				+ "where maChuyenDi = '"+ maChuyenDi +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	public static ArrayList<DiaDanh> getDiaDanhTheoChuyenDi(String maChuyenDi) {
		ArrayList<DiaDanh> dsDiaDanh = new ArrayList<DiaDanh>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select dd.* from CT_ThamQuan as ct join DiaDanh as dd on ct.maDiaDanh = dd.maDiaDanh\r\n"
				+ "where maChuyenDi = '"+ maChuyenDi +"'\r\n"
				+ "order by STT";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dsDiaDanh.add(DiaDanh_DAO.getDiaDanh(rs.getString("maDiaDanh")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDiaDanh;
	}
	public ArrayList<CT_ThamQuan> getAllCT_ThamQuan() {
		ArrayList<CT_ThamQuan> dsCT_ThamQuan = new ArrayList<CT_ThamQuan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select distinct maChuyenDi from CT_ThamQuan";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<DiaDanh> dsDiaDanh = new ArrayList<DiaDanh>();
				dsDiaDanh = getDiaDanhTheoChuyenDi(rs.getString("maChuyenDi"));
				ChuyenDi chuyenDi = ChuyenDi_DAO.getChuyenDi(rs.getString("maChuyenDi"));
				CT_ThamQuan ct_ThamQuan = new CT_ThamQuan(dsDiaDanh, chuyenDi);
				dsCT_ThamQuan.add(ct_ThamQuan);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCT_ThamQuan;
	}
	public ArrayList<CT_ThamQuan> themVaoBangCT_ThamQuan(int page) {
		ArrayList<CT_ThamQuan> dsCT_ThamQuan = new ArrayList<CT_ThamQuan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int number = page * 5;
		String sql = "select distinct ct.maChuyenDi from CT_ThamQuan as ct join ChuyenDi as cd on ct.maChuyenDi = cd.maChuyenDi\r\n"
				+ "			where trangThai = N'Chưa bắt đầu'\r\n"
				+ "			order by ct.maChuyenDi offset "+ number +" rows fetch next 5 rows only";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChuyenDi chuyenDi = ChuyenDi_DAO.getChuyenDi(rs.getString("maChuyenDi"));
				dsCT_ThamQuan.add(new CT_ThamQuan(getDiaDanhTheoChuyenDi(rs.getString("maChuyenDi")), chuyenDi));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCT_ThamQuan;
	}
	public int getSoLuong() {
		int  count = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select count(distinct ct.maChuyenDi) from CT_ThamQuan as ct join ChuyenDi as cd on ct.maChuyenDi = cd.maChuyenDi\r\n"
				+ "			where trangThai = N'Chưa bắt đầu'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs= stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
 }
