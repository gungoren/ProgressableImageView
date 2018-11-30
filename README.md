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
    android:id="@+id/top"
    android:background="@drawable/bg_img"
    android:layout_height="wrap_content"
    android:layout_width="match_parent"
    android:src="@drawable/dubai"
    android:scaleType="fitXY"
    android:layout_margin="20dp"
    app:dividerColor="@color/divider_color"
    android:layout_below="@id/desc"
    app:progress="0.3"
    app:direction="leftToRight"
    app:dividerWidth="2dp"/>
```

```java
progressableImageView.setProgress(progress); //use this if you want to progress
progressableImageView.setDividerWidthAsDp(dp); //use this if you want to change divider width. When it set to zero it will be invisible
progressableImageView.setDividerWidthAsPx(px); //use this if you want to change divider width. When it set to zero it will be invisible
progressableImageView.setDividerColor(color); //use this for set a new color divider color.
progressableImageView.setDirection(ProgressDirection.bottom_to_top); // use this if you wnat to change direction of progress available values [left_to_right, left_to_right, right_to_left, top_to_bottom, bottom_to_top]
```

## Attribute
```app:progress``` value is between 0 and 1 float value. 1 = fullly bright. 0 = fully gray.
```app:dividerColor``` value is color which type is color.
```app:dividerWidth``` value is divider width which type is dimen.
```app:direction``` value is for progress direction which type is enum and values [leftToRight,rightToLeft,topToBottom,bottomToTop].

```gradle
maven { url 'https://jitpack.io' }
```

```gradle
dependencies {
  compile 'com.github.gungoren:ProgressableImageView:1.0.1'
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
