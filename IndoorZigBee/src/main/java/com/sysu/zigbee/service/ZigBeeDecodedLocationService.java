package com.sysu.zigbee.service;

import com.sysu.zigbee.domain.LocationData;
import com.sysu.zigbee.domain.PNGen;
import com.sysu.zigbee.domain.WaveFileReader;
import com.sysu.zigbee.service.Iservice.LocationService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ZigBeeDecodedLocationService implements LocationService {
	static int[][] data = null;
	static int BitLen;// 量化位数
	static int DataLen;// 音频文件的数据长度
	static int g = 19;// 本原多项式
	static int codelength = 4;// 嵌入水印的数据长度
	static int MperC = 100;// 每一个信息码中嵌入的PN码位数
	static int N = 100;// 水印信息的采样点数
	static double[] signal = new double[codelength];// 解扩之后得到的信号
	static double[] decide1 = { 0, 0, 0, 1 };
	static double[] decide2 = { 0, 0, 1, 0 };
	static double[] decide3 = { 0, 0, 1, 1 };
	static double t1;

	public List<LocationData> decodeWaveFile() {
		// 读取音频文件
		List<LocationData> locationDatas = new ArrayList<>(3);
		WaveFileReader wr = new WaveFileReader(
				"/Users/linansen/Documents/workspace/IndoorZigBee/wav_storage_file/PN_code.WAV");
		data = wr.getData();
		BitLen = wr.getBitPerSample();
		DataLen = data[0].length;
		double[] x_code = new double[codelength * MperC];
		double[] data1 = new double[DataLen];// 用于接受音频信号
		for (int i = DataLen - 1; i >= 0; i--) {
			data1[i] = data[0][i] / Math.pow(2, BitLen - 1);
			if (data1[i] > 0.5)
				data1[i] = 1;
			else if (data1[i] < -0.5)
				data1[i] = -1;
			else
				data1[i] = 0;
		}
		// 生成M序列
		int init = 8;// M序列初始状态
		PNGen pn = new PNGen(g, init, codelength * MperC);
		double[] m = pn.gen();
		PNGen pn1 = new PNGen(g, (init + 1), codelength * MperC);
		double[] m1 = pn1.gen();
		PNGen pn2 = new PNGen(g, (init + 2), codelength * MperC);
		double[] m2 = pn2.gen();
		// 解扩&判决
		locationDatas.add(pn.decode(m, decide1, data1, codelength));
		locationDatas.add(pn.decode(m1, decide2, data1, codelength));
		locationDatas.add(pn.decode(m2, decide3, data1, codelength));
		return locationDatas;
	}

}
