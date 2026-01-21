package br.com.stoneapp.core.domain.model

/**
 * Modelo de domínio (puro).
 * - Sem dependência de UI/Compose/AndroidX UI
 */
data class Item(
    val id: Long,
    val title: String
)
