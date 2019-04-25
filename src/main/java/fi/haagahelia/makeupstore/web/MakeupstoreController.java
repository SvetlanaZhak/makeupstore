package fi.haagahelia.makeupstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.makeupstore.domain.CategoryRepository;
import fi.haagahelia.makeupstore.domain.Makeup;
import fi.haagahelia.makeupstore.domain.MakeupRepository;


@Controller
public class MakeupstoreController {
	
	@Autowired
	private MakeupRepository repository;
	
	@Autowired
	private CategoryRepository drepository;
	
	// Show all items
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	// Show all items
	
	@RequestMapping(value="/makeuplist") 
	public String makeuplist(Model model) {
	
	model.addAttribute("makeups", repository.findAll());
	return "makeuplist";
}
	

	// RESTful service to get all items
    @RequestMapping(value="/makeups", method = RequestMethod.GET)
    public @ResponseBody List<Makeup> makeuplistRest() {	
        return (List<Makeup>) repository.findAll();
    }    

	// RESTful service to get item by id
    @RequestMapping(value="/makeup/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Makeup> findMakeupRest(@PathVariable("id") Long makeupId) {	
    	return repository.findById(makeupId);
    } 
	
    @PreAuthorize("hasAuthority('ADMIN')")
 		@RequestMapping(value = "/addmakeup")
 	    public String addMakeup(Model model){
 	    	model.addAttribute("makeup", new Makeup());
 	    	model.addAttribute("categories", drepository.findAll());
 	        return "addmakeup";
 	    }   
 		
 	    @RequestMapping(value = "/save", method = RequestMethod.POST)
 	    public String save(Makeup makeup){
 	        repository.save(makeup);
 	        return "redirect:makeuplist";
 	    }

 	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
 	    public String deleteMakeup(@PathVariable("id") Long makeupId, Model model) {
 	    	repository.deleteById(makeupId);
 	        return "redirect:../makeuplist";
 	    }
 	    
 	    @RequestMapping(value = "/edit/{id}")
 	    public String editMakeup(@PathVariable("id") Long makeupid, Model model) {
 	    	model.addAttribute("makeup", repository.findById(makeupid));
 	    	model.addAttribute("categories", drepository.findAll());
 	        return "editmakeup";
 	    }
 	    
 }