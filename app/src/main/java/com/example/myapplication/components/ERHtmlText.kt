package com.example.myapplication.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.google.android.material.textview.MaterialTextView

@Composable
fun ERHtmlText(textHTML: String, MaxLines: Int? = null) {
    val text = HtmlCompat.fromHtml(textHTML, 0)
    // getting onSurface text color
    val textColor = MaterialTheme.colorScheme.onSurface.toArgb()

    // Using MaterialTextView to display HTML text, https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose?hl=pt-br
    AndroidView(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
        factory = {
            MaterialTextView(it).apply {
                setTextColor(textColor)
                MaxLines.let {
                    if (it != null) {
                        maxLines = it
                    }
                }
            }
        },
        update = { it ->
            it.text = text
        }
    )
}