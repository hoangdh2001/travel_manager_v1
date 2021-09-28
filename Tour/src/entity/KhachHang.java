package entity;

import java.sql.Date;

public class KhachHang extends TaiKhoan {
	private String maKhachHang;
	private String tenKhachHang;
	private boolean gioiTinh;
	private Date ngaySinh;
	private DiaChi diaChi;
	/**
	 * @return the maKhachHang
	 */
	public String getMaKhachHang() {
		return maKhachHang;
	}
	/**
	 * @param maKhachHang the maKhachHang to set
	 */
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	/**
	 * @return the tenKhachHang
	 */
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	/**
	 * @param tenKhachHang the tenKhachHang to set
	 */
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	/**
	 * @return the gioiTinh
	 */
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	/**
	 * @param gioiTinh the gioiTinh to set
	 */
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	/**
	 * @return the ngaySinh
	 */
	public Date getNgaySinh() {
		return ngaySinh;
	}
	/**
	 * @param ngaySinh the ngaySinh to set
	 */
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	/**
	 * @return the diaChi
	 */
	public DiaChi getDiaChi() {
		return diaChi;
	}
	/**
	 * @param diaChi the diaChi to set
	 */
	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}
	/**
	 * @param soDienThoai
	 * @param email
	 * @param matKhau
	 * @param maKhachHang
	 * @param tenKhachHang
	 * @param gioiTinh
	 * @param ngaySinh
	 * @param diaChi
	 */
	public KhachHang(String soDienThoai, String email, String matKhau, String maKhachHang, String tenKhachHang,
			boolean gioiTinh, Date ngaySinh, DiaChi diaChi) {
		super(soDienThoai, email, matKhau);
		setMaKhachHang(maKhachHang);
		setTenKhachHang(tenKhachHang);
		setGioiTinh(gioiTinh);
		setNgaySinh(ngaySinh);
		setDiaChi(diaChi);
	}
	public KhachHang() {
		// TODO Auto-generated constructor stub
	}
}