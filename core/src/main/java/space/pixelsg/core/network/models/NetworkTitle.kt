package space.pixelsg.core.network.models

import org.json.JSONObject

data class NetworkTitle(
    val id: Long,
    val title: String,
    val description: String,
    val genre: String,
    val year: String,
    val urlImagePreview: String,
    val screenImage: List<String>,
    val rating: Int,
    val votes: Int,
    val timer: String,
    val type: String,
    val director: String,
    val series: String, // {'1 серия':'2147423365',...,'N серия':'2147423365'}
) {
    fun getSeriePairs(): List<Pair<String, String>> {
        val string = series.replace("'", "\"")
        val json = JSONObject(string)

        return json.keys()
            .asSequence()
            .toList()
            .map {
                it to json.getString(it)
            }

    }

}

