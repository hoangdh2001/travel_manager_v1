package entity;

public class LoaiChuyenDi {
	private String maLoai;
	private String tenLoai;
	/**
	 * @return the maLoai
	 */
	public String getMaLoai() {
		return maLoai;
	}
	/**
	 * @param maLoai the maLoai to set
	 */
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	/**
	 * @return the tenLoai
	 */
	public String getTenLoai() {
		return tenLoai;
	}
	/**
	 * @param tenLoai the tenLoai to set
	 */
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	/**
	 * @param maLoai
	 * @param tenLoai
	 */
	public LoaiChuyenDi(String maLoai, String tenLoai) {
		super();
		setMaLoai(maLoai);
		setTenLoai(tenLoai);
	}
	public LoaiChuyenDi() {
		// TODO Auto-generated constructor stub
	}
}
