package com.company.crm.client;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, HomeView.class })
public interface AppComponent {

	AppWebApp getAppWebApp();

	void inject(AppEntryPoint appEntryPoint);

}
