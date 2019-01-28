package com.bahadirakin.handlers.common

import com.fasterxml.jackson.databind.ObjectMapper
import javax.inject.Inject

/**
 * Serialises given object to string.
 */
class SerialiseOperation @Inject constructor(
    private val objectMapper: ObjectMapper
) {
    /**
     * Converts given object to string.
     */
    fun convert(obj: Any) = objectMapper.writeValueAsString(obj)
}