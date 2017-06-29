# Kotlinyan

[![](https://jitpack.io/v/moe.feng/kotlinyan.svg)](https://jitpack.io/#moe.feng/kotlinyan)

> Make Kotlin Android app development easier and more elegant
>
> 让 Kotlin Android 应用开发更加简单、优雅

[English Documentation (Sorry... It hasn't finished yet)](README-EN.md)

[Wiki/使用文档](https://github.com/fython/Kotlinyan/wiki)

## 简介

日常开发 Android 应用中，有些功能实现我们常常需要写许多代码，整理成一些 Utils 类。

Kotlinyan 是烧饼自己整理的一些比较常用的方法实现（Utils），由 Kotlin 语言编写，利用扩展方法的特性使得在使用 Kotlin 编写 Android 应用时更加高效。

## 模块引入

目前 Kotlinyan 库由三个模块组成，未来会添加更多的模块、功能，可根据自身需求选择引入到自己的开发项目中。

- `kotlinyan-common` ： Android 常用方法扩展
- `kotlinyan-picasso-support` : [Picasso](https://github.com/square/picasso) 扩展，为 ImageView 提供更加简单的 Lazy Load 方法
- `kotlinyan-glide-support` : [Glide](https://github.com/bumptech/glide) 扩展，同上
- `kotlinyan-recyclerview-support` : RecyclerView 扩展，提供了一个 onLoadMore 事件监听

导入时，先在项目根目录下的 `build.gradle` 加入下列代码：
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

再在应用 Module 的 `build.gradle` 中按需声明要依赖的模块：
```gradle
dependencies {
    compile 'moe.feng.kotlinyan:kotlinyan-common:latest-version'
    compile 'moe.feng.kotlinyan:kotlinyan-picasso-support:latest-version'
    compile 'moe.feng.kotlinyan:kotlinyan-glide-support:latest-version'
    compile 'moe.feng.kotlinyan:kotlinyan-recyclerview-support:latest-version'
    compile 'moe.feng.kotlinyan:kotlinyan-appcompat-support:latest-version'
}
```

## 使用方法

可以在 [Github Wiki](https://github.com/fython/Kotlinyan/wiki) 中找到相关 Extensions 的使用方法。

## 讨论

目前不提供讨论交流群，可以直接通过 Telegram [@fython](https://t.me/fython) 联系我。

也欢迎直接提出 Pull Request 或 Issues。

## 开源协议

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
