package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connect.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	public static NhanVien getNhanVien(String maNhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		NhanVien nhanVien = null;
		String sql = "select * from NhanVien as nv\r\n"
				+ "join TaiKhoan as tk on nv.soDienThoai = tk.soDienThoai\r\n"
				+ "where maNhanVien like N'%" + maNhanVien + "%'";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				nhanVien = new NhanVien();
				nhanVien.setMaNhanVien(rs.getString("maNhanVien"));
				nhanVien.setTenNhanVien(rs.getString("tenNhanVien"));
				nhanVien.setGioiTinh(rs.getBoolean("gioiTinh"));
				nhanVien.setTrangThai(rs.getBoolean("trangThai"));
				nhanVien.setEmail(rs.getString("email"));
				nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
				nhanVien.setDiaChi(DiaChi_DAO.getDiaChi(rs.getString("maDiaChi")));
				nhanVien.setMatKhau(rs.getString("matKhau"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nhanVien;
	}
	
	public String getPassword(String user) {
		String pass="";
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String query = "select * from NhanVien n join TaiKhoan t on n.soDienThoai=t.soDienThoai where t.soDienThoai='"+user+"'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				pass = rs.getString("matKhau");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pass;
	}
	public ArrayList<NhanVien> themVaoBangNhanVien(int page, String soDienThoai, String tenNhanVien, String gioiTinh) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		int number = page * 20;
		String sql = "select *\r\n"
				+ "from TaiKhoan as tk inner join\r\n"
				+ "NhanVien as nv on tk.soDienThoai = nv.soDienThoai inner join\r\n"
				+ "DiaChi as dc on nv.maDiaChi = dc.maDiaChi\r\n"
				+ "where nv.soDienThoai like '%"+ soDienThoai +"%'\r\n"
				+ "and (tenNhanVien like N'%"+ tenNhanVien +"%' or dbo.ufn_removeMark(tenNhanVien) like N'%"+ tenNhanVien +"%')\r\n"
				+ "and gioiTinh like '%"+ gioiTinh +"%'\r\n"
				+ "order by maNhanVien offset "+ number +" rows fetch next 20 rows only";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs == null) {
				return null;
			}
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setMaNhanVien(rs.getString("maNhanVien"));
				nhanVien.setTenNhanVien(rs.getString("tenNhanVien"));
				nhanVien.setGioiTinh(rs.getBoolean("gioiTinh"));
				nhanVien.setTrangThai(rs.getBoolean("trangThai"));
				nhanVien.setDiaChi(DiaChi_DAO.getDiaChi(rs.getString("maDiaChi")));
				nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
				nhanVien.setEmail(rs.getString("email"));
				nhanVien.setMatKhau(rs.getString("matKhau"));
				dsNhanVien.add(nhanVien);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	public int soLuongNhanVien(String soDienThoai, String tenNhanVien, String gioiTinh) {
		int count = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select COUNT(*)\r\n"
				+ "from TaiKhoan as tk inner join\r\n"
				+ "NhanVien as nv on tk.soDienThoai = nv.soDienThoai inner join\r\n"
				+ "DiaChi as dc on nv.maDiaChi = dc.maDiaChi\r\n"
				+ "where nv.soDienThoai like '%"+ soDienThoai +"%'\r\n"
				+ "and (tenNhanVien like N'%"+ tenNhanVien +"%' or dbo.ufn_removeMark(tenNhanVien) like N'%"+ tenNhanVien +"%')\r\n"
				+ "and gioiTinh like '%"+ gioiTinh +"%'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next()) {
				throw new Exception("Không có dữ liệu");
			}
			count = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	public boolean deleteNhanVien(String maNhanVien, String soDienThoai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		String sql = "delete NhanVien\r\n"
				+ "where maNhanVien = N'"+ maNhanVien +"'\r\n"
				+ "delete TaiKhoan\r\n"
				+ "where soDienThoai = '"+ soDienThoai +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public String phatSinhMaTuDongNhanVien() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select max(maNhanVien) from NhanVien";
		String ID = "";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				ID = PhatSinhMaTuDong.getMa(rs.getString(1).substring(0, 4), rs.getString(1).substring(4, 9), 5);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ID;
	}
	public boolean themNhanVien(NhanVien nhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		String sql = "insert TaiKhoan\r\n"
				+ "values ('"+ nhanVien.getSoDienThoai() +"', '"+ nhanVien.getEmail() +"', '"+ nhanVien.getMatKhau() +"')\r\n"
				+ "insert into NhanVien (maNhanVien, tenNhanVien, gioiTinh, maDiaChi, soDienThoai)\r\n"
				+ "values ('"+ phatSinhMaTuDongNhanVien() +"', N'"+ nhanVien.getTenNhanVien() +"', '"+ nhanVien.isGioiTinh()+"', '"+ nhanVien.getDiaChi().getMaDiaChi() +"', '"+ nhanVien.getSoDienThoai() +"')";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean updateNhanVien(NhanVien nhanVien, String soDienThoaiCu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = null;
		if(!nhanVien.getMatKhau().equals("")) {
			sql = "alter table TaiKhoan nocheck constraint all\r\n"
					+ "alter table NhanVien nocheck constraint all\r\n"
					+ "update TaiKhoan\r\n"
					+ "set soDienThoai = '"+ nhanVien.getSoDienThoai() +"',\r\n"
					+ "email = '"+ nhanVien.getEmail() +"',\r\n"
					+ "matKhau = '"+ nhanVien.getMatKhau() +"'\r\n"
					+ "where soDienThoai = '"+ soDienThoaiCu +"'\r\n"
					+ "update NhanVien\r\n"
					+ "set tenNhanVien = N'"+ nhanVien.getTenNhanVien() +"',\r\n"
					+ "gioiTinh = '"+ nhanVien.isGioiTinh() +"',\r\n"
					+ "trangThai = '"+ nhanVien.isTrangThai() +"',\r\n"
					+ "maDiaChi = '"+ nhanVien.getDiaChi().getMaDiaChi() +"',\r\n"
					+ "soDienThoai = '"+ nhanVien.getSoDienThoai() +"'\r\n"
					+ "where maNhanVien = '"+ nhanVien.getMaNhanVien() +"'\r\n"
					+ "alter table NhanVien check constraint all\r\n"
					+ "alter table TaiKhoan check constraint all";
		}
		else {
			sql = "alter table TaiKhoan nocheck constraint all\r\n"
					+ "alter table NhanVien nocheck constraint all\r\n"
					+ "update TaiKhoan\r\n"
					+ "set soDienThoai = '"+ nhanVien.getSoDienThoai() +"',\r\n"
					+ "email = '"+ nhanVien.getEmail() +"'\r\n"
					+ "where soDienThoai = '"+ soDienThoaiCu +"'\r\n"
					+ "update NhanVien\r\n"
					+ "set tenNhanVien = N'"+ nhanVien.getTenNhanVien() +"',\r\n"
					+ "gioiTinh = '"+ nhanVien.isGioiTinh() +"',\r\n"
					+ "trangThai = '"+ nhanVien.isTrangThai() +"',\r\n"
					+ "maDiaChi = '"+ nhanVien.getDiaChi().getMaDiaChi() +"',\r\n"
					+ "soDienThoai = '"+ nhanVien.getSoDienThoai() +"'\r\n"
					+ "where maNhanVien = '"+ nhanVien.getMaNhanVien() +"'\r\n"
					+ "alter table NhanVien check constraint all\r\n"
					+ "alter table TaiKhoan check constraint all";
		}
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	public static NhanVien getNgauNhienNhanVien() {
		NhanVien nhanVien = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT TOP 1 * FROM NhanVien\r\n"
				+ "ORDER BY NEWID()";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nhanVien = new NhanVien();
				nhanVien.setMaNhanVien(rs.getString("maNhanVien"));
				nhanVien.setTenNhanVien(rs.getString("tenNhanVien"));
				nhanVien.setGioiTinh(rs.getBoolean("gioiTinh"));
				nhanVien.setTrangThai(rs.getBoolean("trangThai"));
				nhanVien.setDiaChi(DiaChi_DAO.getDiaChi(rs.getString("maDiaChi")));
				nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nhanVien;
	}
	
}
