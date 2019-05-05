package br.com.plantier.loucademia.domain.application.util;

public class StringUtils {

	public static boolean isEmpty(String s) {
		if(s == null)
			return true;
		
		return s.trim().length() == 0;
	}
	
	public static String leftZeros(int value, int finalSize) {
		return String.format("%0" + finalSize + "d", value);
	}
	
}