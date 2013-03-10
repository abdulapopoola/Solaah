package com.codekraft.data;

public class Settings {

	public static boolean Fajr = false;
	public static boolean Sunrise = false;
	public static boolean Zuhr = false;
	public static boolean Asr = false;
	public static boolean Maghrib = false;
	public static boolean Isha = false;
	
	public boolean isFajr() {
		return Fajr;
	}
	public void setFajr(boolean fajr) {
		Fajr = fajr;
	}
	public boolean isSunrise() {
		return Sunrise;
	}
	public void setSunrise(boolean sunrise) {
		Sunrise = sunrise;
	}
	public boolean isZuhr() {
		return Zuhr;
	}
	public void setZuhr(boolean zuhr) {
		Zuhr = zuhr;
	}
	public boolean isAsr() {
		return Asr;
	}
	public void setAsr(boolean asr) {
		Asr = asr;
	}
	public boolean isMaghrib() {
		return Maghrib;
	}
	public void setMaghrib(boolean maghrib) {
		Maghrib = maghrib;
	}
	public boolean isIsha() {
		return Isha;
	}
	public void setIsha(boolean isha) {
		Isha = isha;
	}
	
}
