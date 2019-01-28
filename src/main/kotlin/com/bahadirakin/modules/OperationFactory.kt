package com.bahadirakin.modules

import com.bahadirakin.handlers.common.SerialiseOperation
import com.bahadirakin.handlers.hello.HelloWorldOperation
import dagger.Component
import javax.inject.Singleton

/**
 * Creates operations for handlers.
 */
@Singleton
@Component(modules = [ServiceModule::class])
interface OperationFactory {

    /**
     * Creates a hello world operation.
     */
    fun helloWorldOperation(): HelloWorldOperation

    /**
     * Creates a serialisation model.
     */
    fun serialiseOperation(): SerialiseOperation
}