package com.softtek.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.softtek.academia.entity.City;
import com.softtek.academia.entity.State;
import com.softtek.academia.service.CityService;
import com.softtek.academia.service.StateService;


@Controller
public class CityController {
	//Constructor basado en inyeccion de dependencias.
	
	private CityService cityService;
	public CityController() {
	}
	
	@Autowired 
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}
	
	// Get All Cities
	@PostMapping("/allCities")
	public ModelAndView displayAllCity() {
		System.out.println("City page Requested : All Cities ");
		ModelAndView mv = new ModelAndView();
		List<City> cityList = cityService.getAllCities();
		mv.addObject("cityList", cityList);
		mv.setViewName("allCities");
		return mv;	
	}

/*	@PostMapping("/allStates")
	public ModelAndView displayAllState() {
		System.out.println("State page Requested : All States ");
		ModelAndView mv = new ModelAndView();
		List<State> stateList = stateService.getAllStates();
		mv.addObject("stateList", stateList);
		mv.setViewName("allStates");
		return mv;	
	}*/
	
	@GetMapping("/addCity")
	public ModelAndView displayNewStateForm(){
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("headerMessage","Add City Details");
		mv.addObject("city", new City());
		return mv;
	}
	
	@PostMapping("/addCity")
	public ModelAndView saveNewCity(@ModelAttribute City city, BindingResult result) {
		
		ModelAndView mv = new ModelAndView("redirect:/home");
		
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		boolean isAdded = cityService.saveCity(city);
		
		if(isAdded) {
			mv.addObject("message", "New City succesfully added");
		}else {
			return new ModelAndView("error");
		}
		
		return mv;
		}
	
		@GetMapping("/deleteCity/{city_id}")
		public ModelAndView deleteCityById(@PathVariable Long city_id) {
			boolean isDeleted = cityService.deleteCityById(city_id);
			System.out.println("State deletion response: " + isDeleted);
			ModelAndView mv = new ModelAndView("redirect:/home");
			return mv;
			
		}
		
		@RequestMapping(value = "/editCity/{city_id}", method = RequestMethod.GET)
		public ModelAndView displayEditCityForm(@PathVariable Long city_id) {
			ModelAndView mv = new ModelAndView("/editCity");
			City city = cityService.getCityById(city_id);
			mv.addObject("headerMessage", "Edit City Details");
			mv.addObject("city", city);
			return mv;
		}

		@RequestMapping(value = "/editCity/{city_id}", method = RequestMethod.POST)
		public ModelAndView saveEditedCity(@ModelAttribute City city, BindingResult result) {
			ModelAndView mv = new ModelAndView("redirect:/home");

			if (result.hasErrors()) {
				System.out.println(result.toString());
				return new ModelAndView("error");
			}
			boolean isSaved = cityService.saveCity(city);
			if (!isSaved) {

				return new ModelAndView("error");
			}

			return mv;
		}
	}