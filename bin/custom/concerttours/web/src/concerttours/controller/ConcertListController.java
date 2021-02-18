package concerttours.controller;

import concerttours.facades.ConcertFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class ConcertListController {

    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";

    @Resource
    private CatalogVersionService catalogVersionService;

    @Resource
    private ConcertFacade concertFacade;

    @RequestMapping(value = "/concerts", method = RequestMethod.GET)
    public String showConcerts(ModelMap model) {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        model.addAttribute("concerts", concertFacade.getConcerts());
        return "concerts";
    }
}
