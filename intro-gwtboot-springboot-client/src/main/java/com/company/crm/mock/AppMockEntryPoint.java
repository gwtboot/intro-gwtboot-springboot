package com.company.crm.mock;

import java.util.logging.Logger;

import com.company.crm.client.HomeClientBundle;
import com.google.gwt.core.client.EntryPoint;

public class AppMockEntryPoint implements EntryPoint {

	private static Logger logger = Logger.getLogger(AppMockEntryPoint.class.getName());

	@Override
	public void onModuleLoad() {
		logger.info("Inject clientBundle");
		HomeClientBundle.BUNDLE.css().ensureInjected();

		logger.info("Create mock component Dagger2");
		DaggerAppMockComponent.builder().build().getAppWebApp();
	}
}
