/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package concerttours.service.impl;

import concerttours.service.ConcerttoursService;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Optional;

public class DefaultConcerttoursService implements ConcerttoursService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultConcerttoursService.class);
	private final static String FIND_LOGO_QUERY = "SELECT {" + CatalogUnawareMediaModel.PK + "} FROM {"
			+ CatalogUnawareMediaModel._TYPECODE + "} WHERE {" + CatalogUnawareMediaModel.CODE + "}=?code";

	private MediaService mediaService;
	private ModelService modelService;
	private FlexibleSearchService flexibleSearchService;

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public String getHybrisLogoUrl(String logoCode) {
		MediaModel media = mediaService.getMedia(logoCode);

		// Keep in mind that with Slf4j you don't need to check if debug is enabled, it is done under the hood.
		LOG.debug("Found media [code: {}]", media.getCode());

		return media.getURL();
	}

	@Override
	public void createLogo(String logoCode) {
		Optional<CatalogUnawareMediaModel> existingLogo = findExistingLogo(logoCode);

		CatalogUnawareMediaModel media = Optional.of(existingLogo).get()
				.orElse(modelService.create(CatalogUnawareMediaModel.class));

		media.setCode(logoCode);
		media.setRealFileName("sap-hybris-platform.png");
		modelService.save(media);

		mediaService.setStreamForMedia(media, getImageStream());
	}

	private Optional<CatalogUnawareMediaModel> findExistingLogo(final String logoCode) {
		FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_LOGO_QUERY);
		fQuery.addQueryParameter("code", logoCode);

		try
		{
			return Optional.of(flexibleSearchService.searchUnique(fQuery));
		}
		catch (final SystemException e)
		{
			return Optional.empty();
		}
	}

	private InputStream getImageStream()
	{
		return DefaultConcerttoursService.class.getResourceAsStream("/concerttours/sap-hybris-platform.png");
	}
}
