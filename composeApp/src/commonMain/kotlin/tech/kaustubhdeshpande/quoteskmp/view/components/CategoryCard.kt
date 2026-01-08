package tech.kaustubhdeshpande.quoteskmp.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    categoryColor: Color,
    category: String,
    categoryIcon: ImageVector
) {
    Column(
        modifier = modifier
            .width(100.dp)
            .height(120.dp)
            .drawBehind {
                val cornerRadius = 16.dp.toPx()
                drawRoundRect(
                    color = categoryColor.copy(alpha = 0.1f),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = categoryIcon,
            contentDescription = "ic_favorite",
            tint = categoryColor,
            modifier = modifier
                .size(24.dp)
                .drawBehind {
                    drawCircle(
                        color = categoryColor.copy(alpha = 0.3f),
                        radius = 24.dp.toPx(),
                        center = Offset(size.width / 2, size.height / 2)
                    )
                }
        )
        Spacer(modifier = modifier.height(24.dp))
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}