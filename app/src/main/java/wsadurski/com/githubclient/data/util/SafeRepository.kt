package wsadurski.com.githubclient.data.util

import arrow.core.Either
import retrofit2.Call
import retrofit2.HttpException
import wsadurski.com.githubclient.domain.error.ErrorThrowableWrapper
import java.io.IOException

open class SafeRepository {

    fun <T, R> executeSafely(call: Call<T>, transform: (T) -> R, default: T): Either<ErrorThrowableWrapper, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(ErrorThrowableWrapper.ServerError(RuntimeException("Response not successful!")))
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> Either.Left(ErrorThrowableWrapper.ServerError(throwable))
                is IOException -> Either.Left(ErrorThrowableWrapper.NetworkConnection(throwable))
                else -> Either.Left(ErrorThrowableWrapper.UnknownError(throwable))
            }
        }
    }
}