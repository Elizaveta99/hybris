package concerttours.populators;

import concerttours.data.BandData;
import concerttours.enums.MusicType;
import concerttours.model.BandModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BandGenresPopulator implements Populator<BandModel, BandData> {

    @Override
    public void populate(BandModel bandModel, BandData bandData) throws ConversionException {
        bandData.setGenres((List<String>)CollectionUtils.emptyIfNull(
                bandModel.getTypes()
                .stream()
                .map(MusicType::getCode)
                .collect(Collectors.toList())));
    }
}
