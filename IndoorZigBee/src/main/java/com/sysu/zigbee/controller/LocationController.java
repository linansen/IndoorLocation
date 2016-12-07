package com.sysu.zigbee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sysu.zigbee.domain.LocationData;

import com.sysu.zigbee.service.Iservice.LocationService;

@RestController
public class LocationController {

	private LocationService locationService;

    @Autowired
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	@RequestMapping("/location")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public List<LocationData> locating() {
        List<LocationData> locationDatas=locationService.decodeWaveFile();
		return locationDatas;

	}

}
