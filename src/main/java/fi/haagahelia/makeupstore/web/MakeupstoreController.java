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
	
	model.addAttribute("makeuplist", repository.findAll());
	return "makeuplist";
}
	

	// RESTful service to get all items
    @RequestMapping(value="/makeups", method = RequestMethod.GET)
    public @ResponseBody List<Makeup> makeupListRest() {	
        return (List<Makeup>) repository.findAll();
    }    

	// RESTful service to get item by id
    @RequestMapping(value="/makeup/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Makeup> findMakeupRest(@PathVariable("id") Long makeupId) {	
    	return repository.findById(makeupId);
    } 
	
	//Delete item
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteMakeup(@PathVariable("id") Long makeupId, Model model){
	repository.deleteById(makeupId);
	return "redirect:../makeuplist";
}
	
	//Add new item
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addMakeup(Model model){
		model.addAttribute("makeup", new Makeup());
		model.addAttribute("category", drepository.findAll());
		return "addmakeup";
}
	
	//Save new item 
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMakeup (Makeup Makeup) {
	repository.save(Makeup);
	return "redirect:makeuplist";
	}
	
	// Edit item
	
	@RequestMapping(value = "/edit/{id}")
	public String editMakeup(@PathVariable("id") Long id, Model model){
	model.addAttribute("makeup", repository.findById(id));
	return "editmakeup";
	}
	@RequestMapping(value={"/", "/home"})
	public String homeSecure() {
		return "home";
	}  
    
    @RequestMapping(value="/hello")
	public String helloSecure() {
		return "hello";
	}
    
   
}

