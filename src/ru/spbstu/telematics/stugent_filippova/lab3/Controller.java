package ru.spbstu.telematics.stugent_filippova.lab3;

public class Controller implements Runnable {
    private Museum museum;
    private East east;
    private West west;


    public Controller(Museum museum, East east, West west) {
        this.museum = museum;
        this.east = east;
        this.west = west;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (museum.dummy) {
            while (true) {
                try {
                    //System.out.println(name + " ���� ����� ������ notify ");
                    museum.dummy.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println(name + "'� ��� ������ notify");

                if (museum.isOpened) {//���� ����� ������ ��������� ���� � ��������� �����
                    east.open();
                    west.close();
                    System.out.println("Controller opened the museum");

                } else {//���� ����� ������ ��������� ���� � ��������� �����
                    east.close();
                    west.open();
                    System.out.println("Controller closed the museum");

                }
                if(!east.isAlive() && !west.isAlive()){
                    museum.alive = false;
                    System.out.println("Controller ended");
                    break;
                }
            }
        }
    }
}