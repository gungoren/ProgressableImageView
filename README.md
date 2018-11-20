# ProgressableImageView
Scale your layout on user interaction. [Live Demo](https://www.youtube.com/watch?v=wA41H0UMoHQ)

Motivated by layout in [Blinkist](https://play.google.com/store/apps/details?id=com.blinkslabs.blinkist.android) app and search bar in [Spotify](https://play.google.com/store/apps/details?id=com.spotify.music) app.

<img src="https://raw.githubusercontent.com/iammert/ScalingLayout/master/art/cover_scaling.png"/>

## Demo
<img src="https://github.com/iammert/ScalingLayout/blob/master/art/gif_behavior.gif"/>

## Fab Demo
<img src="https://github.com/iammert/ScalingLayout/blob/master/art/gif_fab.gif"/>

## Spotify Search Demo
<img src="https://github.com/iammert/ScalingLayout/blob/master/art/gif_searchbar.gif"/>

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


## Download
<a href='https://ko-fi.com/P5P872LP' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi1.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>


```gradle
maven { url 'https://jitpack.io' }
```

```gradle
dependencies {
  compile 'com.github.iammert:ScalingLayout:1.2.1'
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
