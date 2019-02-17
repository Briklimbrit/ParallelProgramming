package ru.spbstu.telematics.stugent_filippova.lab3;

import java.util.LinkedList;
import java.util.Queue;


public class West implements Runnable {
	private Museum museum; //����� � �����
	private volatile boolean  exitOpened = false; //���������� � ���������� ������ �������/�������
	private boolean worked = false;
	private boolean alive = true;
	public Queue<Visitor> exitQueue = new LinkedList<Visitor>();//������� ��������
	public West(Museum museum) {
		this.museum = museum;
	}

	public void open() {
		exitOpened = true;
	}
	public boolean isAlive(){return alive;}

	public void close() {
		exitOpened = false;
	}

	@Override
	public void run() {
		boolean ifOpened = false;
		while (true) {

			synchronized (museum.dummy) {
				if (exitOpened) {
					ifOpened = true;
				}
			}

			if (ifOpened) {//���� ����� ������
				try {
					if(exitQueue.isEmpty() && worked){
						System.out.println("West ended");
						alive = false;
						break;
					}
					if(!exitQueue.isEmpty()){
						pass();//��������� �����
					}
					ifOpened = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void pass() throws InterruptedException {
		//if (!exitQueue.isEmpty()) {//���� ������� �� ����� �� �����
			Visitor oldVisitor = exitQueue.poll();//������� ������, ��������� �� �������
			System.out.println(oldVisitor + " left the museum");//����� � ��� ��� ����
			worked = true;
		//}

	}

}