package ibf2022.assessment.paf.batch3.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

@Repository
public class BeerRepository {

	@Autowired
	private JdbcTemplate template;

	private final String COUNT_STYLES = """
			select s.style_name as Style, count(style_id) as Beer_Count
			from styles as s
			join beers as b
			on s.id = b.style_id
			group by style_id
			order by count(style_id) desc, style_name asc;

							""";

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2

		final List<Style> styleList = new LinkedList<>();

		final SqlRowSet rs = template.queryForRowSet(COUNT_STYLES);

		while (rs.next()){
			Style style = new Style();
			style.setStyleId(rs.getInt("id"));
			style.setName(rs.getString("Style"));
			style.setBeerCount(rs.getInt("Beer_Count"));
			styleList.add(style);
		}

		return styleList;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(/* You can add any number parameters here */) {
		// TODO: Task 3

		return null;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(/* You can add any number of parameters here */) {
		// TODO: Task 4

		return Optional.empty();
	}
}
