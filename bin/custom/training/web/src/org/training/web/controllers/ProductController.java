package org.training.web.controllers;

import de.hybris.platform.catalog.CatalogService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.training.data.ProductData;
import org.training.facades.ProductFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ProductController implements Controller
{

    private CatalogService catalogService;
    private ProductFacade productFacade;

    public void setCatalogService(final CatalogService catalogService)
    {
        this.catalogService = catalogService;
    }

    public void setProductFacade(final ProductFacade productFacade)
    {
        this.productFacade = productFacade;
    }

    public ModelAndView handleRequest(final HttpServletRequest request,
                                      final HttpServletResponse response) throws Exception
    {
        catalogService.setSessionCatalogVersion("hwcatalog", "Online");

        String code = request.getParameter("code");
        ProductData product = null;
        if (code != null)
        {
            product = productFacade.getProduct(code);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("product", product);
        return new ModelAndView("product.jsp", model);
    }

}