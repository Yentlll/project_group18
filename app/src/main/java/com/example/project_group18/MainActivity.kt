package com.example.project_group18

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.project_group18.ui.LoginScreen
import com.example.project_group18.ui.theme.Projectgroup18Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Projectgroup18Theme {
                LoginScreen(
                    user = "Testuser"
                )
            }
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Projectgroup18Theme {
        LoginScreen("Testuser")
    }
}