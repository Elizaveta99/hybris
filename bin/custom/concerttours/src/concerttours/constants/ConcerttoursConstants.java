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
package concerttours.constants;

/**
 * Global class for all Concerttours constants. You can add global constants for your extension into this class.
 */
public final class ConcerttoursConstants extends GeneratedConcerttoursConstants {
	public static final String EXTENSIONNAME = "concerttours";

	private ConcerttoursConstants() {
		//empty to avoid instantiating this constant class
	}

	public static final String NEW_BAND_HEADLINE = "New band, %s";
	public static final String NEW_BAND_CONTENT = "There is a new band in town called, %s. Tour news to be announced soon.";
	public static final long BIG_SALES = 50000L;
	public static final long NEGATIVE_SALES = 0L;
	public static final String BAND_SALES_HEADLINE = "%s album sales exceed 50000";
	public static final String BAND_SALES_CONTENT = "%s album sales reported as %d";

	public static final String PLATFORM_LOGO_CODE = "concerttoursPlatformLogo";
}
