package space.pixelsg.core.models

data class Title(
    val id: Long,
    val title: String,
    val description: String,
    val year: String,
    val genres: String,
    val posterUrl: String,
    val rating: Int,
    val votesCount: Int,
    val episodesCount: Int,
    val screenshots: List<String>,
    val colors: Pair<String, String>? = null
) {
    val simpleName: String
        get() {
            return title.split("/").first().trim().removeBrackets()
        }

    private fun String.removeBrackets(): String {
        var result = ""
        var opened = 0
        forEach {
            if (it == '[') opened++
            if (it == ']') opened--
            if (opened == 0) result += it
        }
        return result
    }
}
