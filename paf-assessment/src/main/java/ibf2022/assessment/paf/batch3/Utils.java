package ibf2022.assessment.paf.batch3;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;

public class Utils {
    
    public static Brewery toBrewery(SqlRowSet rs) {
        Brewery brewery = new Brewery();
        List<Beer> beerList = new LinkedList<>();
        Beer beer = new Beer();
        beer.setBeerName(rs.getString("name"));
        beer.setBeerDescription(rs.getString("b_description"));
        beerList.add(beer);
        
        brewery.setBeers(null);
        brewery.setDescription(rs.getString("br_description"));
        brewery.setAddress1(rs.getString("address"));
        brewery.setPhone(rs.getString("phone"));
        brewery.setWebsite(rs.getString("website"));
        brewery.setBeers(beerList);
        return brewery;
    }
}
