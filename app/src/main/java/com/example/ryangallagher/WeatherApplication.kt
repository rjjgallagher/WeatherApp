package com.example.ryangallagher

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

 /* If you want a place to tie in some hooks at the beginning of your application, you can create
    an application class to do that. In order for Hilt to work, you need to update the application tag
    in the AndroidManifest under the android tag. We've done that. You should see a
    android:name=."WeatherApplication" line in there.
 */
@HiltAndroidApp //This is just telling Hilt that this is where our application starts.
class WeatherApplication: Application()