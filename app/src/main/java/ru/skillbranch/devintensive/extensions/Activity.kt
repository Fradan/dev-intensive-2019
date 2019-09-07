package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager



/**
 * Created by 580305 on 06.09.2019.
 */

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.findViewById<View>(android.R.id.content).windowToken, 0);
}

fun Activity.isKeyboardOpen() : Boolean {

    val rect = Rect()

    val rootView =this.window.decorView.rootView
    rootView.getWindowVisibleDisplayFrame(rect)

    val screenHeight = this.window.decorView.rootView.height

    val keypadHeight = screenHeight - rect.bottom
    return (keypadHeight > screenHeight * 0.15)
}

fun Activity.isKeyboardClosed() : Boolean {
    return !this.isKeyboardOpen()
}