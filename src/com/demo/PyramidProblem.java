package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	

	public static void main(String[] args) throws IOException {
		
		String fileName = "input.txt";
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		String input = "";
		int length=0;
		while((line = br.readLine()) != null){
		    input = input + line+"\n";
		    length++;
		}
		br.close();
		Scanner scanner = new Scanner(input);
		Node a[][] = new Node[length][length];
		for(int i =0; i < a.length; i++) {
			for(int j = 0; j<=i; j++) {
				Node n = new Node();
				n.setValue(scanner.nextInt());
				n.setEven(isEven(n.getValue()));
				a[i][j] = n; 
			}
		}
		scanner.close();
		
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
	
	// used to print the status of the matrix
	private static void printMatrix (Node[][] a) {
		for(int i =0; i < a.length; i++) {
			for(int j = 0; j<=i; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
