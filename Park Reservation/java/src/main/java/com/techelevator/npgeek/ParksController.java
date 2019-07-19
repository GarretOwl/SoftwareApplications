package com.techelevator.npgeek;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.park.Park;
import com.techelevator.npgeek.model.park.ParkDao;
import com.techelevator.npgeek.model.survey.Survey;
import com.techelevator.npgeek.model.survey.SurveyDao;
import com.techelevator.npgeek.model.weather.Weather;
import com.techelevator.npgeek.model.weather.WeatherDao;
@Controller
@SessionAttributes({"temperatureChoice", "parkCode"})
public class ParksController {
	
	@Autowired
	ParkDao parkDao;
	@Autowired
	WeatherDao weatherDao;
	@Autowired
	SurveyDao surveyDao;
	

		@RequestMapping(path = "/", method = RequestMethod.GET)
		public String displayHomePage(ModelMap map) {
			
			List<Park> allParksList = parkDao.getAllParks();
		 
			map.addAttribute("allParkList", allParksList);
			
			return "homePage";
		}
		
		
		@RequestMapping(path = "/parkDetail", method = RequestMethod.GET)
		public String displayParkDetailPage(ModelMap map, @RequestParam ("parkCode") String parkCode) {
			
			if(! map.containsAttribute("temperatureChoice")) {
				map.addAttribute("temperatureChoice", false);
			}

			Park park = parkDao.getParkByParkCode(parkCode);

			List<Weather> weatherList = weatherDao.getWeatherForecastByParkCode(parkCode);
			
			parkCode = park.getParkCode();
			map.put("parkCode", parkCode); //addAttributes has a null check, was giving us errors. Used put instead  
			map.put("park", park);
			map.put("weather", weatherList);
	
			return "parkDetail";
			
			
		}
		
		
		@RequestMapping(path = "/parkDetail", method = RequestMethod.POST)
		public String redirectDisplayParkDetailPage(ModelMap map, @RequestParam ("temperatureChoice") boolean temperatureChoice) {
			
			map.addAttribute("temperatureChoice", temperatureChoice);
	
			return "redirect:/parkDetail";
					
		}
		@RequestMapping(path = "/parkSurvey", method = RequestMethod.GET)
		public String displaySurveyPageWithForm(ModelMap map) {
			
			if(! map.containsAttribute("newSurvey")) {
				map.addAttribute("newSurvey", new Survey());
			}
			
			return "parkSurvey";
		}
		
		@RequestMapping(path = "/parkSurvey", method = RequestMethod.POST)
		public String submitFormAndRedirectToResults(@Valid @ModelAttribute("newSurvey") Survey survey, BindingResult result, RedirectAttributes attr) {
			
			if(result.hasErrors()) {
				return "parkSurvey"; 
			}
						
			surveyDao.addSurvey(survey);
			
		
			return "redirect:/parkSurveyResults";
		}
		
		@RequestMapping(path = "/parkSurveyResults", method = RequestMethod.GET)
		public String displayParkSurveyResultsPage(HttpServletRequest request) {
			
			LinkedHashMap<String, Integer> surveyList = surveyDao.getSurveysWithOneCountMin();		
			
			request.setAttribute("surveys", surveyList);
			
			return "parkSurveyResults";
			
			
		}
		

	}
