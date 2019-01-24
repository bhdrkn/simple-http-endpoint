package com.bahadirakin.handlers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.Collections

/**
 * An API Gateway response generated by serverless framework.
 */
data class ApiGatewayResponse(
    val statusCode: Int = OK,
    var body: String? = null,
    val headers: Map<String, String>? = Collections.emptyMap(),
    val isBase64Encoded: Boolean = false
) {

    companion object {
        private const val OK = 200
        /**
         * Creates a builder instance.
         */
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    /**
     * A fluent builder for ApiGatewayResponse.
     */
    class Builder {
        private val log: Logger = LogManager.getLogger(Builder::class.java)
        // FIXME: Make it singleton and inject!
        var objectMapper: ObjectMapper = ObjectMapper()

        var statusCode: Int = OK
        var headers: Map<String, String>? = Collections.emptyMap()
        var objectBody: Response? = null
        var binaryBody: ByteArray? = null
        var base64Encoded: Boolean = false

        /**
         * Builds instance.
         */
        fun build(): ApiGatewayResponse {
            var body: String? = null

            if (objectBody != null) {
                try {
                    body = objectMapper.writeValueAsString(objectBody)
                } catch (e: JsonProcessingException) {
                    log.error("failed to serialize object", e)
                    throw e
                }
            } else if (binaryBody != null) {
                body = String(Base64.getEncoder().encode(binaryBody), StandardCharsets.UTF_8)
            }
            return ApiGatewayResponse(statusCode, body, headers, base64Encoded)
        }
    }
}
