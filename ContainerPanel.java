import javax.swing.*;
import java.awt.*;


// ContainerPanel class for CE203 Assignment to use and modify if needed
// Date: 04/11/2023
// Author: F. Doctor

public class ContainerPanel extends JPanel{

    ContainerFrame conFrame;
    JLabel id;


    public ContainerPanel(ContainerFrame cf) {

        conFrame = cf;   // reference to ContainerFrame object
        id=new JLabel("");
        this.add(id);



    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D comp = (Graphics2D)g;   // You will need to use a Graphics2D objects for this
        Dimension size = getSize();        // You will need to use this Dimension object to get
                                           // the width / height of the JPanel in which the
                                           // Polygon is going to be drawn

        // Based on which stored PolygonContainer object you want to be retrieved from the
        // ArrayList and displayed, the object would be accessed and its drawPolygon() method
        // would be called here.

        // modify this to search for IDs to retrieve the

        if (conFrame.polygons_!=null&&!conFrame.polygons_.isEmpty()) {
            for (RegPolygon i : conFrame.polygons_) {
                if (i.pId == conFrame.drawSelection) {
                    conFrame.polyContain1 = i;
                    conFrame.polyContain1.drawPolygon(comp, size);
                    id.setText("ID: "+String.format("%06d", conFrame.polyContain1.pId));
                    break;
                }
            }

        }



        // adding



        //printing list ...

    }
}
