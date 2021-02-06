package org.training.service.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

public class MyDefaultProductServiceImpl extends AbstractBusinessService implements ProductService
{
    private FlexibleSearchService flexibleSearchService;

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
    {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public ProductModel getProduct(String code) {
        return null;
    }

    @Override
    public ProductModel getProductForCode(final String code)
    {
        return getProductForCodeAndName(code,"AMD");
    }

    @Override
    public ProductModel getProduct(CatalogVersionModel catalogVersion, String code) {
        return null;
    }

    @Override
    public UnitModel getOrderableUnit(ProductModel product) {
        return null;
    }

    @Override
    public UnitModel getUnit(String code) {
        return null;
    }

    @Override
    public List<ProductModel> getProducts(CategoryModel category) {
        return null;
    }

    @Override
    public List<ProductModel> getProductsForCategory(CategoryModel category) {
        return null;
    }

    @Override
    public List<ProductModel> getOnlineProductsForCategory(CategoryModel category) {
        return null;
    }

    @Override
    public List<ProductModel> getOfflineProductsForCategory(CategoryModel category) {
        return null;
    }

    @Override
    public SearchResult<ProductModel> getProducts(CategoryModel category, int start, int count) {
        return null;
    }

    @Override
    public List<ProductModel> getProductsForCategory(CategoryModel category, int start, int count) {
        return null;
    }

    @Override
    public Integer getProductsCountForCategory(CategoryModel category) {
        return null;
    }

    @Override
    public Integer getAllProductsCountForCategory(CategoryModel category) {
        return null;
    }

    @Override
    public boolean containsProductsForCategory(CategoryModel category) {
        return false;
    }

    @Override
    public ProductModel getProductForCode(CatalogVersionModel catalogVersion, String code) {
        return null;
    }

    @Override
    public List<ProductModel> getAllProductsForCatalogVersion(CatalogVersionModel catalogVersion) {
        return null;
    }

    public ProductModel getProductForCodeAndName(final String code, String name)
    {
        final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {pk} FROM {Product} WHERE "
                + "{code}=?code AND {name} LIKE %?name%");
        query.addQueryParameter(Product.CODE, code);
        query.addQueryParameter(Product.NAME, name);
        final SearchResult<ProductModel> result = flexibleSearchService.search(query);
        final int resultCount = result.getTotalCount();
        if (resultCount == 0)
        {
            throw new UnknownIdentifierException("Product not found!");
        }
        else if (resultCount > 1)
        {
            throw new AmbiguousIdentifierException("Product code and name is not unique!");
        }
        return result.getResult().get(0);
    }
}
