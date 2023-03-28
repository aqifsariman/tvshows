package ibf2022.paf.tvshows.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import ibf2022.paf.tvshows.model.Show;

@Repository
public class TvShowRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final String SHOWS = "tvshows";

    public List<String> findAllShows() {
        return mongoTemplate.findDistinct(new Query(), "name", SHOWS, String.class);
    }

    public List<Document> showInfo(String showName) {
        Criteria criteria = Criteria.where("name").is(showName);

        Query query = Query.query(criteria);

        return mongoTemplate.find(query, Document.class, SHOWS);
    }

    public UpdateResult update(String showName, Show show) {
        Criteria criteria = Criteria.where("name").is(showName);
        Query query = Query.query(criteria);

        System.out.println(" SHOW CLASS " + show);

        Update updateOps = new Update()
                .push("comments").each(List.of(show).toArray());

        return mongoTemplate.upsert(query, updateOps, "tvshows");
    }
}
