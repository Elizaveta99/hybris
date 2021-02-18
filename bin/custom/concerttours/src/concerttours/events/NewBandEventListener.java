package concerttours.events;

import concerttours.constants.ConcerttoursConstants;
import concerttours.model.BandModel;
import concerttours.model.NewsModel;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

public class NewBandEventListener extends AbstractEventListener<AfterItemCreationEvent> {

    private ModelService modelService;

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    protected void onEvent(AfterItemCreationEvent event) {
        if (event != null && event.getSource() != null) {
            Object object = modelService.get(event.getSource());
            if (object instanceof BandModel) {
                BandModel band = (BandModel) object;
                String headline = String.format(ConcerttoursConstants.NEW_BAND_HEADLINE, band.getName());
                String content = String.format(ConcerttoursConstants.NEW_BAND_CONTENT, band.getName());
                NewsModel news = modelService.create(NewsModel.class);
                news.setDate(new Date());
                news.setHeadline(headline);
                news.setContent(content);
                modelService.save(news);
            }
        }
    }
}