package ibf2022.paf.tvshows.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;

import ibf2022.paf.tvshows.model.Show;
import ibf2022.paf.tvshows.repository.TvShowRepository;

@Service
public class TvShowService {

    @Autowired
    TvShowRepository tvShowRepo;

    public List<String> findAllShows() {
        return tvShowRepo.findAllShows();
    }

    public List<Document> showInfo(String showName) {
        return tvShowRepo.showInfo(showName);
    }

    public UpdateResult update(String showName, Show show) {
        return tvShowRepo.update(showName, show);
    }
}
