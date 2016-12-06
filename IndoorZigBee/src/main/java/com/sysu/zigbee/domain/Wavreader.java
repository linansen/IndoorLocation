package com.sysu.zigbee.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Wavreader {
	private String cd;
	public Wavreader(String cd){
		this.cd=cd;
	}
	public void read() throws FileNotFoundException{
		File file=new File(cd);
		FileInputStream in=new FileInputStream(file);//创建字节输入流
		byte[] bbuff=null;//用于保存实际读取的字节
		int hasRead=0;
		try {
			while((hasRead=in.read(bbuff))>0){
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
