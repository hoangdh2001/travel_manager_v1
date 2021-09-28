package entity;

import java.sql.Date;

public class ChuyenDi {
	private String maChuyenDi;
	private Date ngayKhoiHanh;
	private Date ngayKetThuc;
	private int soCho;
	private int soChoConNhan;
	private double giaChuyenDi;
	private DiaChi noiKhoiHanh;
	private LoaiChuyenDi loaiChuyenDi;
	private String trangThai;
	private String moTa;
	private HuongDanVien huongDanVien;
	/**
	 * @return the maChuyenDi
	 */
	public String getMaChuyenDi() {
		return maChuyenDi;
	}
	/**
	 * @param maChuyenDi the maChuyenDi to set
	 */
	public void setMaChuyenDi(String maChuyenDi) {
		this.maChuyenDi = maChuyenDi;
	}
	/**
	 * @return the ngayKhoiHanh
	 */
	public Date getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}
	/**
	 * @param ngayKhoiHanh the ngayKhoiHanh to set
	 */
	public void setNgayKhoiHanh(Date ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}
	/**
	 * @return the ngayKetThuc
	 */
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	/**
	 * @param ngayKetThuc the ngayKetThuc to set
	 */
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	/**
	 * @return the soCho
	 */
	public int getSoCho() {
		return soCho;
	}
	/**
	 * @param soCho the soCho to set
	 */
	public void setSoCho(int soCho) {
		this.soCho = soCho;
	}
	/**
	 * @return the soChoConNhan
	 */
	public int getSoChoConNhan() {
		return soChoConNhan;
	}
	/**
	 * @param soChoConNhan the soChoConNhan to set
	 */
	public void setSoChoConNhan(int soChoConNhan) {
		this.soChoConNhan = soChoConNhan;
	}
	/**
	 * @return the giaChuyenDi
	 */
	public double getGiaChuyenDi() {
		return giaChuyenDi;
	}
	/**
	 * @param giaChuyenDi the giaChuyenDi to set
	 */
	public void setGiaChuyenDi(double giaChuyenDi) {
		this.giaChuyenDi = giaChuyenDi;
	}
	/**
	 * @return the noiKhoiHanh
	 */
	public DiaChi getNoiKhoiHanh() {
		return noiKhoiHanh;
	}
	/**
	 * @param noiKhoiHanh the noiKhoiHanh to set
	 */
	public void setNoiKhoiHanh(DiaChi noiKhoiHanh) {
		this.noiKhoiHanh = noiKhoiHanh;
	}
	/**
	 * @return the loaiChuyenDi
	 */
	public LoaiChuyenDi getLoaiChuyenDi() {
		return loaiChuyenDi;
	}
	/**
	 * @param loaiChuyenDi the loaiChuyenDi to set
	 */
	public void setLoaiChuyenDi(LoaiChuyenDi loaiChuyenDi) {
		this.loaiChuyenDi = loaiChuyenDi;
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
	 * @return the moTa
	 */
	public String getMoTa() {
		return moTa;
	}
	/**
	 * @param moTa the moTa to set
	 */
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	/**
	 * @return the nhanVien
	 */
	public HuongDanVien getHuongDanVien() {
		return huongDanVien;
	}
	/**
	 * @param nhanVien the nhanVien to set
	 */
	public void setHuongDanVien(HuongDanVien huongDanVien) {
		this.huongDanVien = huongDanVien;
	}
	/**
	 * @param maChuyenDi
	 * @param ngayKhoiHanh
	 * @param ngayKetThuc
	 * @param soCho
	 * @param soChoConNhan
	 * @param noiKhoiHanh
	 * @param loaiChuyenDi
	 * @param trangThai
	 * @param moTa
	 * @param huongDanVien
	 */
	public ChuyenDi(String maChuyenDi, Date ngayKhoiHanh, Date ngayKetThuc, int soCho, int soChoConNhan, Double giaChuyenDi,
			DiaChi noiKhoiHanh, LoaiChuyenDi loaiChuyenDi, String trangThai, String moTa, HuongDanVien huongDanVien) {
		super();
		setMaChuyenDi(maChuyenDi);
		setNgayKhoiHanh(ngayKhoiHanh);
		setNgayKetThuc(ngayKetThuc);
		setSoCho(soChoConNhan);
		setSoChoConNhan(soChoConNhan);
		setGiaChuyenDi(giaChuyenDi);
		setNoiKhoiHanh(noiKhoiHanh);
		setLoaiChuyenDi(loaiChuyenDi);
		setTrangThai(trangThai);
		setMoTa(moTa);
		setHuongDanVien(huongDanVien);
	}
	public ChuyenDi() {
		// TODO Auto-generated constructor stub
	}
}