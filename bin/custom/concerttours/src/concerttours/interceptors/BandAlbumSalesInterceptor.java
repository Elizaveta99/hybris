package concerttours.interceptors;

import concerttours.constants.ConcerttoursConstants;
import concerttours.events.BandAlbumSalesEvent;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

public class BandAlbumSalesInterceptor implements ValidateInterceptor, PrepareInterceptor
{
    private EventService eventService;

    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
    {
        if (model instanceof BandModel)
        {
            final BandModel band = (BandModel) model;
            final Long sales = band.getAlbumSales();
            if (sales != null && sales.longValue() < ConcerttoursConstants.NEGATIVE_SALES)
            {
                throw new InterceptorException("Album sales must be positive");
            }
        }
    }

    @Override
    public void onPrepare(final Object model, final InterceptorContext ctx)
    {
        if (model instanceof BandModel)
        {
            final BandModel band = (BandModel) model;
            if (hasBecomeBig(band, ctx))
            {
                eventService.publishEvent(new BandAlbumSalesEvent(band.getCode(), band.getName(), band.getAlbumSales()));
            }
        }
    }

    private boolean hasBecomeBig(final BandModel band, final InterceptorContext ctx)
    {
        final Long sales = band.getAlbumSales();
        if (sales != null && sales.longValue() >= ConcerttoursConstants.BIG_SALES)
        {
            if (ctx.isNew(band))
            {
                return true;
            }
            else
            {
                final Long oldValue = getItemModelContext(band).getOriginalValue(BandModel.ALBUMSALES);
                if (oldValue == null || oldValue.intValue() < ConcerttoursConstants.BIG_SALES)
                {
                    return true;
                }
            }
        }
        return false;
    }
}