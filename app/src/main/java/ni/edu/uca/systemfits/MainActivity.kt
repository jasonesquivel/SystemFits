package ni.edu.uca.systemfits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_main.*
import ni.edu.uca.systemfits.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val Comida = Comida()
    private val Ejercicios = Ejercicios()
    private val Medidas = Medidas()
    private val Recetas = Recetas()
    private val MenuPrincipal = MenuPrincipal()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.isVisible = false
        binding.bottomNavigation.setOnItemSelectedListener {

            when(it.itemId){

                R.id.menumenu -> replaceFragment(MenuPrincipal())
                R.id.comidamenu -> replaceFragment(Comida())
                R.id.ejerciciosmenu -> replaceFragment(Ejercicios())
                R.id.medidasmenu -> replaceFragment(Medidas())
                R.id.recetasmenu -> replaceFragment(Recetas())
                else ->{



                }

            }

            true


        }


    }


    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()


    }
    fun showBottomNavigationView() {
        binding.bottomNavigation.isVisible = true
    }

    fun hideBottomNavigationView() {
        binding.bottomNavigation.isVisible = false
    }

}
