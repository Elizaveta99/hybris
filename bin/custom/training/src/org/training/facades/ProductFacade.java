package org.training.facades;

import org.training.data.ProductData;

public interface ProductFacade
{
    public ProductData getProduct(String productCode);
}