package ni.edu.uca.systemfits

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    var username: String?
        get() = sharedPref.getString("username", null)
        set(value) = sharedPref.edit().putString("username", value).apply()
}
