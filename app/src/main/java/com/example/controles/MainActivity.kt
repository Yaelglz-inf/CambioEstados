package com.example.controles

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.controles.databinding.ActivityMainBinding


class MainViewModel: ViewModel(){

    var contador: Int = 0


}
const val CONTADOR_KEY = "contador_key"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    companion object {
        private const val KEY_CONTADOR = "valor_contador"
    }

    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.contador = savedInstanceState?.getInt(CONTADOR_KEY) ?: 0

        actualizarUI()


        binding.button2.setOnClickListener {

            viewModel.contador += 1

            actualizarUI()
            Toast.makeText(this@MainActivity, "contador: ${viewModel.contador}", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CONTADOR_KEY, viewModel.contador)
    }


    private fun actualizarUI() {
        binding.textView2.text = "contador: ${viewModel.contador}"
    }
}