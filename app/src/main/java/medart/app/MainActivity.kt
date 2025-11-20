package medart.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import medart.app.navigation.MedArtApp

class MainActivity : ComponentActivity() {
//Merge Branch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedArtApp()
        }
    }
}
