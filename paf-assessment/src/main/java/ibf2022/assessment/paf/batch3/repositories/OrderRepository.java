package ibf2022.assessment.paf.batch3.repositories;

import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Order;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Repository
public class OrderRepository {

	@Autowired
	private MongoTemplate template;
	// TODO: Task 5

	/*
	 * db.order.insertMany({
	 * orderId: "<random 8 character order id>",
	 * date: 2023-04-28,
	 * breweryId: 123,
	 * orders:
	 * {beerId: 123,
	 * quantity: 10}
	 * })
	 */

	public JsonObject insertOrder(Order order) {
		Criteria criteria = Criteria.where("breweryId").is(order.getBreweryId());
		Query query = Query.query(criteria);

		String randId = UUID.randomUUID().toString().substring(0, 8);

		Document doc = new Document()
				.append("orderId", randId)
				.append("date", order.getDate())
				.append("breweryId", order.getBreweryId());

		JsonObject docforJson = Json.createObjectBuilder()
				.add("orderId", order.getOrderId())
				.add("date", order.getDate())
				.add("breweryId", order.getBreweryId())
				.build();

	}
}
