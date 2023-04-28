package ibf2022.assessment.paf.batch3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
@RequestMapping("/api/beer")

public class BeerController {

	@Autowired
	private BeerService beerSvc;
	// TODO Task 2 - view 0

	@GetMapping(path = "/styles")
	public String getStyles(Model model) {

		List<Style> styles = beerSvc.getStyles();

		model.addAttribute("styles", styles);

		return "view0";

	}

	// TODO Task 3 - view 1
	@GetMapping(path = "/style/{id}")
	public String getBeersByStyle(@PathVariable("id") int styleId, @RequestParam String styleName, Model model) {

		List<Beer> beers = beerSvc.getBreweriesByBeer(styleId);

		model.addAttribute("beers", beers);

		return "view1";
	}

	// TODO Task 4 - view 2
	@GetMapping(path = "/style/{id}/{breweryId}")
	public String getBeersFromBrewery(@PathVariable("id") int breweryId, Model model) {

		List<Beer> breweries = beerSvc.getBreweriesByBeer(breweryId);

		model.addAttribute("breweries", breweries);
		
		return "view2";
	}

	// TODO Task 5 - view 2, place order

}
