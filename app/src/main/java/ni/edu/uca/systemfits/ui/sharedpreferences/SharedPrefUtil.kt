package ni.edu.uca.systemfits.ui.sharedpreferences

import android.content.Context

object SharedPrefUtil {
    private const val PREF_NAME = "my_preferences"
    private const val KEY_USUARIO = "usuario"

    fun guardarUsuario(context: Context, usuario: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USUARIO, usuario)
        editor.apply()
    }

    fun obtenerUsuario(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USUARIO, null)
    }
}
