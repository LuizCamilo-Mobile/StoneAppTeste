plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "br.com.stoneapp.core.domain"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    // produção
    implementation(libs.androidx.core.ktx)

    // testes unitários
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}


