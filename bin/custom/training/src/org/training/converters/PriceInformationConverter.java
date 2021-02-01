package org.training.converters;

import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.training.data.PriceData;

public class PriceInformationConverter implements Converter<PriceInformation, PriceData>
{

    public PriceData convert(final PriceInformation source) throws ConversionException
    {
        final PriceData data = new PriceData();
        return convert(source, data);
    }

    public PriceData convert(final PriceInformation source, final PriceData prototype)
    {
        prototype.setCurrency(source.getPriceValue().getCurrencyIso());
        prototype.setValue(source.getPriceValue().getValue());
        return prototype;
    }
}
