@file:OptIn(ExperimentalTextApi::class)

package com.vkornee.weatherapp.weather.presentation.view

import android.graphics.PointF
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkornee.weatherapp.weather.domain.model.TempUnit
import com.vkornee.weatherapp.weather.presentation.model.WeekForecastGraphData
import com.vkornee.weatherapp.weather.presentation.model.WeekForecastGraphEntry
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.absoluteValue
import kotlin.time.DurationUnit
import kotlin.time.toDuration


@Composable
fun WeatherGraph(
    modifier: Modifier = Modifier,
    data: WeekForecastGraphData
) {

    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier
            .fillMaxSize()
    ) {

        val points = data.entries
        val tempStep = data.tempStep
        val maxTemp = data.entries.maxBy { it.temp }.temp
        val minTemp = data.entries.minBy { it.temp }.temp
        val maxScale = maxTemp.div(tempStep).times(tempStep) + tempStep
        val minScale = minTemp.div(tempStep).times(tempStep) - tempStep

        val yAxisValues = (minScale..maxScale step tempStep).toImmutableList()
        val xAxisValues = data.entries.map { it.date }

        val axisInset = 25.dp.toPx()

        val yAxisSpace = size.height / yAxisValues.size
        val xAxisSpace = size.width / xAxisValues.size //todo reduce to show only days
        drawAxis(xAxisValues, yAxisValues, xAxisSpace, yAxisSpace, axisInset, textMeasurer)
        withTransform({
            inset(
                axisInset,
                (maxScale - maxTemp).toFloat().div(tempStep).times(yAxisSpace),
                0f,
                yAxisSpace + (minScale - minTemp).absoluteValue.toFloat().div(tempStep).times(yAxisSpace)
            )
        }) {
            drawGraph(points)
            drawToday()
        }
    }

}

@OptIn(ExperimentalTextApi::class)
private fun DrawScope.drawAxis(
    xAxisValues: List<Instant>,
    yAxisValues: ImmutableList<Int>,
    xAxisSpace: Float,
    yAxisSpace: Float,
    padding: Float,
    textMeasurer: TextMeasurer
) {
    for (x in xAxisValues.indices) {
        val measureResult = textMeasurer.measure(xAxisValues[x]
            .toLocalDateTime(TimeZone.of(ZoneId.systemDefault().id)) //todo timezone
            .dayOfWeek
            .getDisplayName(TextStyle.SHORT, Locale.US)
        )
        drawText(
            textLayoutResult = measureResult,
            topLeft = Offset(
                padding+ xAxisSpace * x - measureResult.size.width / 2,
                size.height - measureResult.size.height
            ),
        )

//        drawLine(
//            color = Color.Gray,
//            start = Offset(
//                padding + xAxisSpace * x,
//                size.height - measureResult.size.height
//            ),
//            end = Offset(padding + xAxisSpace * x, 0f)
//        )
    }

    for (y in yAxisValues.indices) {
        val measureResult = textMeasurer.measure("${yAxisValues[y]}")
        val offset = Offset(0f, size.height - yAxisSpace * (y + 1))
        drawText(
            textLayoutResult = measureResult,
            topLeft = offset.copy(y = offset.y - (measureResult.size.height.div(2f)))
        )

        drawLine(
            color = Color.Gray,
            start = offset.copy(x = padding),
            end = offset.copy(x = size.width)
        )
    }
}

private fun DrawScope.drawGraph(points: List<WeekForecastGraphEntry>) {

    val maxX = points.maxBy { it.date.epochSeconds }.date.epochSeconds
    val minX = points.minBy { it.date.epochSeconds }.date.epochSeconds
    val maxY = points.maxBy { it.temp }.temp
    val minY = points.minBy { it.temp }.temp
    val coordinates = points.map {
        PointF(
            (it.date.epochSeconds - minX).toFloat() / (maxX - minX),
            1 - (it.temp - minY).toFloat() / (maxY - minY)
        )
    }.map {
        PointF(
            it.x * size.width,
            it.y * size.height
        )
    }


    val coordinatesWithControlPoints = coordinates.zipWithNext()
        .map { (prev, current) ->
            val controlPoint1 = PointF(
                (current.x + prev.x) / 2,
                prev.y
            )
            val controlPoint2 = PointF(
                (current.x + prev.x) / 2,
                current.y
            )
            Triple(
                controlPoint1,
                controlPoint2,
                current
            )
        }

    val stroke = Path().apply {
        moveTo(coordinates.first().x, coordinates.first().y)
        coordinatesWithControlPoints.forEach { (control1, control2, coordinates) ->
            cubicTo(
                control1.x, control1.y,
                control2.x, control2.y,
                coordinates.x, coordinates.y
            )
        }
    }


//    /** filling the area under the path */
//    val fillPath = android.graphics.Path(stroke.asAndroidPath())
//        .asComposePath()
//        .apply {
//            lineTo(xAxisSpace * xValues.last(), size.height - yAxisSpace)
//            lineTo(xAxisSpace, size.height - yAxisSpace)
//            close()
//        }
//    drawPath(
//        fillPath,
//        brush = Brush.verticalGradient(
//            listOf(
//                Color.Cyan,
//                Color.Transparent,
//            ),
//            endY = size.height - yAxisSpace
//        ),
//    )
    drawPath(
        stroke,
        color = Color.Black,
        style = Stroke(
            width = 5f,
            cap = StrokeCap.Round
        )
    )

    coordinates.forEach {
        drawCircle(Color.Red, 3f, Offset(it.x, it.y))
    }
}

private fun DrawScope.drawToday() {

}

@Composable
@Preview
fun WeatherGraphPreview() {

    val data = WeekForecastGraphData(
        entries = listOf(-17, -5, 0, -3, 5, 9, 15).mapIndexed { i, v ->
            WeekForecastGraphEntry(
                date = Clock.System.now().plus(i.toDuration(DurationUnit.DAYS)),
                temp = v,
                isNow = false
            )
        }.toImmutableList()
    )
    WeatherGraph(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        data = data
    )
}
