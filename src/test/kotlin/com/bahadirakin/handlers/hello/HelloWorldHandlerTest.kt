package com.bahadirakin.handlers.hello

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.bahadirakin.handlers.common.SerialiseOperation
import com.bahadirakin.modules.OperationFactory
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.mock
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HelloWorldHandlerTest {

    @Mock
    lateinit var operationFactory: OperationFactory

    @Mock
    lateinit var helloWorldOperation: HelloWorldOperation

    @Mock
    lateinit var serialiseOperation: SerialiseOperation

    @InjectMocks
    lateinit var helloWorldHandler: HelloWorldHandler

    @Test
    fun handleRequest() {
        // Given
        val message = HelloMessage("Hello", "Bahadir")
        val body = "{\"name\":\"Bahadir\", \"saying\":\"Hello\"}"
        given(operationFactory.helloWorldOperation()).willReturn(helloWorldOperation)
        given(operationFactory.serialiseOperation()).willReturn(serialiseOperation)
        given(helloWorldOperation.execute()).willReturn(message)
        given(serialiseOperation.convert(message)).willReturn(body)

        // When
        val result = helloWorldHandler.handleRequest(mock(APIGatewayProxyRequestEvent::class.java), mock(Context::class.java))

        // Then
        then(operationFactory).should().helloWorldOperation()
        then(helloWorldOperation).should().execute()
        assertThat(result.statusCode, equalTo(200))
        assertThat(result.body, equalTo(body))
    }
}