package ru.avk.graphics.painting

import ru.smak.graphics.convertation.CartesianScreenPlane
import ru.smak.graphics.convertation.Converter
import ru.smak.graphics.painting.APainter
import java.awt.Color
import java.awt.Graphics

class FunctionPainter (override val plane: CartesianScreenPlane,val f:(Double)->Double):APainter(){
    override fun paint(g: Graphics?) {
        if (g!=null){
            PaintFucntion(g)
        }
    }
    fun PaintFucntion(g:Graphics){
        for (i in 0..plane.realWidth) {
            g.color = Color.black
            val y1 = Converter.yCrt2Scr(f(Converter.xScr2Crt(i, plane)),plane)
            val y2 = Converter.yCrt2Scr(f(Converter.xScr2Crt(i+1, plane)),plane)
            g.drawLine(i,y1,i+1,y2)
        }
    }


}