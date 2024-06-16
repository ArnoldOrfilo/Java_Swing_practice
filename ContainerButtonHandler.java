import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ContainerButtonHandler class for CE203 Assignment to use and modify if needed
// Date: 11/11/2022
// Author: F. Doctor

class ContainerButtonHandler implements ActionListener {
    ContainerFrame theApp;   // Reference to ContainerFrame object

    // ButtonHandler constructor
    ContainerButtonHandler(ContainerFrame app ) {
        theApp = app;
    }


    // The action performed method would determine what text input or button press events
    // you might have a single event handler instance where the action performed method determines
    // the source of the event, or you might have separate event handler instances.
    // You might have separate event handler classes for managing text input retrieval and button
    // press events.
    public void actionPerformed(ActionEvent e) {
        if( e.getSource()==theApp.drawPolygon1) {
            theApp.drawSelection = 1;
        }

        else if (e.getSource()==theApp.add_){
            theApp.textfields();

        }
        else if (e.getSource()==theApp.submit){
            theApp.submit_();
        }

        else if (e.getSource()==theApp.cancel){
            theApp.cancel_();
        }
        else if (e.getSource()==theApp.search){
            theApp.textfield_search_();
        }
        else if (e.getSource()==theApp.cancel2){
            theApp.cancel2_();
        }
        else if (e.getSource()==theApp.submit2){
            theApp.submit2_();
        }
        else if (e.getSource()==theApp.display){
            theApp.display_();
        }
        else if (e.getSource()==theApp.sort){
            theApp.sort_();
        }
        else if (e.getSource()==theApp.result_close){
            theApp.setResult_close();
        }

        theApp.repaint();
    }
}

