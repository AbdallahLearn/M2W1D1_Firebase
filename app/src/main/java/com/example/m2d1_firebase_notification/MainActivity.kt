package com.example.m2d1_firebase_notification

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.m2d1_firebase_notification.ui.theme.M2D1_Firebase_NotificationTheme
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()

        firebaseAnalytics = Firebase.analytics

        // ✅ Log "app_open"
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)

        // ✅ Log "notification_opened" if intent has extras (notification click)
        intent.extras?.let {
            val bundle = Bundle()
            for (key in it.keySet()) {
                bundle.putString(key, it.get(key).toString())
            }
            firebaseAnalytics.logEvent("notification_opened", bundle)
        }

        // ✅ Fetch & log FCM token
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result
            Log.d("FCM", "FCM Token: $token")

            val bundle = Bundle()
            bundle.putString("fcm_token", token)
            firebaseAnalytics.logEvent("fcm_token_generated", bundle)
        }

        setContent {
            M2D1_Firebase_NotificationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // ✅ Log screen view custom event
    Firebase.analytics.logEvent("screen_view_greeting", null)

    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    M2D1_Firebase_NotificationTheme {
        Greeting("Android")
    }
}
