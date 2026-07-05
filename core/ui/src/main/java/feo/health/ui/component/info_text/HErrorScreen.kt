package feo.health.ui.component.info_text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feo.health.ui.component.HButton
import feo.health.ui.component.HText

object HErrorScreen {

    @Composable
    operator fun invoke(
        message: String,
        onRetry: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HText.Default(
                    text = "Произошла ошибка",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                HText.Default(text = message)
                Spacer(modifier = Modifier.height(16.dp))
                HButton.Default(onClick = onRetry) { color ->
                    HText.Default(text = "Повторить", color = color)
                }
            }
        }
    }
}
