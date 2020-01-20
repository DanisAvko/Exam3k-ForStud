package ru.avk.graphics.painting

import ru.smak.graphics.convertation.CartesianScreenPlane
import ru.smak.graphics.convertation.Converter
import ru.smak.graphics.painting.APainter
import java.awt.Color
import java.awt.Graphics
import kotlin.concurrent.thread

class ParamFunctionPainter(
    override val plane: CartesianScreenPlane,
    val x: (Double) -> Double,
    val y: (Double) -> Double,
    val tMin: Double,
    val tMax: Double
) : APainter() {
    override fun paint(g: Graphics?) {
        if (g != null) {
            PaintFucntion(g)
        }
    }

    fun PaintFucntion(g: Graphics) {
        g.color = Color.GREEN
        var t = tMin
        val d = (tMax - tMin) / 1000
        thread {
            while (t <= tMax) {
                val x1 = Converter.xCrt2Scr(x(t), plane)
                val y1 = Converter.yCrt2Scr(y(t), plane)
                val x2 = Converter.xCrt2Scr(x(t + d), plane)
                val y2 = Converter.yCrt2Scr(y(t + d), plane)
                g.drawLine(x1, y1, x2, y2)
                t += d
                Thread.sleep(10)
            }
        }.join()
    }


}