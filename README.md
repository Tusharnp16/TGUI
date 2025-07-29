<h1 align="center">
  💠 TGUI – Tony Global University India
</h1>

<p align="center">
  <b>A modern, mobile-first student management system built with Kotlin.</b><br>
  Empowering students and admins with seamless registration, updates, and management.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Kotlin-blueviolet?style=flat-square&logo=kotlin" />
  <img src="https://img.shields.io/badge/Platform-Android-green?style=flat-square&logo=android" />
  <img src="https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square" />
  <img src="https://img.shields.io/badge/UI%20Theme-Cyan-00ffff?style=flat-square" />
</p>

---

## 🚀 Overview

**TGUI (Tony Global University India)** is a sleek **Kotlin-based Android application** that allows:

- 🎓 Students to **register**, **view**, and **update** their information.
- 🧑‍💼 Admins to **manage** all student entries — with options to view, update, and delete.

Built with a beautiful **cyan-themed user interface**, TGUI aims to digitize campus operations and reduce paperwork.

---

## 📱 Features

### 🔹 Student Panel
- ✅ Register yourself with full details
- ✏️ Update personal and academic info
- 👁 View registered information anytime

### 🔸 Admin Panel
- 📃 View all registered students
- 🛠 Edit student records
- ❌ Delete student entries
- 🔎 Filter & search students instantly

---

## 🧑‍💻 Tech Stack

| Layer        | Tech                    |
|--------------|-------------------------|
| Language     | Kotlin (Android Native) |
| Database     | Firebase Realtime DB / SQLite |
| Architecture | MVVM (optional)         |
| UI Design    | XML with Cyan Theme     |
| IDE          | Android Studio          |

---

## 📂 Project Structure

```plaintext
TGUI/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/tgui/
│           │   ├── activities/       # All activity classes (Admin, Student)
│           │   ├── adapters/         # RecyclerView adapters
│           │   ├── models/           # Data models
│           │   └── utils/            # Helper functions
│           ├── res/
│           │   ├── layout/           # XML UI Layouts
│           │   └── values/           # Themes, colors, strings
│           └── AndroidManifest.xml
