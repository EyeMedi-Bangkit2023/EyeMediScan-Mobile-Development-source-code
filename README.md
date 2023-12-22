## EyeMedi
<p align="center">
  <img width="240" src="https://github.com/EyeMedi-Bangkit2023/EyeMedi-Mobile-Development/blob/main/logo.jpg">
</p>
Eyemedi is an application used that can identify diseases based on retinal scans. In addition, users can send feedback about the use of the application which is useful for further application development. For now this application can only identify 4 types, cataract, deabetic retinopathy, glaucoma and normal eyes.

## Application Features
<details>
  <summary>1. Eyes Disease Identification</summary>
  
  * Users can upload image for eye disease classification
  * User gets information about eye diseases including disease details.
</details>
<details>
  <summary>2. Feedback System</summary>
  
  * users can provide feedback for the application.
  * System can displays feedback given by all users
</details>
<details>
  <summary>3. Authentication Service</summary>
  
  * System can validate user credential when login & register to the application
  * System can secure user data
  * System can ensure the integrity of data contained in token cannot be manipulated and can be used to authenticate / authorize two different role / application 
</details>
<details>
  <summary>4. User Service</summary>
  
  * API endpoint to get the history of user profile based on id
  * API endpoint to get the preference of user profile based on id
  * API endpoint to list user information
</details>

## Featured Technologies
* [OkHttp](https://square.github.io/okhttp/) = an open source HTTP client for making network request in Android Apps and enables to interaction with API’s.
* [Live Data](https://developer.android.com/topic/libraries/architecture/livedata?hl=id) = an observable data holder class, it’s used to hold and observe data changes.
* Retrofit  to make API request more straightforward by converting HTTP API endpoints into Kotlin interfaces.
* [Glide](https://github.com/bumptech/glide) - an image loading Library that provides efficient and smooth image loading and caching capabilities, especially useful for displaying images in apps.
* [View Model](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=id) - to store and manage UI related data, it helps separate business logic from UI components.
* [CameraX](https://developer.android.com/training/camerax?hl=id)  - provides a higher-level interface for common camera operations
* [Gson](https://github.com/google/gson) - an library for JSON parsing.
* [Parcelize](https://developer.android.com/kotlin/parcelize?hl=id) - an annotation used in Kotlin to automatically generate code for Parcelable implementation, which allows objects to be serialized for passing between components or processes.
* [Kapt](https://kotlinlang.org/docs/kapt.html) -  to perform annotation processing for Kotlin code programs.
* [Navigation](https://developer.android.com/guide/navigation?hl=id) - a framework that helps manage and simplify the navigation between different screens or destination in an application.
* [Coroutines](https://developer.android.com/kotlin/coroutines?hl=id) - to manage asynchronous tasks without explicitly dealing with callbacks or low-level threading.
* [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=id) - to load and display data, for handling large datasets efficiently in RecyclerViews.

## Steps To Fork Project
In order to duplicate our repository to become your repository on your own github account, you need to **fork** this repository
</br>
![image](https://user-images.githubusercontent.com/85149518/120605441-405eb400-c478-11eb-9304-4dcd1fa61a71.png) 
</br>
Forking repository allows you to modificate this project without affecting the original project.

## Steps To Clone Project
Download code from branch "main" **OR** 
2. Clone Repository. Click on dropdown Code and **copy** HTTPS link
 <br/>
![Image Copy HTTPS](https://camo.githubusercontent.com/1c0cf8056422ff414eee75142b213c5970e085c2e33c0a6d69dc2639d98216f1/68747470733a2f2f6669727374636f6e747269627574696f6e732e6769746875622e696f2f6173736574732f526561646d652f636f70792d746f2d636c6970626f6172642e706e67)
```bash
  git clone https://github.com/EyeMedi-Bangkit2023/EyeMedi-Mobile-Development.git 
```

