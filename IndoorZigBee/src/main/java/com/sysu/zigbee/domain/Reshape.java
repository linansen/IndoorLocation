package com.sysu.zigbee.domain;

public class Reshape {
	private double[]x;
	private int row;
	private int col;
	public Reshape(double []x,int row,int col){
		if(x.length==row*col){
			this.x=x;
			this.row=row;
			this.col=col;
		}
		else{
			System.out.println("矩阵维度不匹配");
		}
	}
	public double[][] shape(){
		double y[][]=new double[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				y[i][j]=x[i*col+j];
			}
		}
		return y;
	}
}
