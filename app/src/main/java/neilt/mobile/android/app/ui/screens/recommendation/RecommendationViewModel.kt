package neilt.mobile.android.app.ui.screens.recommendation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel

class RecommendationViewModel : ViewModel() {
    private val _items = mutableStateOf(generateRandomItemList())
    val items: State<List<RecommendationItemData>> = _items

    private val _likedLetters = mutableStateMapOf<Char, Int>()
    val likedLetters: SnapshotStateMap<Char, Int> = _likedLetters

    fun refreshItems() {
        _items.value = generateRandomItemList(_likedLetters.toList().sortedByDescending { it.second }.map { it.first })
    }

    fun updateItemLikeStatus(index: Int, item: RecommendationItemData, isChecked: Boolean) {
        val updatedList = _items.value.toMutableList().apply {
            this[index] = item.copy(isLiked = isChecked)
        }
        _items.value = updatedList

        updateLikedLetters(item, isChecked)
    }

    private fun updateLikedLetters(item: RecommendationItemData, isLiked: Boolean) {
        item.name.forEach { letter ->
            if (isLiked) {
                _likedLetters[letter] = _likedLetters.getOrDefault(letter, 0) + 1
            } else {
                val currentCount = _likedLetters[letter] ?: 0
                if (currentCount > 1) {
                    _likedLetters[letter] = currentCount - 1
                } else {
                    _likedLetters.remove(letter)
                }
            }
        }
    }
}