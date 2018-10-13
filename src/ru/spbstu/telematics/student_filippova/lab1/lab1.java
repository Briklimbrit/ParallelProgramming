package ru.spbstu.telematics.student_filippova.lab1;

public class lab1 {//здесь будет main и демонстрация работы

	public static void main(String[] args) {
		Matrix first = new Matrix();
		Matrix second = new Matrix();
		first.print();
		System.out.println();
		second.print();
		int[][] res = first.add(second);
		System.out.println();
		for (int i = 0; i < first.DIMENTION; i++){
			for(int j = 0; j < first.DIMENTION; j++){
				System.out.print(res[i][j] + "  ");
			}
			System.out.println();
		}
	}

}
