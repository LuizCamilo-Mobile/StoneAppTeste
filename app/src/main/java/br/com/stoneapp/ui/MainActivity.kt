package br.com.stoneapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.stoneapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Single-Activity: o FragmentContainerView já instancia o MainFragment via android:name.

    }
}