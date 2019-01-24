package com.bahadirakin.handlers.hello

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.bahadirakin.handlers.ApiGatewayResponse
import com.bahadirakin.modules.DaggerOperationFactory
import com.bahadirakin.modules.OperationFactory
import org.apache.logging.log4j.LogManager

/**
 * Handler for hello world operation.
 */
class HelloWorldHandler : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    private val factory: OperationFactory

    constructor() {
        factory = DaggerOperationFactory.create()
    }

    constructor(factory: OperationFactory) {
        this.factory = factory
    }

    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        LOG.info("received: " + input.keys.toString())

        val helloWorldOperation = factory.helloWorldOperation()
        val name = helloWorldOperation.execute()

        return ApiGatewayResponse.build {
            objectBody = HelloResponse(name, input)
            headers = mapOf("X-Powered-By" to "AWS Lambda & serverless")
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(HelloWorldHandler::class.java)
    }
}
