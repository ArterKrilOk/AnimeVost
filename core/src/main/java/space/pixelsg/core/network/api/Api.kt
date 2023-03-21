package space.pixelsg.core.network.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import space.pixelsg.core.network.exception.HttpApiException
import space.pixelsg.core.network.exception.NullResponseException

object Api {
    suspend fun <T> apiCall(block: suspend () -> Response<T>): T = withContext(Dispatchers.IO) {
        val result = block()
        if (!result.isSuccessful) throw HttpApiException(result.code(), result.message() ?: "")
        else if (result.body() == null) throw NullResponseException(result.message() ?: "")
        else result.body()!!
    }
}