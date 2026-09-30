package com.leoaristocrat.opalinegallery.ui.screens.permission

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leoaristocrat.opalinegallery.ui.theme.CapsuleShape
import com.leoaristocrat.opalinegallery.ui.theme.DesignTokens
import com.leoaristocrat.opalinegallery.ui.theme.GlassDepth
import com.leoaristocrat.opalinegallery.ui.theme.LiquidGlassSurface
import com.leoaristocrat.opalinegallery.ui.theme.LocalDynamicColors
import com.leoaristocrat.opalinegallery.ui.theme.pressScale
import com.leoaristocrat.opalinegallery.ui.theme.rememberHaptic

/** Shown until the user grants photo/video read access. */
@Composable
fun PermissionGate(onRequest: () -> Unit) {
    val accent = LocalDynamicColors.current.accent
    val haptic = rememberHaptic()
    val interaction = remember { MutableInteractionSource() }

    Box(Modifier.fillMaxSize().padding(32.dp), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Icon(
                Icons.Rounded.PhotoLibrary,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = accent,
            )
            Text(
                "Opaline Gallery needs access to your photos and notifications",
                style = MaterialTheme.typography.headlineMedium,
                color = DesignTokens.TextPrimary,
                textAlign = TextAlign.Center,
            )
            Text(
                "Grant photo access to scan your library, and notifications to stay updated on new releases. Everything stays offline.",
                style = MaterialTheme.typography.bodyLarge,
                color = DesignTokens.TextSecondary,
                textAlign = TextAlign.Center,
            )
            LiquidGlassSurface(
                depth = GlassDepth.MID,
                shape = CapsuleShape,
                modifier = Modifier
                    .pressScale(interaction)
                    .clickable(interaction, indication = null) { haptic(); onRequest() },
            ) {
                Text(
                    "Grant access",
                    style = MaterialTheme.typography.labelLarge,
                    color = accent,
                    modifier = Modifier.padding(horizontal = 28.dp, vertical = 14.dp),
                )
            }
        }
    }
}
