package ru.avk.graphics.painting

import ru.smak.graphics.convertation.CartesianScreenPlane
import ru.smak.graphics.convertation.Converter
import ru.smak.graphics.painting.APainter
import java.awt.Color
import java.awt.Graphics

class ParamFunctionPainter (override val plane: CartesianScreenPlane,val x:(Double)->Double,val y:(Double)->Double):APainter(){
    override fun paint(g: Graphics?) {
        if (g!=null){
            g.color = Color.black
            PaintFucntion(g)
        }
    }
    fun PaintFucntion(g:Graphics){
        for (i in 0..plane.realWidth) {
            val y1 = Converter.yCrt2Scr(x(Converter.xScr2Crt(i, plane)),plane)
            val y2 = Converter.yCrt2Scr(y(Converter.xScr2Crt(i+1, plane)),plane)
            g.drawLine(i,y1,i+1,y2)
        }
    }


}