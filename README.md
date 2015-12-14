# Bullet Progress Bar
A bullet progress bar for Android

## Introduction

## Usage

### From Java Code

```xml
<com.shlmlkzdh.bulletprogressbar.BulletProgressBar
    android:id="@+id/bullet_progress_bar"
    android:layout_width="match_parent"
    android:layout_height="40dp" />
```

```java
BulletProgressBar progressBar = (BulletProgressBar) findViewById(R.id.bullet_progress_bar);
progressBar.setLength(6); // The length of the progress bar.
progressBar.setProgress(2); // The progress.
```

### From XML

```xml
<com.shlmlkzdh.bulletprogressbar.BulletProgressBar
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:layout_margin="10dp"
    app:bp_border_width="5dp"
    app:bp_bullet_background_color="@android:color/black"
    app:bp_bullet_color="@android:color/white"
    app:bp_length="5"
    app:bp_progress="2"
    app:bp_shadow_radius="0dp" />
```

## Add BulletProgressBar To Your Project
