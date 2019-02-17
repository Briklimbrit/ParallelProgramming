package ru.spbstu.telematics.stugent_filippova.lab3;

import java.util.LinkedList;
import java.util.Queue;


public class East implements Runnable{
	private Museum museum; //знает о музее
	private boolean alive = true;


	private volatile boolean enterOpened = false;//переменная о состоянии открыто\закрыто ВХОД
	public Queue<Visitor> enterQueue = new LinkedList<Visitor>();//очередь вошедших 
	private West west;//объект ВЫХОД
	
	public East(West west, Museum museum) {
		this.west = west;
		this.museum = museum;
	}
	
	public void open(){
		enterOpened = true;
	}
	public boolean isAlive(){return alive;}
	public void close(){
		enterOpened = false;
	}
	
	private void pass() throws InterruptedException{
		//if (!enterQueue.isEmpty()){//если количество людей внутри не ноль
			Visitor newVisitor = enterQueue.poll();//удаляем первого вошедшего
			west.exitQueue.add(newVisitor);//отправляем его в очередь вышедших
			System.out.println(newVisitor+" entered the museum");//такой то там вошел в музей
		//}
	}
	
	@Override
	public void run() {
		boolean ifOpened = false;
		while(true){
			synchronized (museum.dummy) {
				if (enterOpened) {
					ifOpened = true;
				}
			}
			if (ifOpened){//если вход открыт
				try {
					if (enterQueue.isEmpty()){
						System.out.println("East ended");
						alive = false;
						break;
					}else {
						pass();//запускать внутрь
					}
					ifOpened = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}