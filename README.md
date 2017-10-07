# Kotlinyan

[![](https://jitpack.io/v/moe.feng/kotlinyan.svg)](https://jitpack.io/#moe.feng/kotlinyan)

> Make Kotlin Android app development easier and more elegant
>
> 让 Kotlin Android 应用开发更加简单、优雅

[**中文文档**](README-CN.md) [Wiki/API Documentation](https://github.com/fython/Kotlinyan/wiki)

## Introduction

Kotlinyan is an Android utilities collection, written in Kotlin. Use Kotlin features to improve Android development performance.

## Import libarry

Kotlinyan Library contains some modules which have different types of functions.

According to your requirement, introduce these modules to your project.

- `kotlinyan-common` ： Android Common Functions & Extensions
- `kotlinyan-picasso-support` : [Picasso](https://github.com/square/picasso) Extensions. Provide a easy way to lazy load images into ImageView.
- `kotlinyan-glide-support` : [Glide](https://github.com/bumptech/glide) Extensions.
- `kotlinyan-recyclerview-support` : RecyclerView Extensions. Provide OnLoadMoreListener

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
    compile 'moe.feng.kotlinyan:kotlinyan-glide-support:latest-version'
    compile 'moe.feng.kotlinyan:kotlinyan-recyclerview-support:latest-version'
}
```

Implement extension methods to your classes:

```kotlin
import moe.feng.kotlinyan.common.*
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
