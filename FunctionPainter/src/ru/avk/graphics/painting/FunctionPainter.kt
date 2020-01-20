package ru.avk.graphics.painting

import ru.smak.graphics.convertation.CartesianScreenPlane
import ru.smak.graphics.convertation.Converter
import ru.smak.graphics.painting.APainter
import java.awt.Color
import java.awt.Graphics
import kotlin.concurrent.thread

class FunctionPainter(
    override val plane: CartesianScreenPlane,
    val x: ((Double) -> Double)?=null,
    val y: (Double) -> Double
) : APainter() {

    override fun paint(g: Graphics?) {
        if (g != null) {
            PaintFucntion(g)
        }
    }

    fun PaintFucntion(g: Graphics) {
        g.color = Color.GREEN
        if (x==null){
            thread {
                for (i in 0..plane.realWidth) {
                    g.color = Color.RED
                    val y1 = Converter.yCrt2Scr(y(Converter.xScr2Crt(i, plane)), plane)
                    val y2 = Converter.yCrt2Scr(y(Converter.xScr2Crt(i + 1, plane)), plane)
                    g.drawLine(i, y1, i + 1, y2)
                    Thread.sleep(10)
                }
            }.join()
        }else{
            var t =0.0;
            val d = 2*Math.PI / 1000.0
            thread {
                while (t <= 2*Math.PI) {
                        val x1 = Converter.xCrt2Scr(x.invoke(t), plane)
                        val y1 = Converter.yCrt2Scr(y(t), plane)
                        val x2 = Converter.xCrt2Scr(x.invoke(t + d), plane)
                        val y2 = Converter.yCrt2Scr(y(t + d), plane)
                        g.drawLine(x1, y1, x2, y2)
                        t += d
                        Thread.sleep(10)
                }
            }.join()
        }

    }

}