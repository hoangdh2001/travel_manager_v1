package entity;

public class NhanVien extends TaiKhoan {
	private String maNhanVien;
	private String tenNhanVien;
	private boolean gioiTinh;
	private boolean trangThai;
	private DiaChi diaChi;
	/**
	 * @return the maNhanVien
	 */
	public String getMaNhanVien() {
		return maNhanVien;
	}
	/**
	 * @param maNhanVien the maNhanVien to set
	 */
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	/**
	 * @return the tenNhanVien
	 */
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	/**
	 * @param tenNhanVien the tenNhanVien to set
	 */
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
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
	public boolean isTrangThai() {
		return trangThai;
	}
	/**
	 * @param trangThai the trangThai to set
	 */
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
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
	 * @param maNhanVien
	 * @param tenNhanVien
	 * @param gioiTinh
	 * @param trangThai
	 * @param loaiNhanVien
	 * @param diaChi
	 */
	public NhanVien(String soDienThoai, String email, String matKhau, String maNhanVien, String tenNhanVien,
			boolean gioiTinh, boolean trangThai, DiaChi diaChi) {
		super(soDienThoai, email, matKhau);
		setMaNhanVien(maNhanVien);
		setTenNhanVien(tenNhanVien);
		setGioiTinh(gioiTinh);
		setTrangThai(trangThai);
		setDiaChi(diaChi);
	}
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}
}
