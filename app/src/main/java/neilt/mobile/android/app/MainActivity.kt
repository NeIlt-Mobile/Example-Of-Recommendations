package neilt.mobile.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import neilt.mobile.android.app.ui.screens.recommendation.RecommendationScreen
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