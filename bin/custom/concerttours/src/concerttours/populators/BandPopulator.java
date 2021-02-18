package concerttours.populators;

import concerttours.data.BandData;
import concerttours.model.BandModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class BandPopulator implements Populator<BandModel, BandData> {

    private static final String BAND_PREFIX_NAME = "concerttours.band.prefix.name";

    private ConfigurationService configurationService;

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public void populate(BandModel bandModel, BandData bandData) throws ConversionException {
        String prefixName = configurationService.getConfiguration().getString(BAND_PREFIX_NAME);

        bandData.setId(bandModel.getCode());
        bandData.setName(prefixName + bandModel.getName());
        bandData.setDescription(bandModel.getHistory());
        bandData.setAlbumsSold(bandModel.getAlbumSales());
    }
}
