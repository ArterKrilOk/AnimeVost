package space.pixelsg.core.models

data class Paged<T>(
    val count: Int,
    val page: Int,
    val data: List<T>
)
