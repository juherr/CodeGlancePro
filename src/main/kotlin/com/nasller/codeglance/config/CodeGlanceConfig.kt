package com.nasller.codeglance.config

import com.intellij.openapi.application.ApplicationManager
import com.intellij.util.messages.Topic
import kotlin.properties.Delegates

class CodeGlanceConfig {
    var pixelsPerLine = 4
    var maxLinesCount = 100000
    var moreThanLineDelay = 3000
    var disabled by Delegates.observable(false) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            SettingsChangePublisher.onRefreshChanged()
        }
    }
    var singleFileVisibleButton = true
    var hideOriginalScrollBar = false
    var isRightAligned = true
    var hoveringToShowScrollBar by Delegates.observable(false) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            SettingsChangePublisher.onHoveringOriginalScrollBarChanged(newValue)
        }
    }
    var jumpOnMouseDown = true
    var width = 110
    var viewportColor = "A0A0A0"
    var clean = true
    var locked = false
}

val SettingsChangePublisher = ApplicationManager.getApplication().messageBus.syncPublisher(SettingsChangeListener.TOPIC)

interface SettingsChangeListener {

    fun onRefreshChanged() {}

    fun onHoveringOriginalScrollBarChanged(value:Boolean) {}

    fun onGlobalChanged() {}

    companion object {
        val TOPIC = Topic.create("CodeGlanceSettingsChanged", SettingsChangeListener::class.java)
    }
}