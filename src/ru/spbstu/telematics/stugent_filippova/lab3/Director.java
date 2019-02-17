package ru.spbstu.telematics.stugent_filippova.lab3;

public class Director implements Runnable {
    private Museum museum; //����� � �����

    public Director(Museum museum) {
        this.museum = museum;
    }

    public void openMuseum() {//������� ����� ������� �����
        museum.isOpened = true;
        System.out.println("Director: <<Open the museum!>>");
    }

    public void closeMuseum() {//������� �� �������� �����
        museum.isOpened = false;
        System.out.println("Director: <<Close the museum!>>");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (museum.dummy) {
                closeMuseum();//����������� �������� � �������� ����� ����� ������ �����
                museum.dummy.notify();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            openMuseum();
            synchronized (museum.dummy) {
                museum.dummy.notify();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!museum.alive){
                System.out.println("Director ended");
                break;
            }
        }
    }
}