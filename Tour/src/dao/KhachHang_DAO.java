package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connect.ConnectDB;
import entity.DiaChi;
import entity.KhachHang;

public class KhachHang_DAO {
	public ArrayList<KhachHang> getAllKhachHang() {
		ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "select * from TaiKhoan as tk\r\n"
					+ "join KhachHang as kh on tk.soDienThoai = kh.soDienThoai\r\n"
					+ "join DiaChi as dc on kh.maDiaChi = dc.maDiaChi";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				KhachHang khachHang = new KhachHang();
				khachHang.setMaKhachHang(rs.getString("maKhachHang"));
				khachHang.setTenKhachHang(rs.getString("tenKhachHang"));
				khachHang.setGioiTinh(rs.getBoolean("gioiTinh"));
				khachHang.setNgaySinh(rs.getDate("ngaySinh"));
				khachHang.setDiaChi(new DiaChi(rs.getString("maDiaChi"), rs.getString("tinhThanh"), rs.getString("quanHuyen"), rs.getString("phuongXa")));
				khachHang.setSoDienThoai(rs.getString("soDienThoai"));
				khachHang.setEmail(rs.getString("email"));
				khachHang.setMatKhau(rs.getString("matKhau"));
				dsKhachHang.add(khachHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}
	
	public String phatSinhMaKhachHang() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select max(maKhachHang) from KhachHang";
		String ID = "";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				ID = PhatSinhMaTuDong.getMa(rs.getString(1).substring(0, 2), rs.getString(1).substring(2, 7), 5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ID;
	}

	public boolean insertKhachHang(KhachHang khachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "insert TaiKhoan\r\n"
				+ "values (N'"+ khachHang.getSoDienThoai() +"', N'"+ khachHang.getEmail() +"', N'"+ khachHang.getMatKhau() +"')\r\n"
				+ "insert KhachHang\r\n"
				+ "values (N'"+ phatSinhMaKhachHang() +"', N'"+ khachHang.getTenKhachHang() +"', N'"+ khachHang.isGioiTinh() +"', N'"+ khachHang.getNgaySinh() +"', N'"+ khachHang.getDiaChi().getMaDiaChi() +"', N'"+ khachHang.getSoDienThoai() +"')";
		try {
			stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<KhachHang> themVaoBang(int page, String soDienThoai, String tenKH, String gT, String tinhThanh) {
		ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int number = page * 20;
		
		String sql = "select * from TaiKhoan as tk\r\n"
				+ "join KhachHang as kh on tk.soDienThoai = kh.soDienThoai\r\n"
				+ "join DiaChi as dc on kh.maDiaChi = dc.maDiaChi\r\n"
				+ "where tk.soDienThoai like N'%"+ soDienThoai +"%'\r\n"
				+ "and (tenKhachHang like N'%"+ tenKH +"%' or dbo.ufn_removeMark(tenKhachHang) like N'%"+ tenKH +"%')\r\n"
				+ "and gioiTinh like '%"+ gT+"%'\r\n"
				+ "and tinhThanh like N'%"+ tinhThanh +"%'\r\n"
				+ "order by maKhachHang offset "+ number +" rows fetch next 20 rows only";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs == null) {
				return null;
			}
			while (rs.next()) {
				KhachHang khachHang = new KhachHang();
				khachHang.setMaKhachHang(rs.getString("maKhachHang"));
				khachHang.setTenKhachHang(rs.getString("tenKhachHang"));
				khachHang.setGioiTinh(rs.getBoolean("gioiTinh"));
				khachHang.setNgaySinh(rs.getDate("ngaySinh"));
				khachHang.setDiaChi(new DiaChi(rs.getString("maDiaChi"), rs.getString("tinhThanh"), rs.getString("quanHuyen"), rs.getString("phuongXa")));
				khachHang.setSoDienThoai(rs.getString("soDienThoai"));
				khachHang.setEmail(rs.getString("email"));
				khachHang.setMatKhau(rs.getString("matKhau"));
				dsKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	public ArrayList<KhachHang> themVaoBang(int page) {
		ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int number = page * 20;
		
		String sql = "select * from TaiKhoan as tk\r\n"
				+ "join KhachHang as kh on tk.soDienThoai = kh.soDienThoai\r\n"
				+ "join DiaChi as dc on kh.maDiaChi = dc.maDiaChi\r\n"
				+ "order by maKhachHang offset "+ number +" rows fetch next 20 rows only";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs == null)
				return null;
			while (rs.next()) {
				KhachHang khachHang = new KhachHang();
				khachHang.setMaKhachHang(rs.getString("maKhachHang"));
				khachHang.setTenKhachHang(rs.getString("tenKhachHang"));
				khachHang.setGioiTinh(rs.getBoolean("gioiTinh"));
				khachHang.setNgaySinh(rs.getDate("ngaySinh"));
				khachHang.setDiaChi(new DiaChi(rs.getString("maDiaChi"), rs.getString("tinhThanh"), rs.getString("quanHuyen"), rs.getString("phuongXa")));
				khachHang.setSoDienThoai(rs.getString("soDienThoai"));
				khachHang.setEmail(rs.getString("email"));
				khachHang.setMatKhau(rs.getString("matKhau"));
				dsKhachHang.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsKhachHang;
	}

	public int tongKhachHang(String soDienThoai, String tenKH, String gT, String tinhThanh) {
		int count = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "SELECT count(*)\r\n"
				+ "FROM     TaiKhoan AS tk INNER JOIN\r\n"
				+ "                  KhachHang AS kh ON tk.soDienThoai = kh.soDienThoai JOIN\r\n"
				+ "                  DiaChi AS dc on kh.maDiaChi = dc.maDiaChi\r\n"
				+ "				  where tk.soDienThoai like N'%"+ soDienThoai +"%' and tenKhachHang like N'%"+ tenKH +"%' and gioiTinh like N'%"+ gT +"%' and tinhThanh like N'%"+ tinhThanh +"%'";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new Exception("Khong co du lieu");
			}
			count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public String getPassword(String user) {
		String pass="";
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String query = "select * from KhachHang k join TaiKhoan t on k.soDienThoai=t.soDienThoai where t.soDienThoai='"+user+"'";
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
	public boolean xoaKhachHang(String maKhachHang, String soDienThoai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		String sql = "delete KhachHang\r\n"
				+ "where maKhachHang = '"+ maKhachHang +"'\r\n"
				+ "delete TaiKhoan\r\n"
				+ "where soDienThoai = '"+ soDienThoai +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public static boolean updateKhachHang(KhachHang khachHang, String soDienThoaiCu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = null;
		if(!khachHang.getMatKhau().equals("")) {
			sql = "alter table KhachHang nocheck constraint all\r\n"
					+ "alter table TaiKhoan nocheck constraint all\r\n"
					+ "alter table NhanVien nocheck constraint all\r\n"
					+ "update TaiKhoan\r\n"
					+ "set soDienThoai = '"+ khachHang.getSoDienThoai() +"',\r\n"
					+ "email = '"+ khachHang.getEmail() +"',\r\n"
					+ "matKhau = '"+ khachHang.getMatKhau() +"'\r\n"
					+ "where soDienThoai = '"+ soDienThoaiCu +"'\r\n"
					+ "update NhanVien\r\n"
					+ "set soDienThoai = '"+ khachHang.getSoDienThoai() +"'\r\n"
					+ "where soDienThoai = '"+ soDienThoaiCu +"'\r\n"
					+ "update KhachHang\r\n"
					+ "set tenKhachHang = N'"+ khachHang.getTenKhachHang() +"',\r\n"
					+ "gioiTinh = '"+ khachHang.isGioiTinh() +"',\r\n"
					+ "ngaySinh = '"+ khachHang.getNgaySinh() +"',\r\n"
					+ "maDiaChi = '"+ khachHang.getDiaChi().getMaDiaChi() +"',\r\n"
					+ "soDienThoai = '"+ khachHang.getSoDienThoai() +"'\r\n"
					+ "where maKhachHang = '"+ khachHang.getMaKhachHang() +"'\r\n"
					+ "alter table KhachHang check constraint all\r\n"
					+ "alter table TaiKhoan check constraint all\r\n"
					+ "alter table NhanVien check constraint all";
		}
		else {
			sql = "alter table KhachHang nocheck constraint all\r\n"
					+ "alter table TaiKhoan nocheck constraint all\r\n"
					+ "alter table NhanVien nocheck constraint all\r\n"
					+ "update TaiKhoan\r\n"
					+ "set soDienThoai = '"+ khachHang.getSoDienThoai() +"',\r\n"
					+ "email = '"+ khachHang.getEmail() +"'\r\n"
					+ "where soDienThoai = '"+ soDienThoaiCu +"'\r\n"
					+ "update NhanVien\r\n"
					+ "set soDienThoai = '"+ khachHang.getSoDienThoai() +"'\r\n"
					+ "where soDienThoai = '"+ soDienThoaiCu +"'\r\n"
					+ "update KhachHang\r\n"
					+ "set tenKhachHang = N'"+ khachHang.getTenKhachHang() +"',\r\n"
					+ "gioiTinh = '"+ khachHang.isGioiTinh() +"',\r\n"
					+ "ngaySinh = '"+ khachHang.getNgaySinh() +"',\r\n"
					+ "maDiaChi = '"+ khachHang.getDiaChi().getMaDiaChi() +"',\r\n"
					+ "soDienThoai = '"+ khachHang.getSoDienThoai() +"'\r\n"
					+ "where maKhachHang = '"+ khachHang.getMaKhachHang() +"'\r\n"
					+ "alter table KhachHang check constraint all\r\n"
					+ "alter table TaiKhoan check constraint all\r\n"
					+ "alter table NhanVien check constraint all";
		}
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	public static KhachHang getKhachHang(String maKhachHang) {
		KhachHang khachHang = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT *\r\n"
				+ "FROM     KhachHang AS kh INNER JOIN\r\n"
				+ "                  TaiKhoan AS tk ON kh.soDienThoai = tk.soDienThoai INNER JOIN\r\n"
				+ "                  DiaChi AS dc ON kh.maDiaChi = dc.maDiaChi\r\n"
				+ "				  where maKhachHang = '"+ maKhachHang +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				khachHang = new KhachHang();
				khachHang.setMaKhachHang(rs.getString("maKhachHang"));
				khachHang.setTenKhachHang(rs.getString("tenKhachHang"));
				khachHang.setGioiTinh(rs.getBoolean("gioiTinh"));
				khachHang.setNgaySinh(rs.getDate("ngaySinh"));
				khachHang.setSoDienThoai(rs.getString("soDienThoai"));
				khachHang.setEmail(rs.getString("email"));
				khachHang.setDiaChi(DiaChi_DAO.getDiaChi(rs.getString("maDiaChi")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return khachHang;
	}
	public static KhachHang getKhachHangBangSdt(String user) {
		KhachHang khachHang = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT *\r\n"
				+ "FROM     KhachHang AS kh INNER JOIN\r\n"
				+ "                  TaiKhoan AS tk ON kh.soDienThoai = tk.soDienThoai INNER JOIN\r\n"
				+ "                  DiaChi AS dc ON kh.maDiaChi = dc.maDiaChi\r\n"
				+ "				  where kh.soDienThoai = '"+ user +"'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				khachHang = new KhachHang();
				khachHang.setMaKhachHang(rs.getString("maKhachHang"));
				khachHang.setTenKhachHang(rs.getString("tenKhachHang"));
				khachHang.setGioiTinh(rs.getBoolean("gioiTinh"));
				khachHang.setNgaySinh(rs.getDate("ngaySinh"));
				khachHang.setSoDienThoai(rs.getString("soDienThoai"));
				khachHang.setEmail(rs.getString("email"));
				khachHang.setDiaChi(DiaChi_DAO.getDiaChi(rs.getString("maDiaChi")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return khachHang;
	}
}
