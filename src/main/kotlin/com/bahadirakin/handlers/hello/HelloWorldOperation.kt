package com.bahadirakin.handlers.hello

import com.bahadirakin.services.NameService
import javax.inject.Inject

/**
 * Operation executed by Hello World handler.
 */
class HelloWorldOperation @Inject constructor(private val nameService: NameService) {

    /**
     * Executes hello world operation.
     */
    fun execute() = "Hello, " + nameService.provideName()
}