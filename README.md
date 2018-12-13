# Android ConstraintSet: Z Translation

Z Translations on views can be done since Api Level 21: [setTranslationZ(float)](https://developer.android.com/reference/android/view/View.html#setTranslationZ(float))


Since the ConstraintSet class is part of the support (now android x) libs, one could assume that the call to [`setTranslationZ`](https://developer.android.com/reference/android/support/constraint/ConstraintSet.html#setTranslationZ(int,%20float)) will do a workaround for you. But that's not the case, the z translation will only be applied for api levels >= 21 :(

Views in the ConstraintLayout container will be drawn in the order they're added. I use this as a workaround to manipulate the z value of a view [programmatically](https://github.com/C0d3GGz/Android-ConstraintSet-z-translation/blob/master/app/src/main/java/example/constraintsetzvaluebug/MainActivity.kt).
