package com.skyscanner.mini_skyscanner.exception

import java.lang.RuntimeException

// Exception used to handle all unexpected exceptions
class UnexpectedException(message: String): RuntimeException(message)