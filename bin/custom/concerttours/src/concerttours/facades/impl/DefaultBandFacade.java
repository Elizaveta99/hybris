package concerttours.facades.impl;

import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

public class DefaultBandFacade implements BandFacade {

    private BandService bandService;
    private Converter<BandModel, BandData> bandConverter;

    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    public void setBandConverter(Converter<BandModel, BandData> bandConverter) {
        this.bandConverter = bandConverter;
    }

    @Override
    public List<BandData> getBands() {
        return Converters.convertAll(bandService.getBands(), bandConverter);
    }

    @Override
    public BandData getBand(String code) {
        return bandConverter.convert(bandService.getBandForCode(code));
    }
}