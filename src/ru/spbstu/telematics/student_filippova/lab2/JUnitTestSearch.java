package ru.spbstu.telematics.student_filippova.lab2;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class JUnitTestSearch {

	@Test
	public void testPop() {
		Stack<Integer> stack = new Stack<>();
		MyStack<Integer> mstack = new MyStack<>();
		
		stack.push(0);
		mstack.Push(0);
		
		stack.push(1);
		mstack.Push(1);
		
		stack.push(2);
		mstack.Push(2);
		
		assertEquals(stack.search(0), mstack.Search(0));

	}

}
