# Rick And Morty App

Aplicacion para prueba exportacion de logica android a Ios usando el plugin de kotlin multiplatform segun documentacion https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-integrate-in-existing-app.html.

## Descripción General

Este proyecto consiste en una aplicación Android desarrollada inicialmente con Jetpack Compose. Posteriormente, se ha migrado a Kotlin Multiplatform (KMP) para compartir lógica de negocio entre diferentes plataformas (actualmente Android, con posibilidad de extender a iOS u otras). La inyección de dependencias en el módulo compartido se gestiona mediante **Koin**.

## Ramas

*   **`main`**: Contiene la versión original de la aplicación Android desarrollada exclusivamente con Jetpack Compose.
*   **`kmp-migration`**: Contiene la versión de la aplicación con la migración a Kotlin Multiplatform. Esta rama incluye el módulo compartido KMP y las adaptaciones necesarias en la aplicación Android para utilizar dicho módulo.

## Tecnologías Utilizadas

*   **Kotlin**: Lenguaje principal de programación.
*   **Jetpack Compose**: Para la interfaz de usuario de la aplicación Android.
*   **Kotlin Multiplatform (KMP)**: Para compartir código entre plataformas.
*   **Koin**: Para la inyección de dependencias, especialmente en el módulo `shared`.
*   **[Opcional: Añade otras bibliotecas importantes que estés usando, por ejemplo, Ktor para networking, SQLDelight para base de datos, etc.]**

## Estructura del Proyecto (en la rama `kmp-migration`)

*   **`androidApp`**: Módulo específico para la aplicación Android.
*   **`shared`**: Módulo Kotlin Multiplatform que contiene la lógica de negocio compartida.
    *   **`commonMain`**: Código común para todas las plataformas. Aquí se definen los módulos de Koin, como se observa en `RepositoryModule.kt`.
    *   **`androidMain`**: Código específico para la plataforma Android dentro del módulo compartido.
    *   **`iosMain`**: (Si aplica) Código específico para la plataforma iOS dentro del módulo compartido.

**Notas Adicionales:**

*   **Documentación Oficial:** La documentación oficial de Android sobre la migración a KMP es un buen punto de partida: [Add Kotlin Multiplatform to an existing project](https://developer.android.com/kotlin/multiplatform/migrate).
* 
