package wsadurski.com.githubclient.domain.error

sealed class ErrorThrowableWrapper(val throwable: Throwable) {
    class NetworkConnection(throwable: Throwable) : ErrorThrowableWrapper(throwable)
    class ServerError(throwable: Throwable) : ErrorThrowableWrapper(throwable)
    class UnknownError(throwable: Throwable) : ErrorThrowableWrapper(throwable)
}
