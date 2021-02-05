package concerttours.daos.impl;

import concerttours.daos.TokenItemDAO;
import concerttours.model.TokenItemModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

public class DefaultTokenItemDAO implements TokenItemDAO {

    private final static String FIND_TOKENITEMS_QUERY = "SELECT {p:" + TokenItemModel.PK + "} "
            + "FROM {" + TokenItemModel._TYPECODE + " AS p} ";

    private FlexibleSearchService flexibleSearchService;

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
    {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<TokenItemModel> findTokenItems() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_TOKENITEMS_QUERY);
        return flexibleSearchService.<TokenItemModel> search(query).getResult();
    }
}
