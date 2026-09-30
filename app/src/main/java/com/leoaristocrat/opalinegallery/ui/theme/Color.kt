package com.leoaristocrat.opalinegallery.ui.theme

import androidx.compose.ui.graphics.Color

// Core neutral palette (dark-first, glass relies on dark base).
// Deep blue-black base — rich, not a pure-black hole. Kept in lockstep with DesignTokens.
val OpalineBlack = Color(0xFF000000)        // darkest wash (gradient floor)
val OpalineNight = Color(0xFF000000)        // app background (DesignTokens.BgBase)
val OpalineSurface = Color(0xFF1C1C28)      // card surface (DesignTokens.BgSurface)
val OpalineSurfaceHigh = Color(0xFF242433)  // elevated surface

val OpalineWhite = Color(0xFFE8E8F0)        // primary text (near-white, never grey)
val OpalineMuted = Color(0xFF9999B0)        // secondary text (lavender-grey)
val OpalineFaint = Color(0xFF555570)        // disabled / hairline

// Default accent (overridable from Settings)
val AccentViolet = Color(0xFFB79CFF)
val AccentCyan = Color(0xFF7FE7FF)
val AccentMint = Color(0xFF8FF0C4)
val AccentRose = Color(0xFFFF9CC4)
val AccentAmber = Color(0xFFFFD27F)

val DefaultAccent = Color.White

// Light theme neutrals
val LightBackground = Color(0xFFF3F2FA)
val LightSurface = Color(0xFFFFFFFF)
val LightOnSurface = Color(0xFF14131C)
val LightMuted = Color(0xFF5A5870)

// Glass tint bases
val GlassTintDark = Color(0xFFFFFFFF)   // white tint over dark blur
val GlassTintLight = Color(0xFF0B0A12)  // dark tint over light blur
