package com.bahadirakin.handlers.hello

import com.amazonaws.services.lambda.runtime.Context
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

    @InjectMocks
    lateinit var helloWorldHandler: HelloWorldHandler

    @Test
    fun handleRequest() {
        // Given
        val name = "Bahadir"
        given(operationFactory.helloWorldOperation()).willReturn(helloWorldOperation)
        given(helloWorldOperation.execute()).willReturn(name)

        // When
        val result = helloWorldHandler.handleRequest(HashMap(), mock(Context::class.java))

        // Then
        then(operationFactory).should().helloWorldOperation()
        then(helloWorldOperation).should().execute()
        assertThat(result.statusCode, equalTo(200))
        assertThat(result.body, equalTo(String.format("{\"message\":\"%s\",\"input\":{}}", name)))
    }
}