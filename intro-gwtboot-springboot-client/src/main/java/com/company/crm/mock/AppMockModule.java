package com.company.crm.mock;

import javax.inject.Singleton;

import com.company.crm.client.PersonClientFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class AppMockModule {

    @Provides
    @Singleton
    PersonClientFactory personClientFactory() {
        return new PersonMockClientFactory();
    }

}
