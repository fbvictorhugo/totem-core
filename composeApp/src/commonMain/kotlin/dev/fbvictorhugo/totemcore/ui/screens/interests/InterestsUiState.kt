package dev.fbvictorhugo.totemcore.ui.screens.interests

val mockInterestsList = listOf(
    "Roupas (Adulto)", "Roupas Infantis", "Calçados",
    "Fraldas", "Cesta Básica", "Colchão", "Sofá", "Geladeira", "Fogão",
    "Móveis em Geral", "Eletrodomésticos", "Produtos de Higiene",
    "Água Potável", "Produtos de Limpeza", "Cobertores", "Material de Construção"
)

data class InterestsUiState(
    val interests: List<String> = mockInterestsList,
    val selectedInterests: List<String> = emptyList(),
) {

    val isInterestsSelected: Boolean get() = selectedInterests.isNotEmpty()

    fun getMessage(singular: String, plural: String): String {
        return if (selectedInterests.size == 1)
            "${selectedInterests.size} $singular"
        else
            "${selectedInterests.size} $plural"
    }
}