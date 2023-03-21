package space.pixelsg.core.network.exception

open class ApiException(message: String) : RuntimeException(message)

class HttpApiException(code: Int, message: String = "") :
    ApiException("HTTP Exception $code \n$message")

class NullResponseException(message: String = "") :
    ApiException("Response is NULL \n$message")