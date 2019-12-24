package com.softtek.academia.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.academia.entity.City;
import com.softtek.academia.entity.State;
import com.softtek.academia.repository.CityRepository;
import com.softtek.academia.repository.StateRepository;


@Service
@Transactional
public class CityServiceImpl implements CityService{

	
	private CityRepository repository;
	
	public CityServiceImpl () {
		//constructor
	}

	@Autowired
	public CityServiceImpl(CityRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public List<City> getAllCities() {
		// TODO Auto-generated method stub
		List<City> list =  new ArrayList<City>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public City getCityById(Long city_id) {
		// TODO Auto-generated method stub
		City city = repository.findById(city_id).get();
		return city;
	}

	@Override
	public boolean saveCity(City city) {
		// TODO Auto-generated method stub
		try {
			repository.save(city);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteCityById(Long city_id) {
		// TODO Auto-generated method stub
		try {
			repository.deleteById(city_id);
			return true;
		}catch(Exception ex) {
			return false;
		}
		
	}
}