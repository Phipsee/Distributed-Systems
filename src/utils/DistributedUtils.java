package utils;

public class DistributedUtils {
	
	public static boolean isStringEmptyOrNull(String s){
		if(s == null) {
			return true;
		}
		if(s.length() == 0) {
			return true;
		}
		return false;
	}

}
