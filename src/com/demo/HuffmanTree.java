package com.demo;

public class HuffmanTree {

	private int data;
	private int minValue;
	private int maxValue;
	private HuffmanTree parent;
	private HuffmanTree left;
	private HuffmanTree right;

	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public HuffmanTree getLeft() {
		return left;
	}
	public void setLeft(HuffmanTree left) {
		this.left = left;
	}
	public HuffmanTree getRight() {
		return right;
	}
	public void setRight(HuffmanTree right) {
		this.right = right;
	}
	public HuffmanTree getParent() {
		return parent;
	}
	public void setParent(HuffmanTree parent) {
		this.parent = parent;
	}

	public boolean hasLeft() {
		return null != getLeft();
	}
	
	public boolean hasRight() {
		return null != getRight();
	}

	public boolean hasChildren() {
		return (hasLeft() && hasRight());
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public boolean isLeft() {
		return getParent().getLeft() == this;
	}

	public boolean isRight() {
		return getParent().getRight() == this;
	}

}
