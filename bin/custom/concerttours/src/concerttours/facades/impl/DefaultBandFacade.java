package concerttours.facades.impl;

import concerttours.constants.ConcerttoursConstants;
import concerttours.converters.BandConverter;
import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.media.MediaService;

import java.util.List;

public class DefaultBandFacade implements BandFacade {

    private BandService bandService;
    private BandConverter bandConverter;

    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    public void setBandConverter(BandConverter bandConverter) {
        this.bandConverter = bandConverter;
    }

    @Override
    public List<BandData> getBands() {
        return Converters.convertAll(bandService.getBands(), bandConverter);
    }

    @Override
    public BandData getBand(String code) {
        return bandConverter.convert(bandService.getBandForCode(code), ConcerttoursConstants.FORMAT_BAND_DETAIL);
    }
}