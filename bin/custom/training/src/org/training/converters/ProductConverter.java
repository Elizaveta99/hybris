package org.training.converters;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.training.data.ProductData;

public class ProductConverter implements Converter<ProductModel, ProductData>
{

    public ProductData convert(final ProductModel source) throws ConversionException
    {
        final ProductData data = new ProductData();
        return convert(source, data);
    }

    public ProductData convert(final ProductModel source, final ProductData prototype)
    {
        prototype.setCode(source.getCode());
        prototype.setDescription(source.getDescription());
        prototype.setName(source.getName());
        if (source.getPicture() != null)
        {
            prototype.setPictureUrl(source.getPicture().getUrl());
        }
        return prototype;
    }

}
