package concerttours.facades.impl;

import concerttours.data.BandData;
import concerttours.enums.MusicType;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultBandFacade implements BandFacade
{
    private BandService bandService;

    private Converter<BandModel, BandData> bandConverter;

    public void setBandService(final BandService bandService)
    {
        this.bandService = bandService;
    }

    public void setBandConverter(Converter<BandModel, BandData> bandConverter) {
        this.bandConverter = bandConverter;
    }

    @Override
    public List<BandData> getBands()
    {
        return bandService.getBands().stream()
                .map(bandModel -> bandConverter.convert(bandModel))
                .collect(Collectors.toList());
    }
    @Override
    public BandData getBand(final String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Band name cannot be null");
        }
        final BandModel band = bandService.getBandForCode(name);
        if (band == null)
        {
            return null;
        }

        final List<String> genres = new ArrayList<>();
        if (band.getTypes() != null)
        {
            for (final MusicType musicType : band.getTypes())
            {
                genres.add(musicType.getCode());
            }
        }

        return bandConverter.convert(band);
    }
}