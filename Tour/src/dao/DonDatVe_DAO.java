package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.ConnectDB;
import entity.DonDatVe;

public class DonDatVe_DAO {
	public static String phatSinhMaDonDatVe() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select max(maDonDatVe) from DonDatVe";
		String ID = "";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ID = PhatSinhMaTuDong.getMa(rs.getString(1).substring(0, 3), rs.getString(1).substring(3, 8), 5);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ID;
	}
	
	public static boolean insertDonDatVe(DonDatVe donDatVe) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into DonDatVe (maDonDatVe, maKhachHang, maNhanVien, tongTien, soLuong, maChuyenDi)\r\n"
				+ "values ('"+ phatSinhMaDonDatVe() +"', '"+ donDatVe.getKhachHang().getMaKhachHang() +"', '"+ donDatVe.getNhanVien().getMaNhanVien() +"', "+ donDatVe.getTongTien() +", "+ donDatVe.getSoLuong() +", '"+ donDatVe.getChuyenDi().getMaChuyenDi() +"')";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<DonDatVe> themDonDatVeVaoBang(int page, String timKiem, String ngayDatVe) {
		ArrayList<DonDatVe> dsDonDatVe = new ArrayList<DonDatVe>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int number = page * 20;
		String sql = "SELECT *\r\n"
				+ "FROM     DonDatVe AS dv INNER JOIN\r\n"
				+ "                  NhanVien AS nv ON dv.maNhanVien = nv.maNhanVien INNER JOIN\r\n"
				+ "                  KhachHang AS kh ON dv.maKhachHang = kh.maKhachHang\r\n"
				+ "				  where (maDonDatVe like '%"+ timKiem +"%'\r\n"
				+ "				or kh.maKhachHang like '%"+ timKiem +"%'\r\n"
				+ "				or tenKhachHang like N'%"+ timKiem +"%'\r\n"
				+ "				or dbo.ufn_removeMark(tenKhachHang) like N'%"+ timKiem +"%'\r\n"
				+ "				or kh.soDienThoai like '%"+ timKiem +"%'\r\n"
				+ "				or nv.maNhanVien like '%"+ timKiem +"%'\r\n"
				+ "				or tenNhanVien like N'%"+ timKiem +"%'\r\n"
				+ "				or dbo.ufn_removeMark(tenNhanVien) like N'%"+ timKiem +"%') and ngayDatVe like N'%"+ ngayDatVe +"%'\r\n"
				+ "				  Order by maDonDatVe offset "+ number +" rows fetch next 20 rows only";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs= stmt.executeQuery();
			while (rs.next()) {
				DonDatVe donDatVe = new DonDatVe();
				donDatVe.setMaDonDatVe(rs.getString("maDonDatVe"));
				donDatVe.setNgayDatVe(rs.getDate("ngayDatVe"));
				donDatVe.setKhachHang(KhachHang_DAO.getKhachHang(rs.getString("maKhachHang")));
				donDatVe.setNhanVien(NhanVien_DAO.getNhanVien(rs.getString("maNhanVien")));
				donDatVe.setTongTien(rs.getDouble("tongTien"));
				donDatVe.setSoLuong(rs.getInt("soLuong"));
				donDatVe.setChuyenDi(ChuyenDi_DAO.getChuyenDi(rs.getString("maChuyenDi")));
				dsDonDatVe.add(donDatVe);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDonDatVe;
	}
	public int soLuongDonDatVe(String timKiem, String ngayDatVe) {
		int count = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT count(*)\r\n"
				+ "FROM     DonDatVe AS dv INNER JOIN\r\n"
				+ "                  NhanVien AS nv ON dv.maNhanVien = nv.maNhanVien INNER JOIN\r\n"
				+ "                  KhachHang AS kh ON dv.maKhachHang = kh.maKhachHang\r\n"
				+ "				  where (maDonDatVe like '%"+ timKiem +"%'\r\n"
				+ "				or kh.maKhachHang like '%"+ timKiem +"%'\r\n"
				+ "				or tenKhachHang like N'%"+ timKiem +"%'\r\n"
				+ "				or dbo.ufn_removeMark(tenKhachHang) like N'%"+ timKiem +"%'\r\n"
				+ "				or kh.soDienThoai like '%"+ timKiem +"%'\r\n"
				+ "				or nv.maNhanVien like '%"+ timKiem +"%'\r\n"
				+ "				or tenNhanVien like N'%"+ timKiem +"%'\r\n"
				+ "				or dbo.ufn_removeMark(tenNhanVien) like N'%"+ timKiem +"%') and ngayDatVe like N'%"+ ngayDatVe +"%'";
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
	public boolean deleteDonDatVe(String maDonDatVe) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con =ConnectDB.getConnection();
		String sql= "delete DonDatVe\r\n"
				+ "where maDonDatVe = '"+ maDonDatVe +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
}
