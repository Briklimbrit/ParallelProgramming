package ru.spbstu.telematics.stugent_filippova.lab3;


public class Visitor {
	public int num;//имет лишь индивидуальный номер
	
	public Visitor(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Visitor"+num;
	}
}