package ibf2022.assessment.paf.batch3.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
@RequestMapping("/api/beer")

public class BeerController {

	@Autowired
	private BeerService beerSvc;
	// TODO Task 2 - view 0

	@GetMapping(path ="/styles", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String getStylesWithCount (@RequestBody String form, Model model) {

		List<Style> styles = beerSvc.getAllStyleCount();

		model.addAttribute("styles", styles);
		 return "view0";

	}


	// TODO Task 3 - view 1

	// TODO Task 4 - view 2

	// TODO Task 5 - view 2, place order

}
