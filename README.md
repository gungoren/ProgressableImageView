# ProgressableImageView

Motivated by layout in [Words Of Wonders](https://play.google.com/store/apps/details?id=com.fugo.wow) app.

## Demo
<img src="https://github.com/gungoren/ProgressableImageView/blob/master/art/progress.gif"/>

## Divider Width Demo
<img src="https://github.com/gungoren/ProgressableImageView/blob/master/art/divider_width.gif"/>

## Divider Color Demo
<img src="https://github.com/gungoren/ProgressableImageView/blob/master/art/divider_color.gif"/>

## Usage
```xml
<com.gungoren.view.ProgressableImageView
    android:id="@+id/progressableImageView"
    android:layout_height="wrap_content"
    android:layout_width="match_parent"
    android:src="@drawable/pinguinus"
    android:scaleType="fitXY"
    android:layout_margin="20dp"
    app:dividerColor="@color/colorAccent"
    app:progress="0.3"
    app:dividerWidth="2dp"/>
```

```java
progressableImageView.setProgress(progress); //use this if you want to progress
progressableImageView.setDividerWidthAsDp(dp); //use this if you want to change divider width. When it set to zero it will be invisible
progressableImageView.setDividerWidthAsPx(px); //use this if you want to change divider width. When it set to zero it will be invisible
progressableImageView.setDividerColor(color); //use this for set a new color divider color.
```

## Attribute
```app:progress``` value is between 0 and 1 float value. 1 = fullly bright. 0 = fully gray.
```app:dividerColor``` value is color which type is color.
```app:dividerWidth``` value is divider width which type is dimen.


```gradle
maven { url 'https://jitpack.io' }
```

```gradle
dependencies {
  compile 'com.github.gungoren:ProgressableImageView:1.0.0'
}
```
License
--------


    Copyright 2018 Mehmet Gungoren.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
