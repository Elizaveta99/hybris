package concerttours.populators;

import concerttours.data.ConcertData;
import concerttours.data.ProducerData;
import concerttours.model.ConcertModel;
import concerttours.model.ProducerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Optional;

public class ConcertPopulator implements Populator<ConcertModel, ConcertData> {

    private Converter<ProducerModel, ProducerData> producerConverter;

    public void setProducerConverter(Converter<ProducerModel, ProducerData> producerConverter) {
        this.producerConverter = producerConverter;
    }

    @Override
    public void populate(ConcertModel concertModel, ConcertData concertData) throws ConversionException {
        concertData.setId(concertModel.getCode());
        concertData.setName(concertModel.getName());
        concertData.setDate(concertModel.getDate());
        concertData.setVenue(concertModel.getVenue());
        Optional.ofNullable(concertModel.getProducer())
                .ifPresent(producerModel -> concertData.setProducer(producerConverter.convert(producerModel)));
    }
}
