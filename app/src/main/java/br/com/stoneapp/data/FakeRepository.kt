package br.com.stoneapp.data

import android.R.string.ok
import kotlinx.coroutines.delay
import kotlin.random.Random

class FakeRepository {
    suspend fun fetch(): String {
        delay(3000)

        val ok = Random.nextInt(100) < 5
        if (!ok) error("Falha simulada: tente novamente")
        return "Sucesso! Dados carregados às ${System.currentTimeMillis()}."

    }
}