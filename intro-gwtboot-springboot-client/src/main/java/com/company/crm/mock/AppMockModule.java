package com.company.crm.mock;

import javax.inject.Singleton;

import com.company.crm.shared.PersonApi;

import dagger.Module;
import dagger.Provides;

@Module
public class AppMockModule {

	@Provides
	@Singleton
	PersonApi personApi() {
		return new PersonClientMock();
	}

}
