# test
## Overview

This is a simple Android application that fetches data from a remote server using Retrofit, stores the data in a local Room database, and displays it in a RecyclerView.

## Architecture

The architecture in this application is MVVM, data is fetched through APIs using Retrofit and is stored locally in a sqlite database

## Libraries/Frameworks Used

Android Architecture Components: Used for implementing MVVM architecture with ViewModel and LiveData.
Retrofit: Used for making API calls.
Room: Used for local database storage.
Glide: Used for loading images efficiently from URLs.
## Features

Fetches data from a remote server using Retrofit.
Stores data locally using Room database.
Implements a ViewModel to manage UI-related data.
Displays a grid style list of items in a RecyclerView.
Implements navigation to a detail screen.