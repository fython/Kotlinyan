# Kotlinyan

[![](https://jitpack.io/v/moe.feng/kotlinyan.svg)](https://jitpack.io/#moe.feng/kotlinyan)

> Make Kotlin Android app development easier and more elegant
>
> 让 Kotlin Android 应用开发更加简单、优雅

[**中文文档**](README-CN.md)

## Introduction

Kotlinyan is an Android utilities collection, written in Kotlin. Use Kotlin features to improve Android development performance.

## Import libarry

Kotlinyan Library contains two modules now. There will be more functions and modules in the futrue.

According to your requirement, introduce these modules to your project.

- `library-common` ： Android Common Functions & Extensions
- `library-picasso-support` : [Picasso](https://github.com/square/picasso) Extensions. Provide a easy way to lazy load images into ImageView.
- `library-recyclerview-support` : RecyclerView Extensions. Provide OnLoadMoreListener

First, add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add the dependency (according to your requirement):

```gradle
dependencies {
    compile 'moe.feng.kotlinyan:kotlinyan-common:latest-version'
    compile 'moe.feng.kotlinyan:kotlinyan-picasso-support:latest-version'
}
```

Implement extensions to your classes:

```kotlin
class MainActivity: AppCompatActivity(), AndroidExtensions, ... {
    ...
}
```

## Usage

Read [Github Wiki](https://github.com/fython/Kotlinyan/wiki)

## Discussion

Currently, there is no any discussion group. You can contact me through Telegram [@fython](https://t.me/fython).

Welcome to send pull request or issues。

## License

```
MIT License

Copyright (c) 2017 Fung Go (fython)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
