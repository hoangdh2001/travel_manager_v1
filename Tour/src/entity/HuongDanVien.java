package entity;

public class HuongDanVien {
	private String maHuongDanVien;
	private String tenHuongDanVien;
	private boolean gioiTinh;
	private String trangThai;
	private String soDienThoai;
	private DiaChi diaChi;
	/**
	 * @return the maHuongDanVien
	 */
	public String getMaHuongDanVien() {
		return maHuongDanVien;
	}
	/**
	 * @param maHuongDanVien the maHuongDanVien to set
	 */
	public void setMaHuongDanVien(String maHuongDanVien) {
		this.maHuongDanVien = maHuongDanVien;
	}
	/**
	 * @return the tenHuongDanVien
	 */
	public String getTenHuongDanVien() {
		return tenHuongDanVien;
	}
	/**
	 * @param tenHuongDanVien the tenHuongDanVien to set
	 */
	public void setTenHuongDanVien(String tenHuongDanVien) {
		this.tenHuongDanVien = tenHuongDanVien;
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
	 * @return the trangThai
	 */
	public String getTrangThai() {
		return trangThai;
	}
	/**
	 * @param trangThai the trangThai to set
	 */
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	/**
	 * @return the soDienThoai
	 */
	public String getSoDienThoai() {
		return soDienThoai;
	}
	/**
	 * @param soDienThoai the soDienThoai to set
	 */
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
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
	 * @param maHuongDanVien
	 * @param tenHuongDanVien
	 * @param gioiTinh
	 * @param trangThai
	 * @param soDienThoai
	 * @param diaChi
	 */
	public HuongDanVien(String maHuongDanVien, String tenHuongDanVien, boolean gioiTinh, String trangThai,
			String soDienThoai, DiaChi diaChi) {
		super();
		setMaHuongDanVien(maHuongDanVien);
		setTenHuongDanVien(tenHuongDanVien);
		setGioiTinh(gioiTinh);
		setTrangThai(trangThai);
		setSoDienThoai(soDienThoai);
		setDiaChi(diaChi);
	}
	public HuongDanVien() {
		// TODO Auto-generated constructor stub
	}
}
