package concerttours.events;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;

public class BandAlbumSalesEvent extends AbstractEvent {

    private String code;
    private String name;
    private Long sales;

    public BandAlbumSalesEvent(String code, String name, Long sales) {
        super();
        this.code = code;
        this.name = name;
        this.sales = sales;
    }

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public Long getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return this.name;
    }
}