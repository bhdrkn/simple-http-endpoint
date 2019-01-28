package com.bahadirakin.handlers.hello

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.bahadirakin.modules.DaggerOperationFactory
import com.bahadirakin.modules.OperationFactory
import org.apache.logging.log4j.LogManager

/**
 * Handler for hello world operation.
 */
class HelloWorldHandler : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private val factory: OperationFactory

    constructor() {
        factory = DaggerOperationFactory.create()
    }

    constructor(factory: OperationFactory) {
        this.factory = factory
    }

    override fun handleRequest(event: APIGatewayProxyRequestEvent, context: Context): APIGatewayProxyResponseEvent {
        LOG.info("received: " + event.path)

        val helloWorldOperation = factory.helloWorldOperation()
        val helloMessage = helloWorldOperation.execute()
        val body = factory.serialiseOperation().convert(helloMessage)

        return APIGatewayProxyResponseEvent()
                .withBody(body)
                .withStatusCode(OK)
                .withHeaders(mapOf("X-Powered-By" to "AWS Lambda & serverless"))
    }

    companion object {
        private val LOG = LogManager.getLogger(HelloWorldHandler::class.java)
        private const val OK = 200
    }
}
