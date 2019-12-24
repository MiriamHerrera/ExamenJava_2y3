package com.softtek.academia.repository;

import org.springframework.stereotype.Repository;

import com.softtek.academia.entity.City;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface CityRepository extends CrudRepository<City, Long>{
	
}