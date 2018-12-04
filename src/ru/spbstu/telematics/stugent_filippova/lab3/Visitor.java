package ru.spbstu.telematics.stugent_filippova.lab3;


public class Visitor {
	public int num;
	public Visitor(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Visitor"+num;
	}
}