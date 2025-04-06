Firebase Notification & Analytics Integration
Description
This Android application demonstrates the integration of Firebase Cloud Messaging (FCM) for push notifications and Firebase Analytics for event tracking. It is built using Jetpack Compose and written in Kotlin.

The project fulfills the following requirements:

Receives and displays notifications via Firebase Cloud Messaging.

Generates and logs the FCM registration token on startup.

Integrates Firebase Analytics to track key events and custom actions.

How to Run the Project
Clone the Repository

bash
Copy
Edit
git clone https://github.com/AbdallahLearn/M2W1D1_Firebase.git
Open the Project
Open the project in Android Studio.

Configure Firebase

Add your google-services.json file in the app/ directory.

Ensure your Firebase project has Firebase Messaging and Firebase Analytics enabled.

Build and Run
Connect a device or use an emulator, then click Run.

Events Tracked and How They Were Tested
Firebase Cloud Messaging
FCM Token Generated

The token is logged on app startup via FirebaseMessaging.getInstance().token.

Verified through Logcat.

Test Notification

A notification was sent using the Firebase Console.

Displayed correctly on the device using a custom notification handler.

Firebase Analytics
App Opened

Logged using FirebaseAnalytics.Event.APP_OPEN on MainActivity launch.

Notification Received

Logged in FirebaseService.onMessageReceived() when a notification is received.

Notification Opened

Automatically logged by Firebase when the user taps the notification.

Custom Event: FCM Token Generated

Logged as a custom event: fcm_token_generated.
