package org.training.facades.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.training.data.PriceData;
import org.training.data.ProductData;
import org.training.facades.ProductFacade;

import java.util.List;

public class DefaultProductFacade implements ProductFacade
{
    private ProductService productService;
    private PriceService priceService;
    private Converter<ProductModel, ProductData> productConverter;
    private Converter<PriceInformation, PriceData> priceInformationConverter;

    public void setProductService(ProductService productService)
    {
        this.productService = productService;
    }

    public void setPriceService(PriceService priceService)
    {
        this.priceService = priceService;
    }

    public void setProductConverter(final Converter<ProductModel, ProductData> converter)
    {
        this.productConverter = converter;
    }

    public void setPriceInformationConverter(
            final Converter<PriceInformation, PriceData> priceInformationConverter)
    {
        this.priceInformationConverter = priceInformationConverter;
    }

    public ProductData getProduct(String productCode)
    {
        ProductModel product = productService.getProductForCode(productCode);
        List<PriceInformation> prices = priceService.getPriceInformationsForProduct(product);
        final ProductData prodData = productConverter.convert(product);
        if (!prices.isEmpty())
        {
            final PriceInformation price = prices.iterator().next();
            final PriceData priceData = priceInformationConverter.convert(price);
            prodData.setPrice(priceData);
        }
        return prodData;
    }
}