package neilt.mobile.android.app.ui.screens.recommendation

import java.util.UUID

data class RecommendationItemData(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    var isLiked: Boolean = false,
)