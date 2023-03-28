package ibf2022.paf.tvshows.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.client.result.UpdateResult;

import ibf2022.paf.tvshows.model.Show;
import ibf2022.paf.tvshows.service.TvShowService;

@Controller
@RequestMapping
public class TvController {

    @Autowired
    TvShowService tvShowSvc;

    @GetMapping("/show-list")
    public String showList(Model model) {

        model.addAttribute("showList", tvShowSvc.findAllShows());

        return "show-list";
    }

    @GetMapping("/comments/{showName}")
    public String showInfo(@PathVariable("showName") String showName, Model model) {
        model.addAttribute("showInfo", tvShowSvc.showInfo(showName));
        model.addAttribute("showObject", new Show());
        return "show-info";
    }

    @PostMapping("/comments/{showName}")
    public String postComments(@PathVariable("showName") String showName, @ModelAttribute("showName") Show show,
            Model model) {
        UpdateResult result = tvShowSvc.update(showName, show);
        System.out.println(result.getModifiedCount());
        model.addAttribute("showInfo", tvShowSvc.showInfo(showName));
        List<Document> showInfo = tvShowSvc.showInfo(showName);
        System.out.println(showInfo);
        return "comments";
    }

    @GetMapping("/all-comments/{showName}")
    public String showComments(@PathVariable("showName") String showName,
            Model model) {
        model.addAttribute("showInfo", tvShowSvc.showInfo(showName));
        return "comments";
    }
}
