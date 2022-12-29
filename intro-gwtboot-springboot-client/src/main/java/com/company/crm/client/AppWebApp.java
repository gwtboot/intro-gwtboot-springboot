package com.company.crm.client;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.company.crm.client.ui.HelloWorldComposite;

@Singleton
public class AppWebApp {

	private HelloWorldComposite helloWorldComposite;

	@Inject
	public AppWebApp(HelloWorldComposite helloWorldComposite) {
		this.helloWorldComposite = helloWorldComposite;
	}
}
