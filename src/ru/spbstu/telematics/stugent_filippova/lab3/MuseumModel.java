package ru.spbstu.telematics.stugent_filippova.lab3;



public class MuseumModel {
	public static void main(String[] args) {
		Museum museum = new Museum();//������ �����
		Director director = new Director(museum);//������ ��������
		West west = new West(museum);//�������� ���� �����
		East east = new East(west, museum);//��������� ���� ����
		Controller controller = new Controller(museum, east, west);//���������� 


		//������
		Thread eastThread = new Thread(east, "enter");
		Thread westThread = new Thread(west, "exit");
		Thread directorThread = new Thread(director, "director");
		Thread controllerThread = new Thread(controller,  "controller");
		
		//�� ������
		eastThread.start();
		westThread.start();
		for (int i = 0; i < 20; i++) {
			east.enterQueue.add(new Visitor(i));
			System.out.println("Visitor"+i+" is waiting to enter");
		}
		
		controllerThread.start();
		directorThread.start();
	}

}