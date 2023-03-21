package space.pixelsg.core.network.models

data class PagedResponse(
    val state: State,
    val data: List<NetworkTitle>
)
