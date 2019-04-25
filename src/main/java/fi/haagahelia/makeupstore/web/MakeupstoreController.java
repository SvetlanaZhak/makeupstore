package fi.haagahelia.makeupstore.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
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
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/makeuplist")
	public String makeuplist(Model model) {
		model.addAttribute("makeups", repository.findAll());
		return "makeuplist";
	}

	// excel
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public @ResponseBody List<Makeup> excel() {
		return (List<Makeup>) repository.findAll();
	}

	// list all
	@RequestMapping(value = "/makeups", method = RequestMethod.GET)
	public @ResponseBody List<Makeup> makeuplistRest() {
		return (List<Makeup>) repository.findAll();
	}

	// RESTful service to get item by id
	@RequestMapping(value = "/makeup/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Makeup> findMakeupRest(@PathVariable("id") long id) {
		return repository.findById(id);
	}

	// delete item
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteMakeup(@PathVariable("id") long id, Model model) {
		repository.deleteById(id);
		return "redirect:../makeuplist";
	}

	// edit item
	@RequestMapping(value = "/edit/{id}")
	public String editMakeup(@PathVariable("id") long id, Model model) {
		model.addAttribute("makeup", repository.findById(id));
		model.addAttribute("categories", drepository.findAll());
		return "editmakeup";
	}

	// add new item
	@RequestMapping(value = "/add")
	public String addMakeup(Model model) {
		model.addAttribute("makeup", new Makeup());
		model.addAttribute("categories", drepository.findAll());
		return "addmakeup";
	}

	// save added item
	@RequestMapping(value = "/saveadded", method = RequestMethod.POST)
	public String saveadded(@Valid Makeup makeup, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/addmakeup";
		}
		repository.save(makeup);
		return "redirect:makeuplist";
	}

	// save editted item
	@RequestMapping(value = "/saveeditted", method = RequestMethod.POST)
	public String saveeditted(@Valid Makeup makeup, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/editmakeup";
		}
		repository.save(makeup);
		return "redirect:makeuplist";
	}
}
