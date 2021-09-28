package dao;

public class PhatSinhMaTuDong {
	public static String getMa(String cuoi, String hau, String ID, int soLuong) {
		int number = Integer.parseInt(ID);
		String maPhatSinh = null;
		maPhatSinh = String.format("%0"+ soLuong +"d", number + 1);
		return cuoi + hau + maPhatSinh;
	}
	public static String getMa(String cuoi, String ID, int soLuong) {
		int number = Integer.parseInt(ID);
		String maPhatSinh = "0000";
		maPhatSinh = String.format("%0"+ soLuong +"d", number + 1);
		return cuoi + maPhatSinh;
		
	}
}
