package concerttours.facades.impl;

import concerttours.data.ConcertData;
import concerttours.facades.ConcertFacade;
import concerttours.model.ConcertModel;
import concerttours.service.ConcertService;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

public class DefaultConcertFacade implements ConcertFacade {

    private ConcertService concertService;
    private Converter<ConcertModel, ConcertData> concertConverter;

    public void setConcertService(ConcertService concertService) {
        this.concertService = concertService;
    }

    public void setConcertConverter(Converter<ConcertModel, ConcertData> concertConverter) {
        this.concertConverter = concertConverter;
    }

    @Override
    public List<ConcertData> getConcerts() {
        return Converters.convertAll(concertService.getConcerts(), concertConverter);
    }
}
