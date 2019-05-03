package fi.haagahelia.makeupstore.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fi.haagahelia.makeupstore.domain.Makeup;


@Controller
public class WebController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/makeuplist").setViewName("makeuplist");
    }

    @GetMapping("/")
    public String showForm(Makeup makeup) {
        return "addmakeup";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid Makeup makeup, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "makeup";
        }

        return "redirect:/makeuplist";
    }
}
