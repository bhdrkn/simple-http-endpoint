package com.bahadirakin.modules

import com.bahadirakin.services.NameService
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module for dependent services.
 */
@Module
class ServiceModule {

    /**
     * Provides a name service instance to upper layers.
     */
    @Provides
    @Singleton
    fun provideNameService() = NameService()

    /**
     * Provides JSON-OBJ mapper.
     */
    @Provides
    @Singleton
    fun provideObjectMapper() = ObjectMapper()
}