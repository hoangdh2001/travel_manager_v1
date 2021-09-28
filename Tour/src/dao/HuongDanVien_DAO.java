package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.ConnectDB;
import entity.HuongDanVien;

public class HuongDanVien_DAO {
	public static HuongDanVien getHuongDanVien(String maHuongDanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HuongDanVien huongDanVien = null;
		String sql = "select * from HuongDanVien\r\n"
				+ "where maHuongDanVien = '"+ maHuongDanVien +"'";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				huongDanVien = new HuongDanVien();
				huongDanVien.setMaHuongDanVien(rs.getString("maHuongDanVien"));
				huongDanVien.setTenHuongDanVien(rs.getString("tenHuongDanVien"));
				huongDanVien.setGioiTinh(rs.getBoolean("gioiTinh"));
				huongDanVien.setTrangThai(rs.getString("trangThai"));
				huongDanVien.setSoDienThoai(rs.getString("soDienThoai"));
				huongDanVien.setDiaChi(DiaChi_DAO.getDiaChi(rs.getString("maDiaChi")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return huongDanVien;
	}
	public HuongDanVien getHuongDanVienTheoSoDienThoai(String soDienThoai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		HuongDanVien huongDanVien = null;
		String sql = "select * from HuongDanVien where soDienThoai = '"+ soDienThoai +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				huongDanVien = new HuongDanVien();
				huongDanVien.setMaHuongDanVien(rs.getString("maHuongDanVien"));
				huongDanVien.setTenHuongDanVien(rs.getString("tenHuongDanVien"));
				huongDanVien.setGioiTinh(rs.getBoolean("gioiTinh"));
				huongDanVien.setTrangThai(rs.getString("trangThai"));
				huongDanVien.setSoDienThoai(rs.getString("soDienThoai"));
				huongDanVien.setDiaChi(DiaChi_DAO.getDiaChi(rs.getString("maDiaChi")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return huongDanVien;
	}
	public ArrayList<HuongDanVien> themVaoBangHuongDanVien(int page, String tenHuongDanVien, String soDienThoai, String gioiTinh, String trangThai) {
		ArrayList<HuongDanVien> dsHuongDanVien = new ArrayList<HuongDanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int number = page * 20;
		String sql = "select * from HuongDanVien\r\n"
				+ "where (tenHuongDanVien like N'%"+ tenHuongDanVien +"%' or dbo.ufn_removeMark(tenHuongDanVien) like N'%"+ tenHuongDanVien +"%')\r\n"
				+ "and soDienThoai like '%"+ soDienThoai +"%'\r\n"
				+ "and gioiTinh like '%"+ gioiTinh +"%'\r\n"
				+ "and trangThai like N'%"+ trangThai +"%'\r\n"
				+ "order by maHuongDanVien offset "+ number +" rows fetch next 20 rows only";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HuongDanVien huongDanVien = new HuongDanVien();
				huongDanVien.setMaHuongDanVien(rs.getString("maHuongDanVien"));
				huongDanVien.setTenHuongDanVien(rs.getString("tenHuongDanVien"));
				huongDanVien.setGioiTinh(rs.getBoolean("gioiTinh"));
				huongDanVien.setTrangThai(rs.getString("trangThai"));
				huongDanVien.setSoDienThoai(rs.getString("soDienThoai"));
				huongDanVien.setDiaChi(DiaChi_DAO.getDiaChi(rs.getString("maDiaChi")));
				dsHuongDanVien.add(huongDanVien);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHuongDanVien;
	}
	public int soLuongHuongDanVien(String tenHuongDanVien, String soDienThoai, String gioiTinh, String trangThai) {
		int count = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select count(*) from HuongDanVien\r\n"
				+ "where tenHuongDanVien like N'%"+ tenHuongDanVien +"%'\r\n"
				+ "and soDienThoai like '%"+ soDienThoai +"%'\r\n"
				+ "and gioiTinh like '%"+ gioiTinh +"%'\r\n"
				+ "and trangThai like '%"+ trangThai +"%'";
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
	
	public boolean deleteHuongDanVien(String maHuongDanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		String sql = "delete HuongDanVien where maHuongDanVien = '"+ maHuongDanVien +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public static String phatSinhMaHuongDanVien() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select max(maHuongDanVien) from HuongDanVien";
		String ID ="";
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
	public boolean themHuongDanVien(HuongDanVien huongDanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n =0;
		String sql = "insert HuongDanVien (maHuongDanVien, tenHuongDanVien, gioiTinh, soDienThoai, maDiaChi)\r\n"
				+ "values ('"+ phatSinhMaHuongDanVien() +"', N'"+ huongDanVien.getTenHuongDanVien() +"', '"+ huongDanVien.isGioiTinh() +"', '"+ huongDanVien.getSoDienThoai() +"', '"+ huongDanVien.getDiaChi().getMaDiaChi() +"')";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean updateHuongDanVien(HuongDanVien huongDanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		String sql = "update HuongDanVien\r\n"
				+ "set tenHuongDanVien = N'"+ huongDanVien.getTenHuongDanVien() +"',\r\n"
				+ "gioiTinh = '"+ huongDanVien.isGioiTinh() +"',\r\n"
				+ "soDienThoai = '"+ huongDanVien.getSoDienThoai() +"',\r\n"
				+ "maDiaChi = '"+ huongDanVien.getDiaChi().getMaDiaChi() +"'\r\n"
				+ "where maHuongDanVien = '"+ huongDanVien.getMaHuongDanVien() +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public static boolean ktLichHuongDanVien(String maHuongDanVien, Date ngayKhoiHanh, Date ngayKetThuc) {
		ConnectDB.getInstance();
		Connection con= ConnectDB.getConnection();
		String sql = "SELECT hdv.maHuongDanVien, ngayKhoiHanh, ngayKetThuc\r\n"
				+ "FROM     HuongDanVien AS hdv INNER JOIN\r\n"
				+ "                  ChuyenDi AS cd ON hdv.maHuongDanVien = cd.maHuongDanVien\r\n"
				+ "				  where ('"+ ngayKhoiHanh +"' between ngayKhoiHanh and ngayKetThuc\r\n"
				+ "				  or '"+ ngayKetThuc +"' between ngayKhoiHanh and ngayKetThuc)\r\n"
				+ "				  and hdv.maHuongDanVien = '"+ maHuongDanVien +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
}
