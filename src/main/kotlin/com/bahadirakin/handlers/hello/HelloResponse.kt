package com.bahadirakin.handlers.hello

import com.bahadirakin.handlers.Response

/**
 * Response of hello world handler.
 */
data class HelloResponse(val message: String, val input: Map<String, Any>) : Response()
