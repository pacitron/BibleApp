package com.example.bibleapp

import android.app.Application
import com.youversion.platform.core.YouVersionPlatformConfiguration

/**
 * Custom Application class.
 *
 * Android creates exactly one instance of this per app launch, before any
 * Activity or Service is started — which makes it the right place to
 * initialise third-party SDKs like YouVersion.
 *
 * Registered in AndroidManifest.xml via  android:name=".BibleApp"
 */
class BibleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // ⚠️  IMPORTANT: replace the string below with your real App Key.
        //     Get one for free at https://platform.youversion.com
        //     Without a valid key the app will open but Bible content won't load.
        YouVersionPlatformConfiguration.configure(
            this,
            "YOUR_ACTUAL_APP_KEY_HERE"
        )
    }
}
