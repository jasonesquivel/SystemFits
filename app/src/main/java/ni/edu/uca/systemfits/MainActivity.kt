package ni.edu.uca.systemfits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ni.edu.uca.systemfits.databinding.ActivityMainBinding
import ni.edu.uca.systemfits.vistas.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    fun showBottomNavigationView() {
        binding.bottomNavigation.isVisible = true
    }

    fun hideBottomNavigationView() {
        binding.bottomNavigation.isVisible = false
    }
}
