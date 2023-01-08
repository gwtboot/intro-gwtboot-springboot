package com.company.crm.mock;

import javax.inject.Singleton;

import com.company.crm.client.PersonCallbackApi;

import dagger.Module;
import dagger.Provides;

@Module
public class AppMockModule {

	@Provides
	@Singleton
	PersonCallbackApi personCallbackApi() {
		return new PersonClientMock();
	}

}
