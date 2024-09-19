# README

## Описание проекта

Данный проект представляет собой тестовое задание для компании **EffectiveMobile**. Цель задания — Android-приложение, отвечающее поставленным условиям (условия представлены по ссылке: https://docs.google.com/document/d/15MkPXaen2j5oe-8ViUUnondUtGN6fnHCZnVnrmTmMnY/edit)

## Стек технологий

- **Язык программирования**: Kotlin
- **Верстка**: XML
- **Работа с сетью**: OkHttp
- **База данных**: Room ORM
- **Dependency injection**: Koin
- **Многопоточность**: Kotlin Coroutines
- **Прочие технологии**: MVVM, Clean Architecture, Adapter Delegates, многомодульность, Kotlin Flow, Navigation API, Single Activity, ViewBindingDelegates

## Установка и запуск

В корне проекта лежит release APK-файл. Установить на устройство или эмулятор с операционной системой Android 7.0 или выше.

## Структура проекта

```bash
.
├── AndroidManifest.xml
├── java
│   └── ru
│       └── logosph
│           └── effectivemobiletesttask
│               ├── App.kt
│               ├── data
│               │   ├── VacanciesApiImpl.kt
│               │   └── favorite
│               │       ├── AddressConverter.kt
│               │       ├── ExperienceConverter.kt
│               │       ├── FavoriteDB.kt
│               │       ├── FavoriteDao.kt
│               │       ├── FavoriteRepositoryImpl.kt
│               │       ├── ListStringConverter.kt
│               │       └── SalaryConverter.kt
│               ├── domain
│               │   ├── interfaces
│               │   │   ├── FavoriteRepository.kt
│               │   │   └── VacanciesApi.kt
│               │   ├── models
│               │   │   ├── Converters.kt
│               │   │   ├── FavoriteEntity.kt
│               │   │   ├── Offer.kt
│               │   │   ├── Response.kt
│               │   │   └── Vacancy.kt
│               │   └── usecases
│               │       ├── DeleteVacancyFromDBUseCase.kt
│               │       ├── LoadAllDataUseCase.kt
│               │       ├── LoadAllFavoritesUseCase.kt
│               │       └── SaveVacancyToDBUseCase.kt
│               └── ui
│                   ├── MainActivity.kt
│                   ├── NetworkStates.kt
│                   ├── di
│                   │   ├── AppModule.kt
│                   │   ├── DataModule.kt
│                   │   └── DomainModule.kt
│                   ├── fragments
│                   │   ├── FavoriteFragment.kt
│                   │   ├── MessagesFragment.kt
│                   │   ├── ProfileFragment.kt
│                   │   ├── ResponsesFragment.kt
│                   │   ├── SearchFragment.kt
│                   │   ├── VacancyFragment.kt
│                   │   └── adapters
│                   │       ├── OfferDelegate.kt
│                   │       ├── ShowMoreDelegate.kt
│                   │       └── VacancyDelegate.kt
│                   └── view_models
│                       ├── FavoriteFragmentViewModel.kt
│                       └── SearchFragmentViewModel.kt
└── res
    ├── colors
    ├── drawable
    │   ├── back_arrow.xml
    │   ├── bottom_navigation_colors.xml
    │   ├── circle_bg.xml
    │   ├── edit_text_background_style.xml
    │   ├── experience.xml
    │   ├── favorite.xml
    │   ├── favorite_filled.xml
    │   ├── filter.xml
    │   ├── ic_launcher_background.xml
    │   ├── ic_launcher_foreground.xml
    │   ├── messages.xml
    │   ├── point.xml
    │   ├── profile.xml
    │   ├── responses.xml
    │   ├── search.xml
    │   ├── sorting.xml
    │   ├── star.xml
    │   ├── vacancies.xml
    │   └── verified.xml
    ├── font
    │   ├── sf_pro_display_black.otf
    │   ├── sf_pro_display_blackitalic.otf
    │   ├── sf_pro_display_bold.otf
    │   ├── sf_pro_display_bolditalic.otf
    │   ├── sf_pro_display_heavy.otf
    │   ├── sf_pro_display_heavyitalic.otf
    │   ├── sf_pro_display_light.otf
    │   ├── sf_pro_display_lightitalic.otf
    │   ├── sf_pro_display_medium.otf
    │   ├── sf_pro_display_mediumitalic.otf
    │   ├── sf_pro_display_regular.otf
    │   ├── sf_pro_display_regularitalic.otf
    │   ├── sf_pro_display_semibold.otf
    │   ├── sf_pro_display_semibolditalic.otf
    │   ├── sf_pro_display_thin.otf
    │   ├── sf_pro_display_thinitalic.otf
    │   ├── sf_pro_display_ultralight.otf
    │   └── sf_pro_display_ultralightitalic.otf
    ├── layout
    │   ├── activity_main.xml
    │   ├── fragment_favorite.xml
    │   ├── fragment_messages.xml
    │   ├── fragment_profile.xml
    │   ├── fragment_responses.xml
    │   ├── fragment_search.xml
    │   ├── fragment_vacancy.xml
    │   ├── offers_item.xml
    │   ├── show_more_item.xml
    │   └── vacancies_item.xml
    ├── menu
    │   └── bottom_navigation_menu.xml
    ├── mipmap-anydpi-v26
    │   ├── ic_launcher.xml
    │   └── ic_launcher_round.xml
    ├── mipmap-hdpi
    │   ├── ic_launcher.webp
    │   └── ic_launcher_round.webp
    ├── mipmap-mdpi
    │   ├── ic_launcher.webp
    │   └── ic_launcher_round.webp
    ├── mipmap-xhdpi
    │   ├── ic_launcher.webp
    │   └── ic_launcher_round.webp
    ├── mipmap-xxhdpi
    │   ├── ic_launcher.webp
    │   └── ic_launcher_round.webp
    ├── mipmap-xxxhdpi
    │   ├── ic_launcher.webp
    │   └── ic_launcher_round.webp
    ├── navigation
    │   └── nav_graph.xml
    ├── values
    │   ├── colors.xml
    │   ├── strings.xml
    │   ├── styles.xml
    │   └── themes.xml
    ├── values-night
    │   └── themes.xml
    └── xml
        ├── backup_rules.xml
        └── data_extraction_rules.xml
```

## Проделанная работа

Во время работы было добавлено 6 фрагментов, один из которых - экран отображения вакансии (заглушка). Была выстроена чистая архитектура и подключен Koin для внедрения зависимостей. В блоке доменной логики расположены модели, юзкейсы интерфейсы для работы с базой данных и сетью. Имплементации этих интерфейсов расположены в блоке data. Для работы с сетью использовался OkHttp, для десериализации данных - kotlin serialization. Для работы с локальными базами данных использовался Room ORM. Для многопоточности использовались Kotlin Coroutines. Также использовались такие библиотеки как AdapterDelegates для избавления от бойлерплейта при создании адаптеров для RecyclerView и ViewBindingDelegates для избавления от бойлерплейта при создании биндингов.

## Демонстрация

При входе в приложения загружается JSON со списком вакансий и рекомендациями, полученные данные отображаются на экране
![telegram-cloud-photo-size-2-5431595198380631404-y](https://github.com/user-attachments/assets/e04b51e6-5536-4a9e-81aa-4d9f668323ef)

По умолчанию изначально отображается первые 3 вакансии и кнопка "Ещё X вакансий"
![telegram-cloud-photo-size-2-5431595198380631405-y](https://github.com/user-attachments/assets/6789a750-7460-439f-81f1-fcce240efa92)

При нажатии на кнопку отображаются все вакансии и изменяется шапка экрана:
![telegram-cloud-photo-size-2-5431595198380631406-y](https://github.com/user-attachments/assets/8fcc5102-9b33-4015-bcfb-1974535da7c3)

При нажатии на карточки рекомендаций происходит переход по ссылке (в нужное приложение или в браузер)
![telegram-cloud-photo-size-2-5431595198380631408-y](https://github.com/user-attachments/assets/12c93d39-f9b5-4cef-a467-baddd66d2e20)

При нажатиин на карточку вакансии отображается залушка:
![telegram-cloud-photo-size-2-5431595198380631409-y](https://github.com/user-attachments/assets/99bc6f6b-5ed8-491d-8174-652afdde7e70)

При нажатии на сердечко вакансия добавляется в избранное (локально на устройстве):
![telegram-cloud-photo-size-2-5431595198380631410-y](https://github.com/user-attachments/assets/36fd7607-5cd3-44f8-84ca-f7e4936a8acb)

Список избранных вакансий во вкладке "Избранное"
![telegram-cloud-photo-size-2-5431595198380631411-y](https://github.com/user-attachments/assets/28e91935-74e9-40f0-963a-aa3f0563bc56)

При нажатии на сердечко ещё раз (во вкладке "Поиск" или "Избранное") - вакансия удаляется 
![telegram-cloud-photo-size-2-5431595198380631412-y](https://github.com/user-attachments/assets/edf1c256-bb95-44f1-8ac8-42f034283aa2)

## Зависимости
```gradle
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.mockito.core.v5130)
    testImplementation(libs.mockito.kotlin)
    implementation(libs.mockito.android)


    // Navigation API
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Requests (OkHttp)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // DI (Koin)
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    // Json (Kotlinx Serialization)
    implementation(libs.kotlinx.serialization.json)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // AdapterDelegates
    implementation(libs.adapterdelegates4.kotlin.dsl)
    implementation(libs.adapterdelegates4.kotlin.dsl.viewbinding)

    // ViewBinding delegates
    implementation(libs.viewbindingpropertydelegate.full)

    // Local DB (Room ORM)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt (libs.androidx.room.compiler)

}
```
