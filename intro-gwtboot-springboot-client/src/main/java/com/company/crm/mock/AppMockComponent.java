package com.company.crm.mock;

import javax.inject.Singleton;

import com.company.crm.client.AppWebApp;
import com.company.crm.client.HomeView;

import dagger.Component;

@Singleton
@Component(modules = { AppMockModule.class, HomeView.class })
public interface AppMockComponent {

	AppWebApp getAppWebApp();

	void inject(AppMockEntryPoint appMockEntryPoint);

}
