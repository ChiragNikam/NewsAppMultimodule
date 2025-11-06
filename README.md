# 📰 Multi-Module News App  
### Android | Jetpack Compose | Firebase | Retrofit | MVVM

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-%2300C4B3.svg?logo=jetpackcompose&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-Auth-orange?logo=firebase)
![Retrofit](https://img.shields.io/badge/Retrofit-2.11.0-green)

---

## 📱 Overview
A modern Android application built with **Jetpack Compose** and **multi-module architecture**, featuring secure authentication with **Firebase**, live **News headlines** using the [NewsAPI.org](https://newsapi.org), and seamless navigation between modules.

This project demonstrates clean architecture, modular development, and real-world app structure using Compose and MVVM.

---

## 🚀 Features
- 🔐 **Firebase Authentication** – Sign up and sign in via email & password.  
- 📰 **Live News Feed** – Browse top headlines and search for specific topics.  
- 🧩 **Multi-Module Architecture** – Decoupled Auth, News, and Theme modules.  
- 🧠 **MVVM Pattern** – Separation of concerns with ViewModel + Repository.  
- 🌐 **Retrofit + Moshi** – Type-safe API calls and JSON parsing.  
- 🖼 **Coil** – Fast and cached image loading.  
- 🎨 **Material 3 Design** – Modern Compose UI with theming support.  
- 🔄 **Smooth State Management** – Loading, success, and error handling.

---

## 🧩 Module Structure

- app/         → Main NavHost and entry point.
- auth/        → Firebase Login, Signup, Splash.
- news/        → News feed and search UI (NewsAPI).
- theme/       → Shared Material 3 styles and colors.

---

## ⚙️ Tech Stack

| Layer | Library |
|-------|----------|
| UI | Jetpack Compose, Material3 |
| Architecture | MVVM, ViewModel, MutableState |
| Network | Retrofit, Moshi, OkHttp Interceptors |
| Auth | Firebase Authentication |
| Image Loading | Coil |
| Build Tools | Kotlin DSL, Gradle 8+, AndroidX Compose BOM |

---

## 🔑 API Reference
Using [NewsAPI.org](https://newsapi.org/docs/get-started):

```http
GET /v2/top-headlines
GET /v2/everything
````

You’ll need your own API key from [NewsAPI.org](https://newsapi.org/register)
Add it in your Retrofit provider or as a BuildConfig field.

---

## 🧑‍💻 Author

**Chirag Nikam**
Android Developer | Kotlin • Jetpack Compose • Firebase
📧 *Reach me on LinkedIn or GitHub for collaborations.*

---
