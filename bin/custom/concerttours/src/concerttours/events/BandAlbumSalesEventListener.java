package concerttours.events;

import concerttours.constants.ConcerttoursConstants;
import concerttours.model.NewsModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

public class BandAlbumSalesEventListener extends AbstractEventListener<BandAlbumSalesEvent> {

    private ModelService modelService;

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    protected void onEvent(BandAlbumSalesEvent event) {
        if (event != null) {
            String headline = String.format(ConcerttoursConstants.BAND_SALES_HEADLINE, event.getName());
            String content = String.format(ConcerttoursConstants.BAND_SALES_CONTENT, event.getName(), event.getSales());
            NewsModel news = modelService.create(NewsModel.class);
            news.setDate(new Date());
            news.setHeadline(headline);
            news.setContent(content);
            modelService.save(news);
        }
    }
}