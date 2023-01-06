package com.company.crm.client;

import javax.inject.Singleton;

import com.company.crm.shared.PersonApi;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

	@Provides
	@Singleton
	PersonApi personApi() {
		return new PersonClient();
	}

}
