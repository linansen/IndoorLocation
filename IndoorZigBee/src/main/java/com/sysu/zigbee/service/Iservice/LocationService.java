package com.sysu.zigbee.service.Iservice;

import java.util.List;

import com.sysu.zigbee.domain.LocationData;

public interface LocationService {
	
	public List<LocationData> decodeWaveFile();

}
