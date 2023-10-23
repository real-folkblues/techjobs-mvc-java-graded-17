package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
//designates controller action w URL path
@Controller
@RequestMapping("search")
public class SearchController {


    //path is defined with getmapping(pathname) if path null, uses index path or /
    //responds to get requests at /value = ""
    @GetMapping(value = "")
    public String search(Model model) {
        //method code for displaying search results
        model.addAttribute("columns", columnChoices);
        return "search";
    }
//renders form defined in search html
    @PostMapping(value = "post")
    public String displaySearchResults(Model model,
       @RequestParam String searchType,
       @RequestParam String searchTerm){
        ArrayList<Job> jobs;
        if (searchTerm.equals("all")|| searchTerm.trim().isEmpty()) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);}
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }
    // TODO #3 - Create a second handler to process a search request and render the updated search view.
    //will deal with user input and display results

}

