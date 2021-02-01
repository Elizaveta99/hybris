package org.training.web.controllers;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.training.service.impl.TrainingProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class TrainingProductController implements Controller
{
    private CatalogService catalogService;
    private TrainingProductServiceImpl trainingProductService;

    public void setCatalogService(final CatalogService catalogService)
    {
        this.catalogService = catalogService;
    }

    public void setTrainingProductService(final TrainingProductServiceImpl trainingProductService)
    {
        this.trainingProductService = trainingProductService;
    }

    public ModelAndView handleRequest(final HttpServletRequest request,
                                      final HttpServletResponse response) throws Exception
    {
        catalogService.setSessionCatalogVersion("hwcatalog", "Online");

        final String code = request.getParameter("code");
        final String name = request.getParameter("name");
        ProductModel product = null;
        if (code != null && name != null)
        {
            //product = trainingProductService.getProductForCodeAndName(code, name);
            product = trainingProductService.getProductForCode(code);
        }
        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("product", product);
        return new ModelAndView("trainingProduct.jsp", model);
    }

}