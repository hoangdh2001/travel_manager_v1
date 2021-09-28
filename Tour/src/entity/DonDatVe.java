package entity;

import java.sql.Date;

public class DonDatVe {
	private String maDonDatVe;
	private Date ngayDatVe;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private double tongTien;
	private int soLuong;
	private ChuyenDi chuyenDi;
	/**
	 * @return the maDonDatVe
	 */
	public String getMaDonDatVe() {
		return maDonDatVe;
	}
	/**
	 * @param maDonDatVe the maDonDatVe to set
	 */
	public void setMaDonDatVe(String maDonDatVe) {
		this.maDonDatVe = maDonDatVe;
	}
	/**
	 * @return the ngayDatVe
	 */
	public Date getNgayDatVe() {
		return ngayDatVe;
	}
	/**
	 * @param ngayDatVe the ngayDatVe to set
	 */
	public void setNgayDatVe(Date ngayDatVe) {
		this.ngayDatVe = ngayDatVe;
	}
	/**
	 * @return the khachHang
	 */
	public KhachHang getKhachHang() {
		return khachHang;
	}
	/**
	 * @param khachHang the khachHang to set
	 */
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	/**
	 * @return the nhanVien
	 */
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	/**
	 * @param nhanVien the nhanVien to set
	 */
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	/**
	 * @return the tongTien
	 */
	public double getTongTien() {
		return tongTien;
	}
	/**
	 * @param tongTien the tongTien to set
	 */
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	
	/**
	 * @return the soLuong
	 */
	public int getSoLuong() {
		return soLuong;
	}
	/**
	 * @param soLuong the soLuong to set
	 */
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	/**
	 * @return the chuyenDi
	 */
	public ChuyenDi getChuyenDi() {
		return chuyenDi;
	}
	/**
	 * @param chuyenDi the chuyenDi to set
	 */
	public void setChuyenDi(ChuyenDi chuyenDi) {
		this.chuyenDi = chuyenDi;
	}
	/**
	 * @param maDonDatVe
	 * @param ngayDatVe
	 * @param khachHang
	 * @param nhanVien
	 * @param tongTien
	 */
	public DonDatVe(String maDonDatVe, Date ngayDatVe, KhachHang khachHang, NhanVien nhanVien, double tongTien, int soLuong, ChuyenDi chuyenDi) {
		super();
		setMaDonDatVe(maDonDatVe);
		setNgayDatVe(ngayDatVe);
		setKhachHang(khachHang);
		setNhanVien(nhanVien);
		setTongTien(tongTien);
		setSoLuong(soLuong);
		setChuyenDi(chuyenDi);
	}
	public DonDatVe() {
		// TODO Auto-generated constructor stub
	}
}
