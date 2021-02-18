package concerttours.controller;

import concerttours.facades.BandFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class BandDetailsController {

    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";

    @Resource
    private CatalogVersionService catalogVersionService;

    @Resource
    private BandFacade bandFacade;

    @RequestMapping(value = "/bands/{bandId}", method = RequestMethod.GET)
    public String showBandDetails(@PathVariable String bandId, Model model) throws UnsupportedEncodingException {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        String decodedBandId = URLDecoder.decode(bandId, "UTF-8");
        model.addAttribute("band", bandFacade.getBand(decodedBandId));
        return "bandDetails";
    }
}
