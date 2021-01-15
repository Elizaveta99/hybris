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
package concerttoursdependent.setup;

import static concerttoursdependent.constants.ConcerttoursdependentConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import concerttoursdependent.constants.ConcerttoursdependentConstants;
import concerttoursdependent.service.ConcerttoursdependentService;


@SystemSetup(extension = ConcerttoursdependentConstants.EXTENSIONNAME)
public class ConcerttoursdependentSystemSetup
{
	private final ConcerttoursdependentService concerttoursdependentService;

	public ConcerttoursdependentSystemSetup(final ConcerttoursdependentService concerttoursdependentService)
	{
		this.concerttoursdependentService = concerttoursdependentService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		concerttoursdependentService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ConcerttoursdependentSystemSetup.class.getResourceAsStream("/concerttoursdependent/sap-hybris-platform.png");
	}
}
