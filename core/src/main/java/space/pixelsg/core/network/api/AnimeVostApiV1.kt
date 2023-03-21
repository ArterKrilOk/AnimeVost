package space.pixelsg.core.network.api

import retrofit2.Response
import retrofit2.http.*
import space.pixelsg.core.network.models.NetworkEpisode
import space.pixelsg.core.network.models.PagedResponse

interface AnimeVostApiV1 {
    @GET("v1/last")
    suspend fun getLastUpdates(
        @Query("page") page: Int,
        @Query("quantity") limit: Int
    ): Response<PagedResponse>


    @POST("v1/playlist")
    @FormUrlEncoded
    suspend fun getEpisodes(
        @Field("id") id: Long
    ): Response<List<NetworkEpisode>>

    @POST("v1/search")
    @FormUrlEncoded
    suspend fun getAnimeByName(
        @Field("name") query: String,
    ): Response<PagedResponse>
}