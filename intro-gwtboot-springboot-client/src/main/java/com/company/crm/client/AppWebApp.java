package com.company.crm.client;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppWebApp {

	private HelloWorldComposite helloWorldComposite;

	@Inject
	public AppWebApp(HelloWorldComposite helloWorldComposite) {
		this.helloWorldComposite = helloWorldComposite;
	}
}
