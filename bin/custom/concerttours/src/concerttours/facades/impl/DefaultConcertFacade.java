package concerttours.facades.impl;

import concerttours.data.ConcertData;
import concerttours.data.ProducerData;
import concerttours.facades.ConcertFacade;
import concerttours.model.ConcertModel;
import concerttours.model.ProducerModel;
import concerttours.service.ConcertService;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class DefaultConcertFacade implements ConcertFacade {

    private ConcertService concertService;
    private Converter<ConcertModel, ConcertData> concertConverter;
    private Converter<ProducerModel, ProducerData> producerConverter;

    public void setConcertService(ConcertService concertService) {
        this.concertService = concertService;
    }

    public void setConcertConverter(Converter<ConcertModel, ConcertData> concertConverter) {
        this.concertConverter = concertConverter;
    }

    public void setProducerConverter(Converter<ProducerModel, ProducerData> producerConverter) {
        this.producerConverter = producerConverter;
    }

    @Override
    public List<ConcertData> getConcerts() {
        List<ProducerModel> producerModelList = new ArrayList<>();
        for (ConcertModel concertModel: concertService.getConcerts()) {
            producerModelList.add(concertModel.getProducer());
        }
        List<ProducerData> producers = Converters.convertAll(producerModelList, producerConverter);
        List<ConcertData> concerts = Converters.convertAll(concertService.getConcerts(), concertConverter);
        for (int i = 0; i < producers.size(); i++) {
            concerts.get(i).setProducer(producers.get(i));
        }
        return concerts;
    }
}
