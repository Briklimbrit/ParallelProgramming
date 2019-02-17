package ru.spbstu.telematics.stugent_filippova.lab3;

import java.util.LinkedList;
import java.util.Queue;


public class East implements Runnable{
	private Museum museum; //����� � �����
	private boolean alive = true;


	private volatile boolean enterOpened = false;//���������� � ��������� �������\������� ����
	public Queue<Visitor> enterQueue = new LinkedList<Visitor>();//������� �������� 
	private West west;//������ �����
	
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
		//if (!enterQueue.isEmpty()){//���� ���������� ����� ������ �� ����
			Visitor newVisitor = enterQueue.poll();//������� ������� ���������
			west.exitQueue.add(newVisitor);//���������� ��� � ������� ��������
			System.out.println(newVisitor+" entered the museum");//����� �� ��� ����� � �����
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
			if (ifOpened){//���� ���� ������
				try {
					if (enterQueue.isEmpty()){
						System.out.println("East ended");
						alive = false;
						break;
					}else {
						pass();//��������� ������
					}
					ifOpened = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}