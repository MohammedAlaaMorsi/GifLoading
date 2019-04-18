# GifLoading
Gif images loading indicator
[![](https://jitpack.io/v/Mohammed-Alaa/GifLoading.svg)](https://jitpack.io/#Mohammed-Alaa/GifLoading)
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)  
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-GifLoading-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7512)
## Usage

**For a working implementation of this project see the `app/` folder.**

### Step 1

Add this in your root build.gradle at the end of repositories
```groovy
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
     
}
```

### Step 2

Include the library as a local library project or add the dependency in your build.gradle.

```groovy
dependencies {
    implementation 'com.github.Mohammed-Alaa:GifLoading:1.0.2'
}
```	

### Step 3

Add the following xml to your layout file.

```xml
<com.mohammedalaa.gifloading.LoadingView
        android:id="@+id/loading_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:visibility="gone"
        app:message="@string/your_message"
        app:text_size="@dimen/your_text_size"
        app:block_while_loading="true"
        app:srcImg="@drawable/your_gif"
        app:text_color="@color/your_color" />
```

### Step 4

Reference the View in Java code.

```java
       LoadingView loadingView= (LoadingView) findViewById(R.id.loading_view);
```
Show loading
```java
       loadingView.showLoading();
```
Hide loading
```java
       loadingView.hideLoading();
```
  ![](gifloading.gif)
  
  
  ## Credits

Inspired by 

- [Loading.io](https://www.loading.io) for there awesome Gifs
  
  ## License

    Copyright 2018 Mohammmed Alaa
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
  
