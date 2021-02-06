package concerttours.controller;

import concerttours.facades.BandFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class BandListController {

    @Resource
    private BandFacade bandFacade;

    @RequestMapping(value = "/bands", method = RequestMethod.GET)
    public String showBands(ModelMap model) {
        model.addAttribute("bands", bandFacade.getBands());
        return "bands";
    }
}
