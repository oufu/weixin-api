package com.sanyka.weixin.utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		Date d = Tools.strDt2Dt("2017-09-28 17:10:10");
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("11232");
				this.cancel();
			}
		}, d);
		d = Tools.strDt2Dt("2017-09-28 17:10:20");
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("11232");
				this.cancel();
			}
		}, d);
		d = Tools.strDt2Dt("2017-09-28 17:10:30");
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("11232");
				this.cancel();
			}
		}, d);
		d = Tools.strDt2Dt("2017-09-28 17:10:40");
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("11232");
				this.cancel();
			}
		}, d);
		d = Tools.strDt2Dt("2017-09-28 17:10:50");
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("11232");
				this.cancel();
			}
		}, d);
//		timer.cancel();
	}
}
