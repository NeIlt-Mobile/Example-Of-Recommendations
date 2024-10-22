package neilt.mobile.android.app.ui.screens.recommendation

fun generateRandomItemList(popularLetters: List<Char> = emptyList()): List<RecommendationItemData> {
    val allItems = List(20) {
        RecommendationItemData(name = generateRandomString())
    }

    if (popularLetters.isEmpty()) return allItems

    val mostPopularLetter = popularLetters.firstOrNull()
    return allItems.sortedByDescending { item ->
        if (mostPopularLetter != null) {
            item.name.count { it == mostPopularLetter }
        } else {
            item.name.count { it in popularLetters }
        }
    }
}

fun generateRandomString(popularLetters: List<Char> = emptyList()): String {
    val length = 8
    val chars = ('A'..'Z') + ('a'..'z')
    val randomPart = (1..length).map { chars.random() }.joinToString("")

    val lettersToAdd = popularLetters.take(length).joinToString("")
    return (lettersToAdd + randomPart).take(length)
}