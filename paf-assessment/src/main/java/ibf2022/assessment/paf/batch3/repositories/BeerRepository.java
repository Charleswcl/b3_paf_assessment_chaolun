package ibf2022.assessment.paf.batch3.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.Utils;
import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

@Repository
public class BeerRepository {

	@Autowired
	private JdbcTemplate template;

	private final String GET_STYLES_AND_COUNT = """
			select s.id, s.style_name, count(style_id) as count
			from styles as s
			join beers as b
			on s.id = b.style_id
			group by style_id
			order by count(style_id) desc, style_name asc;
							""";

	private final String GET_BEER_BY_STYLID = """
			select b.brewery_id, b.name as Beer_Name, b.descript as Description, br.name as Brewery_Name
			from beers as b
			join breweries as br
			on b.brewery_id = br.id
			where style_id = ?
			order by b.name asc;
				""";

	private final String GET_BEERS_FROM_BREWERY = """
			select * from breweries;
			select * from beers;
			select br.descript as br_description, br.address1 as address, br.phone, br.website, b.name, b.descript as b_description
			from beers as b
			join breweries as br
			on b.brewery_id = br.id
			where br.id = ?
			order by b.name asc;
				""";

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2

		final List<Style> styleList = new LinkedList<>();

		final SqlRowSet rs = template.queryForRowSet(GET_STYLES_AND_COUNT);

		while (rs.next()) {
			Style style = new Style();
			style.setStyleId(rs.getInt("id"));
			style.setName(rs.getString("style_name"));
			style.setBeerCount(rs.getInt("count"));
			styleList.add(style);
		}

		return styleList;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(int styleId) {
		// TODO: Task 3
		final List<Beer> beerList = new LinkedList<>();

		final SqlRowSet rs = template.queryForRowSet(GET_BEER_BY_STYLID, styleId);

		while (rs.next()) {
			Beer beer = new Beer();
			beer.setBreweryId(rs.getInt("brewery_id"));
			beer.setBeerName(rs.getString("Beer_Name"));
			beer.setBeerDescription(rs.getString("Description"));
			beer.setBreweryName(rs.getString("Brewery_Name"));
			beerList.add(beer);
		}

		return beerList;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(int breweryId) {
		// TODO: Task 4

		SqlRowSet rs = template.queryForRowSet(GET_BEERS_FROM_BREWERY, breweryId);

		if (!rs.next())
			return Optional.empty();
		return Optional.of(Utils.toBrewery(rs));
	}
}
