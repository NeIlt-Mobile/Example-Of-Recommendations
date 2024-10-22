package neilt.mobile.android.app.ui.screens.recommendation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun RecommendationScreen(
    viewModel: RecommendationViewModel = viewModel()
) {

    val items by viewModel.items
    val likedLetters = viewModel.likedLetters

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.refreshItems() }) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh Items")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.size, key = { items[it].id }) { index ->
                val item = items[index]

                RecommendationItem(item = item) { isChecked ->
                    viewModel.updateItemLikeStatus(index, item, isChecked)
                }
            }
        }
    }

//    Column(modifier = Modifier.padding(16.dp)) {
//        likedLetters.forEach { (letter, count) ->
//            BasicText(text = "Letter: $letter, Count: $count")
//        }
//    }
}

@Composable
fun RecommendationItem(item: RecommendationItemData, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicText(text = item.name)
        Checkbox(
            checked = item.isLiked,
            onCheckedChange = onCheckedChange
        )
    }
}