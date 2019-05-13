package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.MySeatDao;
import com.entity.Seat;

public class MySeatService {
MySeatDao ms = new MySeatDao();
	public List<Seat> queryAll(Integer id) {
		List<Seat> list =ms.queryAll(id);

		return list;
	}
	public void delete(String seatNumber) {
		ms.delete(seatNumber);
		
	}

}
