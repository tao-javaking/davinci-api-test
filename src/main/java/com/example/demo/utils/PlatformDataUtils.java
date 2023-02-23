package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cgj
 * @date 2022/8/25
 */
public class PlatformDataUtils {

	//WEB平台
	public static  Map<String,Object> WEB_MAP = new HashMap<>();
	//WAP平台
	public static  Map<String,Object> WAP_MAP = new HashMap<>();
	//AND平台
	public static  Map<String,Object> AND_MAP = new HashMap<>();
	//IOS平台
	public static  Map<String,Object> IOS_MAP = new HashMap<>();

	static {
		WEB_MAP.put("cookieEnabled", "1");
		WEB_MAP.put("hasLiedLanguages", "false");
		WEB_MAP.put("navigatorPlatform", "Win32");
		WEB_MAP.put("language", "zh-CN");
		WEB_MAP.put("hasLiedBrowser", "false");
		WEB_MAP.put("crossCode", "m39XHmjFBSW-1Bc8Rtn0yC6kgm5Bnymv");
		WEB_MAP.put("fontsHash", "e2f482f698766b7fefee1a69447396a4");
		WEB_MAP.put("availableScreenResolution", "834,1536");
		WEB_MAP.put("javaEnabled", "0");
		WEB_MAP.put("timezoneOffset", "-480");
		WEB_MAP.put("browserVersion", "98.0.4758.82");
		WEB_MAP.put("browserName", "Chrome");
		WEB_MAP.put("webdriver", "false");
		WEB_MAP.put("openDatabase", "true");
		WEB_MAP.put("screenResolution", "864,1536");
		WEB_MAP.put("adBlock", "false");
		WEB_MAP.put("webglVendorAndRenderer", "Google Inc. (Intel)~ANGLE (Intel, Intel(R) UHD Graphics Direct3D11 vs_5_0 ps_5_0, D3D11-27.20.100.9664)");
		WEB_MAP.put("browserEngine", "Blink");
		WEB_MAP.put("hasLiedOs", "false");
		WEB_MAP.put("indexedDb", "true");
		WEB_MAP.put("hasLiedResolution", "false");
		WEB_MAP.put("isRiskBrowser", "0");
		WEB_MAP.put("node", "192.168.15.24:9080");
		WEB_MAP.put("canvasHash", "dc265c440b9fbae15befc85d7f73eced");
		WEB_MAP.put("flashVersion", "0");
		WEB_MAP.put("custID", "default");
		WEB_MAP.put("webSmartID", "dc67a65941f49349721605f0319fd9e1");
		WEB_MAP.put("cookieCode", "EADN7WqtEb4KG0PzObzmN2nx6yGS6UbI");
		WEB_MAP.put("algID", "IOSAlg");
		WEB_MAP.put("mimeTypesHash", "fe9c964a38174deb6891b6523b8e4518");
		WEB_MAP.put("isIncognito", "0");
		WEB_MAP.put("touchSupport", "0,false,false");
		WEB_MAP.put("timezone", "Asia/Shanghai");
		WEB_MAP.put("localStorage", "true");
		WEB_MAP.put("webglHash", "01f0ff16eddb784d4346b7ad6204d695");
		WEB_MAP.put("pluginsHash", "2ee4df5f3ed7f5f93e45890a79b8af53");
		WEB_MAP.put("devicePixelRatio", "1.125");
		WEB_MAP.put("platform", "WEB");
		WEB_MAP.put("osVersion", "10");
		WEB_MAP.put("hashCode", "cloryBWfjf2ZEOh1IjG_nCVn3HeFIGuKuTpwzplGLJA");
		WEB_MAP.put("sessionStorage", "true");
		WEB_MAP.put("audio", "124.04347527516074");
		WEB_MAP.put("timestamp", System.currentTimeMillis());
		WEB_MAP.put("os", "Windows");
		WEB_MAP.put("passiveIP", "[, 192.168.15.24, , , Windows NT, 10.0]");
		WEB_MAP.put("cpuClass", "not available");
		WEB_MAP.put("isSelenium", "0");
		WEB_MAP.put("userAgent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36");
		WEB_MAP.put("updateTime", System.currentTimeMillis());
		WEB_MAP.put("deviceMemory", "8");
		WEB_MAP.put("addBehavior", "false");
		WEB_MAP.put("hardwareConcurrency", "8");
		WEB_MAP.put("doNotTrack", "unknown");
		WEB_MAP.put("passiveCode", "DkoPwRssNqXfwx3DEvnoFAeuu0Vve_6W");
		WEB_MAP.put("createTime", System.currentTimeMillis());
		WEB_MAP.put("crc64Code", "-3094836019188200");
		WEB_MAP.put("colorDepth", "24");

		WAP_MAP.put("cookieEnabled", "1");
		WAP_MAP.put("hasLiedLanguages", "false");
		WAP_MAP.put("navigatorPlatform", "Linux aarch64");
		WAP_MAP.put("language", "zh-CN");
		WAP_MAP.put("hasLiedBrowser", "false");
		WAP_MAP.put("crossCode", "6ys42kwauY8S06Oe4DV949QLAq6rXxIC");
		WAP_MAP.put("fontsHash", "d9382be63bd8af4153aaf07871b305c1");
		WAP_MAP.put("availableScreenResolution", "873,393");
		WAP_MAP.put("javaEnabled", "0");
		WAP_MAP.put("timezoneOffset", "-480");
		WAP_MAP.put("browserVersion", "16.7.27");
		WAP_MAP.put("browserName", "MIUI Browser");
		WAP_MAP.put("wapSmartID", "e2abd0c102c4c4f8d7fbb7a8b36a0d35");
		WAP_MAP.put("webdriver", "false");
		WAP_MAP.put("openDatabase", "true");
		WAP_MAP.put("screenResolution", "873,393");
		WAP_MAP.put("adBlock", "false");
		WAP_MAP.put("deviceType", "mobile");
		WAP_MAP.put("webglVendorAndRenderer", "Qualcomm~Adreno (TM) 619");
		WAP_MAP.put("browserEngine", "Blink");
		WAP_MAP.put("hasLiedOs", "false");
		WAP_MAP.put("indexedDb", "true");
		WAP_MAP.put("hasLiedResolution", "false");
		WAP_MAP.put("isRiskBrowser", "1");
		WAP_MAP.put("node", "192.168.15.24:9080");
		WAP_MAP.put("canvasHash", "98e859d5b3ebad7439e25fd938398330");
		WAP_MAP.put("flashVersion", "0");
		WAP_MAP.put("custID", "default");
		WAP_MAP.put("cookieCode", "IABZ2zvywIt5dz0j_atF1sk3kRJzCe9P");
		WAP_MAP.put("algID", "IOSAlg");
		WAP_MAP.put("mimeTypesHash", "d41d8cd98f00b204e9800998ecf8427e");
		WAP_MAP.put("isIncognito", "0");
		WAP_MAP.put("touchSupport", "5,true,true");
		WAP_MAP.put("timezone", "Asia/Shanghai");
		WAP_MAP.put("localStorage", "true");
		WAP_MAP.put("webglHash", "0d19db23b770aceeabaa5cb0964d1f51");
		WAP_MAP.put("pluginsHash", "d41d8cd98f00b204e9800998ecf8427e");
		WAP_MAP.put("devicePixelRatio", "2.75");
		WAP_MAP.put("platform", "WAP");
		WAP_MAP.put("osVersion", "10");
		WAP_MAP.put("hashCode", "g15DITBZrwA2CP709PmqbU-H7_-WqfcEuyNRVrih5Ks");
		WAP_MAP.put("sessionStorage", "true");
		WAP_MAP.put("audio", "124.08072787804849");
		WAP_MAP.put("timestamp", System.currentTimeMillis());
		WAP_MAP.put("os", "Android");
		WAP_MAP.put("passiveIP", "[, 192.168.14.2, , , , ]");
		WAP_MAP.put("cpuClass", "not available");
		WAP_MAP.put("isSelenium", "0");
		WAP_MAP.put("userAgent", "Mozilla/5.0 (Linux; U; Android 10; zh-cn; M2007J17C Build/QKQ1.200628.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/89.0.4389.116 Mobile Safari/537.36 XiaoMi/MiuiBrowser/16.7.27 swan-mibrowser");
		WAP_MAP.put("updateTime", System.currentTimeMillis());
		WAP_MAP.put("deviceMemory", "not available");
		WAP_MAP.put("addBehavior", "false");
		WAP_MAP.put("hardwareConcurrency", "8");
		WAP_MAP.put("doNotTrack", "unknown");
		WAP_MAP.put("passiveCode", "R3aOVI0dt2And_YASlzH_R3BjD8XSnjn");
		WAP_MAP.put("createTime", System.currentTimeMillis());
		WAP_MAP.put("crc64Code", "2840506382286377017");
		WAP_MAP.put("colorDepth", "24");

		AND_MAP.put("simCountry","cn");
		AND_MAP.put("appVersion","5.1.6");
		AND_MAP.put("sdkSmartID","4dfb1ef54dcd09a2c8c0ccfac9eb1114");
		AND_MAP.put("networkCountry","cn");
		AND_MAP.put("language","zh");
		AND_MAP.put("resolution","[1080,2179]");
		AND_MAP.put("isXposed","0");
		AND_MAP.put("timezoneOffset","+480");
		AND_MAP.put("isVPN","0");
		AND_MAP.put("fingerprint","Redmi/gauguinpro/gauguinpro:10/QKQ1.200628.002/V12.0.11.0.QJSCNXM:user/release-keys");
		AND_MAP.put("sensorListHash","c4dcda0c54dabefe");
		AND_MAP.put("wapSmartID","352d2ac7d25790b7f0abe3bfd22abccd");
		AND_MAP.put("model","M2007J17C");
		AND_MAP.put("brand","Redmi");
		AND_MAP.put("isProxy","0");
		AND_MAP.put("isMulti","0");
		AND_MAP.put("node","192.168.15.24:9080");
		AND_MAP.put("custID","234");
		AND_MAP.put("sdkVersion","5.1.6");
		AND_MAP.put("cookieCode","NRbnkMsBq3IYaCDtIVJ4yeWHkXzNe1TG");
		AND_MAP.put("algID","IOSAlg");
		AND_MAP.put("wifiListHash","23af56bf14e727f7");
		AND_MAP.put("gpsEnable","1");
		AND_MAP.put("systemID","QKQ1.200628.002");
		AND_MAP.put("networkOperator","46011");
		AND_MAP.put("timezone","Asia/Shanghai");
		AND_MAP.put("systemVersion","10");
		AND_MAP.put("isRooted","0");
		AND_MAP.put("platform","AND");
		AND_MAP.put("manufacturer","Xiaomi");
		AND_MAP.put("wifiMacAddress","9c:bc:f0:a1:2c:1d");
		AND_MAP.put("isDebug","0");
		AND_MAP.put("isVM","0");
		AND_MAP.put("hashCode","H8cj8RGM57mU-8U671iZJ2d8dJBtr5_vHBgeIMcQDdw");
		AND_MAP.put("localCode","172.16.1.145");
		AND_MAP.put("deviceNameHash","ed00d310623d41ba5f6964ff251cdd4f");
		AND_MAP.put("packageName","cn.com.bsfit.sdkdemo5_1");
		AND_MAP.put("ipInfoHash","89fa2fdee70b87b22babb76e2a0cb6e7");
		AND_MAP.put("networkType","WiFi");
		AND_MAP.put("batteryLevel","21");
		AND_MAP.put("timestamp",Long.toString(System.currentTimeMillis()));
		AND_MAP.put("phoneType","1");
		AND_MAP.put("product","gauguinpro");
		AND_MAP.put("passiveIP","[192.168.14.2, , , , 172.16.1.145, , ]");
		AND_MAP.put("display","QKQ1.200628.002test-keys");
		AND_MAP.put("startupTime","1661409251");
		AND_MAP.put("userAgent","Dalvik/2.1.0(Linux;U;Android10;M2007J17CMIUI/V12.0.11.0.QJSCNXM)");
		AND_MAP.put("updateTime",Long.toString(System.currentTimeMillis()));
		AND_MAP.put("cpuName","AArch64Processorrev14(aarch64)");
		AND_MAP.put("availableSystem","97357041664");
		AND_MAP.put("cpuABI","arm64-v8a");
		AND_MAP.put("brightness","894");
		AND_MAP.put("totalMemory","7742595072");
		AND_MAP.put("availableMemory","4459151360");
		AND_MAP.put("passiveCode","R3aOVI0dt2And_YASlzH_R3BjD8XSnjn");
		AND_MAP.put("batteryStatus","3");
		AND_MAP.put("createTime",Long.toString(System.currentTimeMillis()));
		AND_MAP.put("crc64Code","4207066068536352150");
		AND_MAP.put("totalSystem","114917617664");
		AND_MAP.put("board","gauguinpro");
		AND_MAP.put("androidID","4512e8d21f889b97");

		IOS_MAP.put("appVersion", "5.1.4");
		IOS_MAP.put("sdkSmartID", "64747789549cf74f0d752809a57968b2");
		IOS_MAP.put("networkCountry", "CN");
		IOS_MAP.put("IDFV", "BEDE61C0-0CE0-4A83-99BE-88342433D644");
		IOS_MAP.put("language", "zh-Hans-CN");
		IOS_MAP.put("resolution", "[750,1334]");
		IOS_MAP.put("timezoneOffset", "+480");
		IOS_MAP.put("isVPN", "0");
		IOS_MAP.put("currentWiFi", "[0e93b47c1f706036,80:89:17:ba:bc:2]");
		IOS_MAP.put("wapSmartID", "101c51f4c1a5788dd715839fe45c2d77");
		IOS_MAP.put("model", "iPhone8,1");
		IOS_MAP.put("debugTags", "[0,1]");
		IOS_MAP.put("dfpUUID", "CC1638D7-1ED3-4816-84C7-A0D2E9969D70");
		IOS_MAP.put("isCydia", "0");
		IOS_MAP.put("isProxy", "0");
		IOS_MAP.put("version", "15.5");
		IOS_MAP.put("wifiEnable", "1");
		IOS_MAP.put("isMulti", "1");
		IOS_MAP.put("node", "192.168.15.24:9080");
		IOS_MAP.put("custID", "456");
		IOS_MAP.put("sdkVersion", "5.1.4");
		IOS_MAP.put("cookieCode", "QAClZIVdWMHw81aCYtXdZ03QMlSOBAA5");
		IOS_MAP.put("algID", "IOSAlg");
		IOS_MAP.put("timezone", "Asia/Shanghai");
		IOS_MAP.put("isRooted", "0");
		IOS_MAP.put("platform", "IOS");
		IOS_MAP.put("isDebug", "0");
		IOS_MAP.put("isVM", "0");
		IOS_MAP.put("cloakTags", "000000");
		IOS_MAP.put("hashCode", "vcAFCZSUkC6hKtklq0ENQc8iBtFfzBtb_OeghStinMc");
		IOS_MAP.put("localCode", "172.16.1.135");
		IOS_MAP.put("deviceNameHash", "efac9b09c5fdaab3a294acf2891d68f9");
		IOS_MAP.put("packageName", "com.zjbskj.dfptest");
		IOS_MAP.put("ipInfoHash", "6875e10e6062c3c8ee8b671c8532610e");
		IOS_MAP.put("networkType", "WiFi");
		IOS_MAP.put("batteryLevel", "61");
		IOS_MAP.put("timestamp", System.currentTimeMillis());
		IOS_MAP.put("rootedTags", "[0,0,0]");
		IOS_MAP.put("passiveIP", "[, 172.16.1.135, 192.168.14.2, , ]");
		IOS_MAP.put("coordinates", "[120.112724-30.292261]");
		IOS_MAP.put("startupTime", "1661165742");
		IOS_MAP.put("updateTime", System.currentTimeMillis());
		IOS_MAP.put("availableSystem", "3615120578");
		IOS_MAP.put("brightness", "0.980032");
		IOS_MAP.put("totalMemory", "2107621376");
		IOS_MAP.put("vmTags", "0");
		IOS_MAP.put("passiveCode", "R3aOVI0dt2And_YASlzH_R3BjD8XSnjn");
		IOS_MAP.put("batteryStatus", "1");
		IOS_MAP.put("createTime", System.currentTimeMillis());
		IOS_MAP.put("crc64Code", "5099153907674154065");
		IOS_MAP.put("totalSystem", "15968497664");
		IOS_MAP.put("multiTags", "1");

	}
    

}