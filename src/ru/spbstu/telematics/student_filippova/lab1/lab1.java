package ru.spbstu.telematics.student_filippova.lab1;

public class Lab1 {//здесь будет main и демонстрация работы

	public static void main(String[] args) {
		Matrix first = new Matrix();
		Matrix second = new Matrix();
		//first.print();
		//System.out.println();
		//second.print();
		Matrix res = first.add(second);
		System.out.println();
		System.out.println(first.toString());
		System.out.println(second);
		System.out.println(res);
		
	}

}
