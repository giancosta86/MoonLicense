# MoonLicense

*Language-independent Java library for setting/removing license notices*


## Introduction

License management is an important domain of software development, involving several activities - for example, providing a LICENSE file along with a project, or including copyright notices in source files.

It is fairly evident that such activities must *not* become boring chores - therefore it is paramount to make them as simple, automated and transparent as possible.

Inspired by great existing open source solutions (*[License Maven Plugin](http://mojo.codehaus.org/license-maven-plugin/)* and *[License Gradle Plugin](https://github.com/hierynomus/license-gradle-plugin)*), I wanted to create my own tool, tailored to my needs - and so I created **MoonLicense**.


## Supported languages

MoonLicense is a Java library, but its *regex-based engine* supports a wide variety of languages:

* **Java**, **Scala**, **Groovy**, **C**, **C#**, **C++**, **JavaScript** and any other language whose long comments are in /\* ... \*/ format

* **OCaml**, **Pascal** and other languages whose long comments are in (\* ... \*) format

* **HTML**, whose formatter keeps documents compatible with the standard by inserting license notices *after* the **<!DOCTYPE ...>** declaration

* **XML**, whose formatter keeps documents compatible with the standard by inserting license notices *after* the **<?xml...>** declaration

Further languages can be easily supported by creating custom formats.


## Library architecture

MoonLicense is written in Java, originally to be the kernel of [MoonLicense-Gradle](https://github.com/giancosta86/MoonLicense-Gradle) - its plugin for Gradle - but it can be used as a full-fledged, standalone library.

Featuring a small set of types, MoonLicense's model is based on the following concepts:

* **License**: a license, having:
 * a name
 * a reference URL
 * a *full template*, employed, for example, in LICENSE files
 * a *notice template*, usually employed in source file headers
* **NoticeFormat**: describes how to format a licence notice in a source code file and how to identify it so as to replace or remove it later

* **ProductInfo**: product information (e.g.: name, inception year and copyright holder)

* **ProductInfoInjector**: replaces placeholders within templates (for example, **%%%PRODUCT_NAME%%%**) with the related information provided by a ProductInfo
* **StringNoticeService**: sets or removes a license notice within a string - usually the content of a source file - using a given NoticeFormat

* **LicensedFileSet**: a set of files sharing the same license and product info. The files belonging to the set are described via:
 * *includes*: a map of (*regex* -> *NoticeFormat*) entries, each telling which paths will match and the license format to apply
 * *excludes*: a list of regular expressions describing paths to exclude from the set

* **TreeNoticeService**: employs StringNoticeService to set or remove license notices within every file - matching at least a LicensedFileSet in a given sequence - under a given root directory.

More details on the API can be found in the javadocs.


## Regex-based, extensible engine

New languages can be supported just by implementing **NoticeFormat**, especially by extending **DefaultNoticeFormat**.

Such custom subclass can be easily plugged into the engine by specifying it as the LicenseFormat of an *includes* group in a **LicensedFileSet**.

It is also possible to subclass an existing implementation - such as **JavaNoticeFormat** - to slightly customize an existing format.

For further information, please refer to the library's javadocs.

## Special thanks

Special thanks to great tools which I used a lot and which inspired my work:

* [License Maven Plugin](http://mojo.codehaus.org/license-maven-plugin/)
* [License Gradle Plugin](https://github.com/hierynomus/license-gradle-plugin)
