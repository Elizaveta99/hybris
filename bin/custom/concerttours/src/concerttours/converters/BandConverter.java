package concerttours.converters;

import concerttours.constants.ConcerttoursConstants;
import concerttours.data.BandData;
import concerttours.model.BandModel;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.media.MediaService;

public class BandConverter extends AbstractPopulatingConverter<BandModel, BandData> {

    private MediaService mediaService;

    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Override
    public BandData convert(BandModel source) throws ConversionException
    {
        BandData target = new BandData();
        populate(source, target);
        target.setImageURL(getImageURL(source, mediaService.getFormat(ConcerttoursConstants.FORMAT_BAND_LIST)));
        return target;
    }

    public BandData convert(BandModel source, String formatString) throws ConversionException {
        BandData target = new BandData();
        populate(source, target);
        target.setImageURL(getImageURL(source, mediaService.getFormat(formatString)));
        return target;
    }

    private String getImageURL(BandModel bandModel, MediaFormatModel format)
    {
        MediaContainerModel container = bandModel.getImage();
        if (container != null)
        {
            return mediaService.getMediaByFormat(container, format).getDownloadURL();
        }
        return null;
    }
}
