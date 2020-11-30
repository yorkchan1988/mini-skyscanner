package com.skyscanner.mini_skyscanner.exception

// Exception used to handle errors returned from server
class ApiException constructor(val name: String, override val message: String) : RuntimeException(message) {
}