package com.company.crm.client;

import java.util.logging.Logger;

import org.dominokit.rest.DominoRestConfig;

import com.company.crm.shared.PersonEndpoint;
import com.google.gwt.core.client.EntryPoint;

public class AppEntryPoint implements EntryPoint {

	private static Logger logger = Logger.getLogger(AppEntryPoint.class.getName());

	@Override
	public void onModuleLoad() {
		logger.info("Inject clientBundle");
		HomeClientBundle.BUNDLE.css().ensureInjected();

		logger.info("Create component Dagger2");
		DaggerAppComponent.builder().build().getAppWebApp();

		DominoRestConfig.initDefaults();
		DominoRestConfig.getInstance().setDefaultServiceRoot(PersonEndpoint.SERVER_CONTEXT_PATH);
	}
}
