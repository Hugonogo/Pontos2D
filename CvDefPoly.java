/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcg;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class CvDefPoly extends Canvas{
    Vector v = new Vector();
    float x0, y0, rWidth = 10.0F, rHeight = 7.5F, pixelSize;
    boolean ready = true;
    int centerX, centerY;
    
    CvDefPoly(){
        
        addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent evt){
            float xA = fx(evt.getX()), yA = fy(evt.getY());
            if(ready){
                v.removeAllElements();
                x0 = xA; y0 = yA;
                ready = false;
            }
            float dx = xA - x0, dy = yA - y0;
            if (v.size() > 0 & dx * dx + dy * dy < 4 * pixelSize * pixelSize) {
               
                ready = true;
            }else{v.addElement(new Point2D(xA, yA));}
            repaint();
        }

        });
       
       
        
    }
     void initgr(){
         Dimension d = getSize();
         int maxX = d.width - 1, maxY = d.height - 1;
         pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
         centerX = maxX/2;
         centerY = maxY/2;
    }
    
    int iX(float x){return Math.round(centerX + x/pixelSize);}
    int iY(float y){return Math.round(centerY - y/pixelSize);}
    float fx(int x){return (x - centerX)* pixelSize;}
    float fy(int y){return (centerY - y)* pixelSize;}
    
    @Override
    public void paint(Graphics g){
        initgr();
        int left = iX(-rWidth/2), right = iX(rWidth/2), bottom = iY(-rHeight/2), top = iY(rHeight/2);
        g.drawRect(left, top, right - left, bottom - top);
        int n  = v.size();
        if (n == 0) {
            return;
        }
        Point2D a = (Point2D)(v.elementAt(0));
        g.drawRect(iX(a.x) - 2, iY(a.y)-2, 4, 4);
        for (int i = 1; i <= n ; i++) {
            if (i == n & !ready) {
                break;
            }
            Point2D b = (Point2D)(v.elementAt(i % n));
            g.drawLine(iX(a.x), iY(a.y), iX(b.x), iY(b.y));
            a = b;
        }
        
    
    }
    
}
    
