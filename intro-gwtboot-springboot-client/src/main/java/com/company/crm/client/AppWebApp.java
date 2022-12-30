package com.company.crm.client;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppWebApp {

	private HomeComposite helloWorldComposite;

	@Inject
	public AppWebApp(HomeComposite helloWorldComposite) {
		this.helloWorldComposite = helloWorldComposite;
	}
}
