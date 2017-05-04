package cn.itcast.shop.Utils;

import java.util.UUID;

public class UUidUtils {
	public static String getUUid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
