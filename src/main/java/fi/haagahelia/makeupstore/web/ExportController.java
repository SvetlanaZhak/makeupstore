package fi.haagahelia.makeupstore.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fi.haagahelia.makeupstore.domain.MakeupRepository;

@Controller
public class ExportController {

    @Autowired
    private MakeupRepository makeupRepository ;

    /**
     * Handle request to download an Excel document
     */
    @GetMapping("/download")
    public String download(Model model) {
    	
		model.addAttribute("makeuplist", makeupRepository.findAll());
        return "";
    }


}
