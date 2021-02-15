package concerttours.daos.impl;

import concerttours.daos.ConcertDao;
import concerttours.model.ConcertModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

public class DefaultConcertDao implements ConcertDao {

    private static final String FIND_CONCERTS_QUERY = "SELECT {p:" + ConcertModel.PK + "} FROM {"
            + ConcertModel._TYPECODE + " AS p}";

    private FlexibleSearchService flexibleSearchService;

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<ConcertModel> findConcerts() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_CONCERTS_QUERY);
        return flexibleSearchService.<ConcertModel> search(query).getResult();
    }
}
