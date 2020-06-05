package com.main.application;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		HDDBST myBST = new HDDBST();
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 3; i++)
					myBST.put("key" + i, "val" + i);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 4; i <= 6; i++)
					myBST.put("key" + i, "val" + i);
			}
		});

		thread1.start();
		thread2.start();

		thread1.join(500);
		thread2.join(500);

		myBST.printLOT();
	}
}
