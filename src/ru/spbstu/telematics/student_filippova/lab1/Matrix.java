package ru.spbstu.telematics.student_filippova.lab1;

import java.util.Random;


public class Matrix {

	final int DIMENTION = 5;
	
	int[][] array;
	
	public Matrix() {
		Random rnd = new Random();
		int min = -100;
		int max = 100;
		
		array = new int[DIMENTION][DIMENTION];
		for (int i = 0; i < DIMENTION; i++){
			for(int j = 0; j < DIMENTION; j++){
				array[i][j] = min + rnd.nextInt(max - min + 1);
			}
		}
	}
	
	
	protected Matrix(int[][] result) {
		// TODO Auto-generated constructor stub
		this.array = result;
	}

	/*
	public void print() {
		for (int i = 0; i < DIMENTION; i++){
			for(int j = 0; j < DIMENTION; j++){
				System.out.print(array[i][j] + "  ");
			}
			System.out.println();
		}
	}
	*/
	public Matrix add(Matrix a) {
		int[][] result = new int[DIMENTION][DIMENTION];
		for (int i = 0; i < DIMENTION; i++){
			for(int j = 0; j < DIMENTION; j++){
				result[i][j] = this.array[i][j] + a.array[i][j];
			}
		}
		return new Matrix(result);
		
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < DIMENTION; i++){
			for(int j = 0; j < DIMENTION; j++){
				b.append(array[i][j] + "  ");
			}
			b.append('\n');
		}
		return b.toString();
	}

}
