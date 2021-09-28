package entity;

import java.util.ArrayList;

public class CT_ThamQuan {
	private ArrayList<DiaDanh> dsdiaDanh;
	private ChuyenDi chuyenDi;
	/**
	 * @return the diaDanh
	 */
	public ArrayList<DiaDanh> getDsDiaDanh() {
		return dsdiaDanh;
	}
	/**
	 * @param diaDanh the diaDanh to set
	 */
	public void setDsDiaDanh(ArrayList<DiaDanh> dsdiaDanh) {
		this.dsdiaDanh = dsdiaDanh;
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
	 * @param diaDanh
	 * @param chuyenDi
	 */
	public CT_ThamQuan(ArrayList<DiaDanh> dsdiaDanh, ChuyenDi chuyenDi) {
		super();
		setDsDiaDanh(dsdiaDanh);
		setChuyenDi(chuyenDi);
	}
	public CT_ThamQuan() {
		// TODO Auto-generated constructor stub
	}
}
