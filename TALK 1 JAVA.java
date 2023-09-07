import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Scanner;
class Record {
    private String name;
    private int idNumber;
    private int contactNumber;
    public Record() {}
    public Record(String name, int idNumber,
                  int contactNumber)
    {
        this.name = name;
        this.idNumber = idNumber;
        this.contactNumber = contactNumber;
    }
    public int getContactNumber() { return contactNumber; }
    public void setContactNumber(int contactNumber)
    {

        this.contactNumber = contactNumber;
    }
    public int getIdNumber() { return idNumber; }
    public void setIdNumber(int idNumber)
    {
        this.idNumber = idNumber;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    @Override public String toString()
    {
        return "Records{"
                + "name=" + name + ", idNumber=" + idNumber
                + ", contactNumber=" + contactNumber + '}';
    }
}
class StudentRecordManagement {
    LinkedList<Record> list;
    public StudentRecordManagement()
    {
        list = new LinkedList<>();
    }
    public void add(Record record)
    {
        if (!find(record.getIdNumber())) {
            list.add(record);
        }
        else {
            System.out.println(
                    "Record already exists in the Record list");
        }
    }
    public boolean find(int idNimber)
    {
        for (Record l : list) {
            if (l.getIdNumber() == idNimber) {
                System.out.println(l);
                return true;
            }
        }
        return false;
    }
    public void delete(int recIdNumber)
    {
        Record recordDel = null;
        for (Record ll : list) {
            if (ll.getIdNumber() == recIdNumber) {
                recordDel = ll;
            }
        }
        if (recordDel == null) {
            System.out.println("Invalid record Id");
        }
        else {
            list.remove(recordDel);
            System.out.println(
                    "Successfully removed record from the list");
        }
    }
    public Record findRecord(int idNumber)
    {
        for (Record l : list) {
            if (l.getIdNumber() == idNumber) {
                return l;
            }
        }
        return null;
    }
    public void update(int id, Scanner input)
    {
        if (find(id)) {
            Record rec = findRecord(id);
            System.out.print(
                    "What is the new Student id Number ? ");
            int idNumber = input.nextInt();
            System.out.print(
                    "What is the new Student contact Number ");
            int contactNumber = input.nextInt();
            input.nextLine();
            System.out.print(
                    "What is the new Student Name ? ");
            String name = input.nextLine();

            rec.setIdNumber(idNumber);
            rec.setName(name);
            rec.setContactNumber(contactNumber);
            System.out.println(
                    "Record Updated Successfully");
        }
        else {
            System.out.println(
                    "Record Not Found in the Student list");
        }
    }
    public void display()
    {
        if (list.isEmpty()) {
            System.out.println("The list has no records\n");
        }
        for (Record record : list) {
            System.out.println(record.toString());
        }
    }
}
class Main extends JFrame implements ActionListener
{
    JLabel l;
    JRadioButton jb[]=new JRadioButton[5];
    JButton b1,b2;
    ButtonGroup bg;
    int count=0,current=0,x=1,y=1,now=0;
    int m[]=new int[10];
    Main(String s)
    {
        super(s);
        l=new JLabel();
        add(l);
        bg=new ButtonGroup();
        for(int i=0;i<5;i++)
        {
            jb[i]=new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        b1=new JButton("Next");
        b2=new JButton("Bookmark");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);add(b2);
        set();
        l.setBounds(30,40,450,20);
        jb[0].setBounds(50,80,100,20);
        jb[1].setBounds(50,110,100,20);
        jb[2].setBounds(50,140,100,20);
        jb[3].setBounds(50,170,100,20);
        b1.setBounds(100,240,100,30);
        b2.setBounds(270,240,100,30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250,100);
        setVisible(true);
        setSize(600,350);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            if(check())
                count=count+1;
            current++;
            set();
            if(current==9)
            {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        }
        if(e.getActionCommand().equals("Bookmark"))
        {
            JButton bk=new JButton("Bookmark"+x);
            bk.setBounds(480,20+30*x,100,30);
            add(bk);
            bk.addActionListener(this);
            m[x]=current;
            x++;
            current++;
            set();
            if(current==9)
                b2.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for(int i=0,y=1;i<x;i++,y++)
        {
            if(e.getActionCommand().equals("Bookmark"+y))
            {
                if(check())
                    count=count+1;
                now=current;
                current=m[y];
                set();
                ((JButton)e.getSource()).setEnabled(false);
                current=now;
            }
        }

        if(e.getActionCommand().equals("Result"))
        {
            if(check())
                count=count+1;
            current++;
            //System.out.println("correct ans="+count);
            JOptionPane.showMessageDialog(this,"correct ans="+count);
            System.exit(0);
        }
    }
    void set()
    {
        jb[4].setSelected(true);
        if(current==0)
        {
            l.setText("TALK : Que1: Number of primitive data types in Java are?");
            jb[0].setText("6");jb[1].setText("7");jb[2].setText("8");jb[3].setText("9");
        }
        if(current==1)
        {
            l.setText("TALK : Que2: What is the size of float and double in java?");
            jb[0].setText("32 and 64");jb[1].setText("32 and 32");jb[2].setText("64 and 64");jb[3].setText("64 and 32");
        }
        if(current==2)
        {
            l.setText("TALK : Que3: Select the valid statement to declare and initialize an array.?");
            jb[0].setText("int[]A = {}");jb[1].setText("int[]A = {1,2,3}");jb[2].setText("int[]A = (1,2,3)");jb[3].setText("int[][]A = {1,2,3}");
        }
        if(current==3)
        {
            l.setText("TALK : Que4: Arrays in java are?");
            jb[0].setText("Object references");jb[1].setText("Objects");jb[2].setText("Primitive data type");jb[3].setText("None");
        }
        if(current==4)
        {
            l.setText("TALK : Que5:  In which of the following is toString() method defined?");
            jb[0].setText("java.lang.Object");jb[1].setText("java.lang.String\n");jb[2].setText("java.lang.util ");jb[3].setText("None");
        }
        if(current==5)
        {
            l.setText("TALK : Que6: compareTo() returns ");
            jb[0].setText("True");jb[1].setText("False");jb[2].setText("An int value");jb[3].setText("None");
        }
        if(current==6)
        {
            l.setText("TALK : Que7:  To which of the following does the class string belong to ? ");
            jb[0].setText("java.lang");jb[1].setText("java.awt");jb[2].setText("java.applet");
            jb[3].setText("java.string");
        }
        if(current==7)
        {
            l.setText("TALK : Que8: Total constructor string class have?");
            jb[0].setText("3");jb[1].setText("7");jb[2].setText("13");
            jb[3].setText("20");
        }
        if(current==8)
        {
            l.setText("TALK : Que9: Identify the return type of a method that does not return any value.");
            jb[0].setText("int");jb[1].setText("void");jb[2].setText("double");jb[3].setText("None");
        }
        if(current==9)
        {
            l.setText("TALK : Que10: Identify the modifier which cannot be used for constructor ?");
            jb[0].setText("public");jb[1].setText("protected");jb[2].setText("private");
            jb[3].setText("static");
        }
        l.setBounds(30,40,450,20);
        for(int i=0,j=0;i<=90;i+=30,j++)
            jb[j].setBounds(50,80+i,200,20);
    }
    boolean check()
    {
        if(current==0)
            return(jb[2].isSelected());
        if(current==1)
            return(jb[0].isSelected());
        if(current==2)
            return(jb[1].isSelected());
        if(current==3)
            return(jb[1].isSelected());
        if(current==4)
            return(jb[0].isSelected());
        if(current==5)
            return(jb[2].isSelected());
        if(current==6)
            return(jb[0].isSelected());
        if(current==7)
            return(jb[2].isSelected());
        if(current==8)
            return(jb[1].isSelected());
        if(current==9)
            return(jb[3].isSelected());
        return false;
    }
    public static void menu()
    {
        System.out.println("\nMENU");
        System.out.println("1: Add Student");
        System.out.println("2: Delete Student");
        System.out.println("3: Update Student");
        System.out.println("4: Search Student");
        System.out.println("5: Display Students");
        System.out.println("9: Exit program");
        System.out.print("Enter your selection : ");
    }
    public static void main(String s[]) {
        Scanner sss = new Scanner(System.in);
        while (true) {
            String us, ps;
            String user2 = new String("8143");
            String pass2 = new String("Tanmay@28");
            float score;
            Scanner sr = new Scanner(System.in);
            System.out.println("                                      \t\t  Welcome to TALK\n\t\t\t\t\t\t\t\t\t( An Intelligious Student manager system )\n");
            System.out.println("Enter the choice \n1. --> To Take the Attendance\n2. --> To take practice the exam\n3. --> To Start Student Management System\n4. --> To EXIT");
            int sw = sr.nextInt();
            switch (sw) {
                case 1: {
                    System.out.println("Enter the username ");
                    us = sr.next();
                    System.out.println("Enter the password ");
                    ps = sr.next();
                    if (ps.equals(pass2) && us.equals(user2)) {
                        while (true) {
                            char pp;
                            int i = 1, j = 1;
                            String secure = new String("EXIT");
                            String user1 = new String("8143");
                            String pass1 = new String("Tanmay@28");
                            String[] str = new String[20];
                            String[] str1 = new String[20];
                            String[] str2 = new String[20];
                            String pass, user;
                            Scanner ss = new Scanner(System.in);
                            do {
                                System.out.println("Enter the First Name of Student ");
                                str[i] = ss.next();
                                if (str[i].equals(secure)) {
                                    System.out.println("Welcome Admin"); //Exit admin
                                    break;
                                } else {
                                    System.out.println("Enter the Last Name of Student");
                                    str1[i] = ss.next();
                                    System.out.println("Enter the Valid Enrollment number");
                                    do {//enrollment
                                        str2[i] = ss.next();
                                        if (str2[i].length() != 6) {
                                            System.out.println("!!Invalid Input!!\n!!!Try Again!!!");
                                        }
                                    }
                                    while (str2[i].length() != 6);//enrollment giving
                                    if (i < 10) {
                                        System.out.println("\nYour Attendance is marked as 0" + i + "\n");
                                    } else {
                                        System.out.println("\nYour Attendance is marked as " + i + "\n");
                                    }
                                    i++;
                                    j = i;
                                }
                            }
                            while (i < 100);
                            System.out.println("\nPlease select an option \nPress.1 --> To check Attendance\nPress.2 --> To Exit");
                            int yy = ss.nextInt();
                            if (yy == 1) {
                                do {
                                    System.out.println("Enter the Username : ");
                                    user = ss.next();
                                    System.out.println("Enter the Password : ");
                                    pass = ss.next();
                                    if (pass.equals(pass1) && user.equals(user1)) {
                                        for (i = 1; i < j; i++) {
                                            System.out.println("\nName of Student is " + str[i] + " " + str1[i] + "\n" + "Mobile Number is " + str2[i]);
                                            System.out.println("Student no. " + i + "\n");
                                        }
                                        i = i - 1;
                                        System.out.println("There are total " + i + " number of Student presenty is registrated\n\n");
                                        System.out.println("\nDo you like to reset the System \nPress.1 --> To reset it\nPress.2 --> To Shutdown");
                                        int xx = ss.nextInt();
                                        if (xx == 1) {
                                            System.out.println("\nContinue From Here\n==========================================");
                                            break;
                                        } else {
                                            System.exit(0);
                                        }
                                    }
                                    System.out.println("\nSelect an option\nPress.Y--> To try again\nPress.N --> To No");
                                    pp = ss.next().charAt(0);
                                    if (pp == 'Y' || pp == 'y') {
                                        System.out.println("OKh--------");
                                    } else {
                                        break;
                                    }
                                } while (pp == 'y' || pp == 'Y');
                            } else {
                                System.out.println("\n Thankyou ");
                                break;
                            }
                        }
                    } else {
                        System.out.println("\t\t\t\t\tSorry Wrong choice\n \t\t\t\t\t\t\tTRY AGAIN ");
                    }
                }
                break;
                case 2: {
                    System.out.println("Enter the username ");
                    us = sr.next();
                    System.out.println("Enter the password ");
                    ps = sr.next();
                    if (ps.equals(pass2) && us.equals(user2)) {
                        new Main("Online Test Of Java");
                    } else
                        System.out.println("Wrong Choice");
                }
                break;
                case 3: {
                    System.out.println("Enter the username ");
                    us = sr.next();
                    System.out.println("Enter the password ");
                    ps = sr.next();
                    if (ps.equals(pass2) && us.equals(user2)) {
                        StudentRecordManagement hr = new StudentRecordManagement();
                        Record record = new Record();
                        record.setIdNumber(6862);
                        record.setContactNumber(911);
                        record.setName("Ankit");
                        hr.add(record);
                        Scanner input = new Scanner(System.in);
                        int option = 0;
                        do {
                            menu();
                            option = input.nextInt();
                            switch (option) {
                                case 1:
                                    System.out.print("What is the Student id Number ? ");

                                    int idNumber = input.nextInt();
                                    System.out.print("What is the Student contact Number ? ");
                                    int contactNumber = input.nextInt();
                                    input.nextLine();
                                    System.out.print("What is the Student Name ? ");
                                    String name = input.nextLine();
                                    record = new Record(name, idNumber,
                                            contactNumber);
                                    hr.add(record);
                                    System.out.println(record.toString());
                                    break;
                                case 2:
                                    System.out.print(
                                            "What is the Student id number ? ");
                                    int rId = input.nextInt();
                                    hr.delete(rId);
                                    break;
                                case 3:
                                    System.out.print("What is the Student id number? ");
                                    int rIdNo = input.nextInt();
                                    hr.update(rIdNo, input);
                                    break;
                                case 4:
                                    System.out.print(
                                            "What is the Student id ? ");
                                    int bookId = input.nextInt();
                                    if (!hr.find(bookId)) {
                                        System.out.println(
                                                "Student id does not exist\n");
                                    }
                                    break;
                                case 5:
                                    hr.display();
                                    break;
                                case 9:
                                    System.out.println(
                                            "\nThank you for using the program. Goodbye!\n");
                                    break;
                                default:
                                    System.out.println("\nInvalid input\n");
                                    break;
                            }
                        }
                        while (option != 9);
                    } else
                        System.out.println("\t\t\t\t\tSorry Wrong choice\n \t\t\t\t\t\t\tTRY AGAIN ");
                }
                break;
                case 4:
                {
                    System.exit(0);
                }break;
                default: {
                    System.out.println("\t\t\t\t\tSorry Wrong choice\n \t\t\t\t\t\t\tTRY AGAIN ");
                }
                break;
            }
            System.out.println("\nSelect an option\nPress.Y--> To Restart the Program\nPress.N --> To Exit");
            char ppp = sss.next().charAt(0);
            if (ppp == 'Y' || ppp == 'y')
            {
                System.out.println("OKh--------");
            }else {
                System.exit(0);
                break;
            }
        }
    }
}