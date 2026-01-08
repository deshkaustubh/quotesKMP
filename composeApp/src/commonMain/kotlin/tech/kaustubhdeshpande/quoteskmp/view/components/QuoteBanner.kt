package tech.kaustubhdeshpande.quoteskmp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuoteBanner(modifier: Modifier = Modifier) {
    val circlesColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(164.dp)
            .background(color = Color(0xFF6366f1), shape = RoundedCornerShape(8.dp))
            .drawBehind {
                val circleColor = circlesColor
                val radiusX = size.width / 2f
                val radiusY = size.height / 2f

                drawCircle(
                    color = circleColor,
                    radius = radiusX * 0.35f,
                    center = center.copy(
                        x = center.x - (radiusX - 72),
                        y = center.y - (radiusY - 72)
                    )
                )
                drawCircle(
                    color = circleColor,
                    radius = radiusX * 0.35f,
                    center = center.copy(
                        x = center.x + (radiusX - 64),
                        y = center.y + (radiusY - 64)
                    )
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .wrapContentHeight(),
                text = "I am",
                textAlign = TextAlign.Center,
                softWrap = true,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 32.sp,
                    color = Color.White
                ),
                letterSpacing = 4.sp
            )
            Text(
                modifier = Modifier
                    .wrapContentHeight(),
                text = "Iron Man",
                textAlign = TextAlign.Center,
                softWrap = true,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 32.sp,
                    color = Color.White
                ),
                letterSpacing = 4.sp
            )
        }
    }
}