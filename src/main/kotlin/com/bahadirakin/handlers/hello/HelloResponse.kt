package com.bahadirakin.handlers.hello

/**
 * Response of hello world handler.
 */
data class HelloResponse(val message: String, val input: Map<String, Any>)
