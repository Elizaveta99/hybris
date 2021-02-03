package concerttours.events;

import concerttours.constants.ConcerttoursConstants;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import java.util.Date;
import concerttours.model.BandModel;
import concerttours.model.NewsModel;

public class NewBandEventListener extends AbstractEventListener<AfterItemCreationEvent>
{
    private ModelService modelService;

    public ModelService getModelService()
    {
        return modelService;
    }
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }
    @Override
    protected void onEvent(final AfterItemCreationEvent event)
    {
        if (event != null && event.getSource() != null)
        {
            final Object object = modelService.get(event.getSource());
            if (object instanceof BandModel)
            {
                final BandModel band = (BandModel) object;
                final String headline = String.format(ConcerttoursConstants.NEW_BAND_HEADLINE, band.getName());
                final String content = String.format(ConcerttoursConstants.NEW_BAND_CONTENT, band.getName());
                final NewsModel news = modelService.create(NewsModel.class);
                news.setDate(new Date());
                news.setHeadline(headline);
                news.setContent(content);
                modelService.save(news);
            }
        }
    }
}