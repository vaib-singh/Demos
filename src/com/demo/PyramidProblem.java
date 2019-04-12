package com.demo;

import java.util.Scanner;

class Node {
	private Integer value;
	private boolean isEven;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public boolean isEven() {
		return isEven;
	}
	public void setEven(boolean isEven) {
		this.isEven = isEven;
	}
	
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}


public class PyramidProblem {
	

	public static int max = 0;
	
	public static void main(String[] args) {
		
		String input =  "215\n" + 
						"192 124\n" + 
						"117 269 442\n" + 
						"218 836 347 235\n" + 
						"320 805 522 417 345\n" + 
						"229 601 728 835 133 124\n" + 
						"248 202 277 433 207 263 257\n" + 
						"359 464 504 528 516 716 871 182\n" + 
						"461 441 426 656 863 560 380 171 923\n" + 
						"381 348 573 533 448 632 387 176 975 449\n" + 
						"223 711 445 645 245 543 931 532 937 541 444\n" + 
						"330 131 333 928 376 733 017 778 839 168 197 197\n" + 
						"131 171 522 137 217 224 291 413 528 520 227 229 928\n" + 
						"223 626 034 683 839 052 627 310 713 999 629 817 410 121\n" + 
						"924 622 911 233 325 139 721 218 253 223 107 233 230 124 233";
		String[] lines = input.split("\r\n|\r|\n");
		Scanner scanner = new Scanner(input);
		Node a[][] = new Node[lines.length][lines.length];
		for(int i =0; i < a.length; i++) {
			for(int j = 0; j<=i; j++) {
				Node n = new Node();
				n.setValue(scanner.nextInt());
				n.setEven(isEven(n.getValue()));
				a[i][j] = n; 
			}
		}
		scanner.close();
		for(int i =0; i < a.length; i++) {
			for(int j = 0; j<=i; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		for (int i = a.length-2; i >= 0; i--) 
        { 
            for (int j = 0; j <= i; j++) 
            { 
            	if ((a[i + 1][j].isEven() == a[i][j].isEven()) && (a[i + 1][j+1].isEven() == a[i][j].isEven())) {
            		a[i][j].setValue(null);
            	}
            	
            	else if(((a[i + 1][j].isEven() != a[i][j].isEven()) && null != a[i + 1][j].getValue()) ||
            			(null != a[i + 1][j].getValue() && (a[i + 1][j].isEven() != a[i][j].isEven()) && (a[i + 1][j+1].isEven() != a[i][j].isEven()) && (a[i+1][j].getValue() > a[i+1][j].getValue()))) {
            		 a[i][j].setValue(a[i][j].getValue() + a[i + 1][j].getValue()); 
            	}
            	
            	else if (null == a[i+1][j+1].getValue()) {
            		a[i][j].setValue(null);
            	}
                else {
						a[i][j].setValue(a[i][j].getValue() + a[i+1][j+1].getValue());
                }
//            	printMatrix(a);
//            	System.out.println();
            }
            
        }
		System.out.println("Sum : " + a[0][0]);
		
	}

	private static boolean isEven(int num) {
		return num%2==0;
	}
	private static void printMatrix (Node[][] a) {
		for(int i =0; i < a.length; i++) {
			for(int j = 0; j<=i; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
