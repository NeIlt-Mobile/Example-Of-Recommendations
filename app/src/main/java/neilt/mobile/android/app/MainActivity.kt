package neilt.mobile.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        val exampleList = remember { List(20) { generateRandomString(8) } }
                        RecommendationScreen(exampleList)
                    }
                }
            }
        }
    }
}

@Composable
fun RecommendationScreen(
    items: List<String>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items.size) { index ->
            val itemName = items[index]
            var isLiked by remember { mutableStateOf(false) }

            RecommendationItem(name = itemName, isLiked = isLiked) { isChecked ->
                isLiked = isChecked
            }
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
    val randomItems = List(20) { generateRandomString(8) }
    RecommendationScreen(items = randomItems)
}

private fun generateRandomString(length: Int): String {
    val chars = ('A'..'Z') + ('a'..'z')
    return (1..length).map { chars.random() }.joinToString("")
}