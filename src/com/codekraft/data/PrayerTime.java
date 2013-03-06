package com.codekraft.data;

public enum PrayerTime {
	FAJR {
		public String toString() {
			return "Fajr";
		}
	},
	SUNRISE {
		public String toString() {
			return "Sunrise";
		}
	},
	ZUHR {
		public String toString() {
			return "Zuhr";
		}
	},
	ASR {
		public String toString() {
			return "Asr";
		}
	},
	MAGHRIB {
		public String toString() {
			return "Maghrib";
		}
	},
	ISHA {
		public String toString() {
			return "Isha";
		}
	}
}