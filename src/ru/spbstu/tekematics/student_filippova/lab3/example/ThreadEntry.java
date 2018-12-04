package ru.spbstu.tekematics.student_filippova.lab3.example;


import java.util.Random;

public class ThreadEntry {
	
	static class FooContainer {
		
		private int num;

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
		
		
	}
	
	static FooContainer data = new FooContainer();

	public static void main(String[] args) throws Exception {
		System.out.println("I am startup thread " + Thread.currentThread().getName());
		
		DataProcessor p = new DataProcessor(data);
		//p.run();
		Thread t = new Thread(p);
		t.start();
		
		for (int i = 0; i < 5; i ++) {
			data.setNum(new Random().nextInt(100));
			System.out.println("I have written " + data.getNum());
			Thread.sleep(1000);
		}
		
		t.interrupt();
		t.join();
	}
}