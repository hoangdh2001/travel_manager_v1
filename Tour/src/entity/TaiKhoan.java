package entity;

public class TaiKhoan {
	private String soDienThoai;
	private String email;
	private String matKhau;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the matKhau
	 */
	public String getMatKhau() {
		return matKhau;
	}
	/**
	 * @param matKhau the matKhau to set
	 */
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	/**
	 * @param soDienThoai
	 * @param email
	 * @param matKhau
	 */
	public TaiKhoan(String soDienThoai, String email, String matKhau) {
		super();
		setSoDienThoai(soDienThoai);
		setEmail(email);
		setMatKhau(matKhau);
	}
	
	public TaiKhoan() {
		// TODO Auto-generated constructor stub
	}
}
