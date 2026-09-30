package com.leoaristocrat.opalinegallery.utils

object VersionUtils {
    fun isNewerVersion(current: String, tag: String): Boolean {
        val currentParts = current.removePrefix("v.").removePrefix("v").removePrefix("V").split(".").map { it.toIntOrNull() ?: 0 }
        val tagParts = tag.removePrefix("v.").removePrefix("v").removePrefix("V").split(".").map { it.toIntOrNull() ?: 0 }
        val maxLength = maxOf(currentParts.size, tagParts.size)
        for (i in 0 until maxLength) {
            val c = currentParts.getOrElse(i) { 0 }
            val t = tagParts.getOrElse(i) { 0 }
            if (t > c) return true
            if (t < c) return false
        }
        return false
    }
}
