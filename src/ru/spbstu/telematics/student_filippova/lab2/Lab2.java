package ru.spbstu.telematics.student_filippova.lab2;

import java.util.Stack;

public class Lab2 {

	public static void main(String[] args) {

		Stack<Integer> stack = new Stack<>();
		MyStack<String> mstack = new MyStack<>();
		
		stack.push(0);
		mstack.Push("0");
		
		stack.push(1);
		mstack.Push("I");
		
		stack.push(2);
		mstack.Push("II");
		
		for(Integer i: stack )
		{
			System.out.println(i);
		}
		
		for(String i: mstack )
		{
			System.out.println(i);
		}
		
		System.out.println("�������� �� ���� �����" + stack.search(0));
		System.out.println("�������� �� ���� ���" + mstack.Search("0"));

		System.out.println("������� ����: " + stack);
		System.out.println("�������: " + stack.pop());
		System.out.println("�������: " + stack.pop());
		System.out.println("�������: " + stack.pop());
		System.out.println("������� ����: " + stack);
		
		System.out.println("������� ����: " + mstack);
		System.out.println("�������: " + mstack.Pop());
		System.out.println("�������: " + mstack.Pop());
		System.out.println("�������: " + mstack.Pop());
		System.out.println("������� ����: " + mstack);

		
	}

}
