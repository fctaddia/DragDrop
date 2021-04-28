<img src="docs/dragdrop_logo.png" alt="Showcase" height="100px">

## DragDrop
Drag and drop with all views

[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.32-e36d0c.svg?style=flat-square)](http://kotlinlang.org)
[![AndroidX](https://img.shields.io/badge/AndroidX-1.3.2-572346.svg?style=flat-square)](https://developer.android.com/jetpack/androidx/)
[![GitHub (pre-)release](https://img.shields.io/github/v/release/fctaddia/dragdrop.svg?color=eb6c46&label=Release&style=flat-square)](./../../releases)
[![License](https://img.shields.io/github/license/fctaddia/DragDrop?color=29a621&label=License)](https://opensource.org/licenses/MIT)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/ddbcb5cee8ed484797236a698d6e7c34)](https://www.codacy.com/gh/fctaddia/DragDrop/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fctaddia/DragDrop&amp;utm_campaign=Badge_Grade)

---

<img src="docs/drag_screen.png" alt="Showcase" height="300px">

### How does it work
Just add the DragDropLayout object in your xml layout.
```xml
<it.kenble.draganddrop.design.drag.DragDropLayout
  android:id="@+id/gridLayout"
  android:layout_marginTop="@dimen/margin10"
  android:layout_width="match_parent"
  android:layout_height="wrap_content" />
```
I recommend not to add the objects inside the layout directly in the xml but I have specifically created a function to dynamically add views to the DragDrpLayout.<br>
Once you have created the xml layout and added the views to the layout with Kotlin, remember to call this function otherwise you will not be able to use drag and drop.
```Kotlin
binding.gridLayout.init()
```
### Conclusions
Perfect now enjoy using drag and drop in your apps.
