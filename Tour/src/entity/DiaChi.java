package entity;

public class DiaChi {
	private String maDiaChi;
	private String tinhThanh;
	private String quanHuyen;
	private String phuongXa;
	/**
	 * @return the maDiaChi
	 */
	public String getMaDiaChi() {
		return maDiaChi;
	}
	/**
	 * @param maDiaChi the maDiaChi to set
	 */
	public void setMaDiaChi(String maDiaChi) {
		this.maDiaChi = maDiaChi;
	}
	/**
	 * @return the tinhThanh
	 */
	public String getTinhThanh() {
		return tinhThanh;
	}
	/**
	 * @param tinhThanh the tinhThanh to set
	 */
	public void setTinhThanh(String tinhThanh) {
		this.tinhThanh = tinhThanh;
	}
	/**
	 * @return the quanHuyen
	 */
	public String getQuanHuyen() {
		return quanHuyen;
	}
	/**
	 * @param quanHuyen the quanHuyen to set
	 */
	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}
	/**
	 * @return the phuongXa
	 */
	public String getPhuongXa() {
		return phuongXa;
	}
	/**
	 * @param phuongXa the phuongXa to set
	 */
	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}
	/**
	 * @param maDiaChi
	 * @param tinhThanh
	 * @param quanHuyen
	 * @param phuongXa
	 */
	public DiaChi(String maDiaChi, String tinhThanh, String quanHuyen, String phuongXa) {
		super();
		setMaDiaChi(maDiaChi);
		setTinhThanh(tinhThanh);
		setQuanHuyen(quanHuyen);
		setPhuongXa(phuongXa);
	}
	public DiaChi() {
		// TODO Auto-generated constructor stub
	}
	
}
