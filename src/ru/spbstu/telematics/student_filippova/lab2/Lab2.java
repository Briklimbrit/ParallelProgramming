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
		
		System.out.println("Итераций до нуля чужой" + stack.search(0));
		System.out.println("Итераций до нуля мой" + mstack.Search("0"));

		System.out.println("Текущий стек: " + stack);
		System.out.println("Удаляем: " + stack.pop());
		System.out.println("Удаляем: " + stack.pop());
		System.out.println("Удаляем: " + stack.pop());
		System.out.println("Текущий стек: " + stack);
		
		System.out.println("Текущий стек: " + mstack);
		System.out.println("Удаляем: " + mstack.Pop());
		System.out.println("Удаляем: " + mstack.Pop());
		System.out.println("Удаляем: " + mstack.Pop());
		System.out.println("Текущий стек: " + mstack);

		
	}

}
