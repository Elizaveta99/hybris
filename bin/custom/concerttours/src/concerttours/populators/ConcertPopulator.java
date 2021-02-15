package concerttours.populators;

import concerttours.data.ConcertData;
import concerttours.model.ConcertModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ConcertPopulator implements Populator<ConcertModel, ConcertData> {

    @Override
    public void populate(ConcertModel concertModel, ConcertData concertData) throws ConversionException {
        concertData.setId(concertModel.getCode());
        concertData.setName(concertModel.getName());
        concertData.setDate(concertModel.getDate());
        concertData.setVenue(concertModel.getVenue());
    }
}
