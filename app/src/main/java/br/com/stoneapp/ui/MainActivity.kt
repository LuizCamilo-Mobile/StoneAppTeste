package br.com.stoneapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.stoneapp.R
import br.com.stoneapp.di.AppContainer
import br.com.stoneapp.di.MainViewModelFactory
import br.com.stoneapp.feature.home.ui.MainFragment

class MainActivity : AppCompatActivity() {

    private val container by lazy { AppContainer() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val factory = MainViewModelFactory(container.getItemsUseCase)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MainFragment(factory))
                .commit()
        }
    }
}
