package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SaveDataDao;

@Service("saveDataService")
public class SaveDataSericeImpl implements SaveDataService {
	
	@Autowired
	private SaveDataDao saveDataDao;

	@Override
	public void saveDataToDatabase() {
		saveDataDao.saveDataToDatabase();
	}

	@Override
	public void readFromDatabase() {
		saveDataDao.readFromDatabase();
		
	}
}
