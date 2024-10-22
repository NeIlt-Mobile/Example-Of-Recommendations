package neilt.mobile.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neilt.mobile.android.app.ui.theme.ExampleOfRecommendationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExampleOfRecommendationsTheme {
                RecommendationScreen()
            }
        }
    }
}

@Composable
fun RecommendationScreen() {
    var items by remember { mutableStateOf(generateRandomItemList()) }
    val likedLetters = remember { mutableStateMapOf<Char, Int>() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                items = generateRandomItemList(20)
            }) {
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
            items(items.size) { index ->
                val itemName = items[index]
                var isLiked by remember { mutableStateOf(false) }

                RecommendationItem(name = itemName, isLiked = isLiked) { isChecked ->
                    isLiked = isChecked
                    if (isChecked) {
                        itemName.forEach { letter ->
                            likedLetters[letter] = likedLetters.getOrDefault(letter, 0) + 1
                        }
                    } else {
                        itemName.forEach { letter ->
                            val currentCount = likedLetters[letter] ?: 0
                            if (currentCount > 1) {
                                likedLetters[letter] = currentCount - 1
                            } else {
                                likedLetters.remove(letter)
                            }
                        }
                    }
                }
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        likedLetters.forEach { (letter, count) ->
            BasicText(text = "Letter: $letter, Count: $count")
        }
    }
}

@Composable
fun RecommendationItem(name: String, isLiked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicText(text = name)
        Checkbox(
            checked = isLiked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationScreenPreview() {
    RecommendationScreen()
}

private fun generateRandomString(length: Int = 8): String {
    val chars = ('A'..'Z') + ('a'..'z')
    return (1..length).map { chars.random() }.joinToString("")
}

private fun generateRandomItemList(size: Int = 20): List<String> {
    return List(size) { generateRandomString() }
}