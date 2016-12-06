package com.sysu.zigbee.domain;

import java.util.Arrays;

/**
 * M序列生成器
 * @author 杰
 */
public class PNGen {
	private int g;
	private int init;
	private int Len;
	public PNGen(int g,int init,int Len){
		this.g=g;
		this.init=init;
		this.Len=Len;
	}
	public double[] gen(){
		String LenG,LenInit;
		LenG=Integer.toBinaryString(g);
		LenInit=Integer.toBinaryString(init);
		double[] m=new double[Len];
		int[] BinG=new int[LenG.length()];
		int[] BinInit=new int[LenInit.length()];
		int temp=0;
		//将本原多项式和初始状态值转换为二进制序列
		for(int i=BinG.length-1;i>=0;i--){
			BinG[i]=g%2;
			g=g/2;
		}
		for(int i=BinInit.length-1;i>=0;i--){
			BinInit[i]=init%2;
			init=init/2;
		}
		//开始生成M序列
		for(int i=0;i<Len;i++){ 
			m[i]=2*BinInit[LenInit.length()-1]-1;
			for(int j=0;j<LenInit.length();j++){
				temp+=BinInit[j]*BinG[j+1];
			}
			temp=temp%2;
			for(int k=LenInit.length()-1;k>0;k--){
				BinInit[k]=BinInit[k-1];
			}
			BinInit[0]=temp;
			temp=0;
		}
		return m;
	}
	public LocationData decode(double[]m,double[]decide,double[]data1,int codelength){
		LocationData locationData=new LocationData();
		double jk_code[]=new double[m.length];
		double[]signal=new double[codelength];
		double[] signalCode=new double[4];
		double t=0;
		for(int i=0;i<data1.length-m.length;i+=50){
			int k=0;
			//解扩
			for(int j=i;j<i+400;j++){
				jk_code[k]=m[k]*data1[j];
				k++;
			}
			//判决
			for(int l=0;l<codelength;l++){
				double rec=0;
				for(int j=0;j<100;j++){
					rec+=jk_code[j+100*l];
				}
				if(rec>0)
					signal[l]=1;
				else if(rec<0)
					signal[l]=0;
				else 
					signal[l]=100;
			}
			if(Arrays.equals(signal, decide)){
				t=(double)i*1000/44100;
				System.out.println(t+"毫秒");
				locationData.setSynyTime(t);
				int count=0;
				for(double d:signal)
				{
					System.out.println((int)d);
					signalCode[count++]=d;
				}
				locationData.setLocation(signalCode);
				
				break;
			}
		}
		return locationData;
	}
}
