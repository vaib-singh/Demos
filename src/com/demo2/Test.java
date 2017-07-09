package com.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		int n = (int) Math.ceil((double) 10 / 3);
		Stack<Integer> left = new Stack<Integer>();
		int min =1, max=4, count=0, freq = 8;
		List<Integer> lst1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
		List<Integer>  lst2 = new ArrayList<Integer>(Arrays.asList(2, 1, 3, 4));
        List<Integer>  lst3 = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5));

        System.out.println(lst1.equals(lst2));
		left.push(4);
		left.push(4);
		left.push(4);
		left.push(4);
		Stack<Integer> right = new Stack<Integer>();
		right.push(4);
		boolean check = false;
		boolean complete = false;
		while (check) {
			System.out.println(left + ":" + right);
			if(left.peek() >= max && right.peek() >= max) {
				right.pop();
				right.push(1);
				left.pop();
				right.push(1);
				int ct = 0;
				while(right.size() !=1) {
					System.out.println(left + ":" + right);
					if(left.size() == 0) {
						complete = true;
						break;
					}
					if(left.peek()>=max) {
						left.pop();
						right.push(1);
					}
					else {
						if(left.peek()<max && ct ==0) {
							left.push(left.pop()+1);
							ct++;
						}	
						left.push(right.pop());
					}
				}
				if(complete) {
					break;
				}
			} 
			else if(left.peek() < max && right.peek()>=max){
				left.push(left.pop()+1);
				right.pop();
				right.push(1);
			}
			else if (right.peek() >= max) {
				right.pop();
				right.push(1);
				
			}
			else if(right.peek()< max) {
				right.push(right.pop() + 1);
			}
		} 
	}
	
}
