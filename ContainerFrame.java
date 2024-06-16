import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayList;



public class ContainerFrame extends JFrame{

    JButton drawPolygon1;    // Example Button to select Polygon 1 to draw
    JButton add_;
    JButton display;
    JButton search;
    JButton sort;
    JTextField reg;
    JTextField number_sides;
    JTextField starting_angle;
    JTextField radius;
    JTextField R;
    JTextField G;
    JTextField B;
    JButton submit;
    JButton submit2;
    JButton cancel;
    JButton cancel2;
    JPanel textfield_;
    JPanel Textfield_search;
    JPanel buttPanel;
    ContainerPanel drawPanel;
    JPanel result_;
    JLabel result;

    JButton result_close=new JButton();


    ContainerButtonHandler cbHandler;

    ArrayList<RegPolygon>polygons_=new ArrayList<>();


    int drawSelection = 0;   // Selection variable to determine which Polygon is selected to be drawn

    // Here I have provided an example of one PolygonContainer objects where the number of sides
    // and side length of the polygon are hardcoded.
    // These should be input from the application text fields where the user would type them in.
    // This would create new PolygonContainer objects that would be stored in and accessed from
    // an ArrayList data structure rather than be explicitly defined as below

    RegPolygon polyContain1;

    public void createComponents() {

        cbHandler = new ContainerButtonHandler(this);
//        drawPolygon1 = new JButton("DrawPoly");
//        drawPolygon1.addActionListener(cbHandler);
        drawPanel = new ContainerPanel(this);

        result_=new JPanel();
        result_.setLayout(new BoxLayout(result_,BoxLayout.Y_AXIS));
        result=new JLabel("");
        result_.add(result);

        add(result_,BorderLayout.EAST);

        buttons();
        add(drawPanel, BorderLayout.CENTER);



        setSize(1200, 400);
        setVisible(true);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	// Close action.
    }

    public void buttons(){ // JPanel includes the buttons
        buttPanel = new JPanel();
        add_=new JButton("Add");
        add_.addActionListener(cbHandler);
        display=new JButton("Display");
        display.addActionListener(cbHandler);
        search=new JButton("Search");
        search.addActionListener(cbHandler);
        sort=new JButton("Sort");
        sort.addActionListener(cbHandler);

        buttPanel.add(add_);
        buttPanel.add(display);
        buttPanel.add(search);
        buttPanel.add(sort);

        this.add(buttPanel, BorderLayout.SOUTH);
        this.revalidate();
    }

    public void textfields(){ // If user presses Add button, this panel shows.

        this.remove(buttPanel);
        textfield_=new JPanel();
        JLabel Reg_number=new JLabel("Registration Number:");
        reg=new JTextField(6);
        JLabel num_sides=new JLabel("Number Of Sides:");
        number_sides=new JTextField(3);
        JLabel starting_angle_=new JLabel("Starting Angle:");
        starting_angle=new JTextField(3);
        JLabel radius_=new JLabel("Radius:");
        radius=new JTextField(5);
        JLabel colors=new JLabel("Colors(RGB):");
        JLabel R_=new JLabel("R:");
        R=new JTextField(3);
        JLabel G_=new JLabel("G:");
        G=new JTextField(3);
        JLabel B_=new JLabel("B:");
        B=new JTextField(3);
        submit=new JButton("Submit");
        submit.addActionListener(cbHandler);
        cancel=new JButton("Cancel");
        cancel.addActionListener(cbHandler);

        textfield_.add(Reg_number);
        textfield_.add(reg);
        textfield_.add(num_sides);
        textfield_.add(number_sides);
        textfield_.add(starting_angle_);
        textfield_.add(starting_angle);
        textfield_.add(radius_);
        textfield_.add(radius);
        textfield_.add(colors);
        textfield_.add(R_);
        textfield_.add(R);
        textfield_.add(G_);
        textfield_.add(G);
        textfield_.add(B_);
        textfield_.add(B);
        textfield_.add(submit);
        textfield_.add(cancel);
        this.add(textfield_,BorderLayout.NORTH);
        this.revalidate();
    }



    public void textfield_search_(){ // If user presses search, this panel shows
        this.remove(buttPanel);
        Textfield_search=new JPanel();
        JLabel Reg_number=new JLabel("Registration Number:");
        reg=new JTextField(6);
        submit2=new JButton("Submit");
        submit2.addActionListener(cbHandler);
        cancel2=new JButton("Cancel");
        cancel2.addActionListener(cbHandler);
        Textfield_search.add(Reg_number);
        Textfield_search.add(reg);
        Textfield_search.add(submit2);
        Textfield_search.add(cancel2);
        this.add(Textfield_search,BorderLayout.NORTH);
        this.revalidate();
    }

    public void cancel2_(){ // When user presses cancel when searching polygon. switch from search to main menu
        this.remove(Textfield_search);
        buttons();
    }

    public void submit2_(){ // search function of the program
        if (reg.getText().isEmpty()||reg.getText().length()!=6){
            JOptionPane.showMessageDialog(null,"Invalid reg number!");
            return;
        }

        try{
            Integer.parseInt(reg.getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid reg number!");
            return;
        }

        if (!find_reg(Integer.parseInt(reg.getText()))){
            JOptionPane.showMessageDialog(null,"Reg number not found!");
            return;
        }

        this.drawSelection=Integer.parseInt(reg.getText());
        this.remove(Textfield_search);
        this.buttons();
        this.revalidate();


    }

    boolean find_reg(int reg){ // check if the reg number exist
        for (RegPolygon i:polygons_){
            if (i.pId==reg){

                return true;
            }
        }
        return false;
    }

    public void setResult_close(){ // close (hide) the result of Display and Sort
        result.setText("");
        result_.remove(result_close);
        this.revalidate();
    }


    public void cancel_(){ //When user presses cancel when adding polygons.
        remove(textfield_);
        buttons();
    }
    public void submit_(){ //When user presses submit when adding a new polygon
        if (!check_input()){
            return;
        }

        RegPolygon temp=new RegPolygon(Integer.parseInt(reg.getText()),Integer.parseInt(number_sides.getText()),Double.parseDouble(starting_angle.getText()),Double.parseDouble(radius.getText()),Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText()));
        polygons_.add(temp);

        this.drawSelection=temp.pId;
        this.remove(textfield_);
        this.buttons();
        if (result.getText() != null && !result.getText().isEmpty()){
            display_();
        }
        this.revalidate();
        JOptionPane.showMessageDialog(null,"Polygon successfully added!");

    }

    public void sort_(){ // When user press sort

        System.out.println("Below are all polygons in ascending order by their ID: ");

        polygons_.sort(null);
        String temp=new String("<html>Below are all polygons in ascending order by their ID: <br><br>");
        for (RegPolygon i:polygons_){
            System.out.println(i.toString());
            temp+=(i.toString());
            temp+="<br>";
        }

        temp+="</html>";

        if (result_.isAncestorOf(result_close)) {
            result.setText(temp);
            this.revalidate();
        } else {
            result.setText(temp);
            result_close=new JButton("Close");
            result_close.addActionListener(cbHandler);
            result_.add(result_close);

            this.revalidate();
        }



    }

    public void display_(){ // When user presses display

        System.out.println("Below are all polygons: ");

        String temp=new String("<html>Below are all polygons: <br><br>");
        for (RegPolygon i:polygons_){
            System.out.println(i.toString());
            temp+=(i.toString());
            temp+="<br>";
        }

        temp+="</html>";

        if (result_.isAncestorOf(result_close)) {
            result.setText(temp);
            this.revalidate();
        } else {
            result.setText(temp);
            result_close=new JButton("Close");
            result_close.addActionListener(cbHandler);
            result_.add(result_close);
            this.revalidate();
        }
    }

    public boolean check_input(){ //Checks the user input when adding a new polygon

        if (reg.getText().isEmpty()||number_sides.getText().isEmpty()||starting_angle.getText().isEmpty()||radius.getText().isEmpty()||R.getText().isEmpty()||G.getText().isEmpty()||B.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"You have empty input!");
            return false;
        }

        try{
            Integer.parseInt(reg.getText());

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid registration number!");
            return false;
        }

        try{
            Integer.parseInt(number_sides.getText());

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid number of sides!");
            return false;
        }

        try{
            Integer.parseInt(number_sides.getText());

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid number of sides!");
            return false;
        }

        try{
            Double.parseDouble(starting_angle.getText());

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid starting angle!");
            return false;
        }

        try{
            Double.parseDouble(radius.getText());

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid radius!");
            return false;
        }
        try{
            Double.parseDouble(starting_angle.getText());

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid starting angle!");
            return false;
        }

        try{
            Integer.parseInt(R.getText());

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid R value!");
            return false;
        }

        try{
           Integer.parseInt(G.getText());

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid G value!");
            return false;
        }
        try{
           Integer.parseInt(B.getText());

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid B value!");
            return false;
        }

        if (reg.getText().length()!=6){
            JOptionPane.showMessageDialog(null,"Invalid registration number length!");
            return false;
        }

        if (polygons_!=null&&!polygons_.isEmpty()){
            for (RegPolygon i:polygons_){
                if (i.pId==Integer.parseInt(reg.getText())){
                    JOptionPane.showMessageDialog(null,"Registration number occupied!");
                    return false;
                }
            }
        }


        int regnum=Integer.parseInt(reg.getText());
        int sides=Integer.parseInt(number_sides.getText());
        double radius_value=Double.parseDouble(radius.getText());
        double r_value=Integer.parseInt(R.getText());
        double g_value=Integer.parseInt(G.getText());
        double b_value=Integer.parseInt(B.getText());

//        if (regnum<=0||sides<3||radius_value<=0||r_value<0||g_value<0||b_value<0||r_value>255||g_value>255||b_value>255){
//            JOptionPane.showMessageDialog(null,"Invalid input!");
//            return false;
//        }

        if (regnum<=0){
            JOptionPane.showMessageDialog(null,"Invalid reg number!");
            return false;
        }

        if (sides<3){
            JOptionPane.showMessageDialog(null,"Invalid sides!");
            return false;
        }

        if (radius_value<=0){
            JOptionPane.showMessageDialog(null,"Invalid radius!");
            return false;
        }

        if (r_value<0||g_value<0||b_value<0||r_value>255||g_value>255||b_value>255){
            JOptionPane.showMessageDialog(null,"Invalid RGB value!");
            return false;
        }



        return true;
    }



    public static void main(String[] args) {

        ContainerFrame cFrame = new ContainerFrame();
        cFrame.createComponents();
    }

}
