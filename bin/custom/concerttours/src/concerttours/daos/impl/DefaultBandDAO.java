package concerttours.daos.impl;

import concerttours.daos.BandDAO;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

public class DefaultBandDAO implements BandDAO {

    private static final String FIND_BANDS_QUERY = "SELECT {p:" + BandModel.PK + "} FROM {"
            + BandModel._TYPECODE + " AS p}";
    private static final String FIND_BANDS_BY_CODE_QUERY = "SELECT {p:" + BandModel.PK + "} FROM {"
            + BandModel._TYPECODE + " AS p} WHERE {p:" + BandModel.CODE + "}=?code ";

    private FlexibleSearchService flexibleSearchService;

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<BandModel> findBands() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_BANDS_QUERY);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }

    @Override
    public List<BandModel> findBandsByCode(String code) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_BANDS_BY_CODE_QUERY);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }
}
