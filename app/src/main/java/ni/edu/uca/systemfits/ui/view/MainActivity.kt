package ni.edu.uca.systemfits.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.toolbar.isVisible = false
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_configuracion -> replaceFragment(configuracion())
            }
            true
        }

        binding.bottomNavigation.isVisible = false
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.menumenu -> replaceFragment(MenuPrincipal())
                R.id.comidamenu -> replaceFragment(Comida())
                R.id.ejerciciosmenu -> replaceFragment(Ejercicios())
                R.id.medidasmenu -> replaceFragment(Medidas())
                R.id.recetasmenu -> replaceFragment(Recetas())
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_configuracion -> {
                replaceFragment(configuracion())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }


    fun showBottomNavigationView() {
        binding.bottomNavigation.isVisible = true
        binding.toolbar.isVisible = true
    }

    fun hideBottomNavigationView() {
        binding.bottomNavigation.isVisible = false
    }
}
