package attendencemanagement;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddStudent extends JFrame implements ActionListener{
	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
	Container c2;
	JLabel l1,l2,l3,l4,l,l5,l6,l7;
	JTextField t1,t2,t3,t4,t6,t7;
	JTextArea t5;
	JButton b3,b4;
	int x;
	AddStudent(int n)
	{
		x=n;
		c2=getContentPane();   
		c2.setLayout(null); 
		c2.setBackground(Color.gray);
		ImageIcon i = new ImageIcon("F:\\JAVA CSB\\attendencemanagement\\src\\attendencemanagement\\as.jpeg");
		JLabel b1=new JLabel(i);
		b1.setBounds(770,10,560,800);
		c2.add(b1);
		
		l=new JLabel("ADD DETAILS");
		l1=new JLabel("Name");
		l2=new JLabel("Registration No.");
		l3=new JLabel("RollNo");
		l4=new JLabel("Address");
		l5=new JLabel("DOB");
		l6=new JLabel("Father's Name");
		l7=new JLabel("Mobile No.");
		
		l.setBounds(50,50,200,50);
		l1.setBounds(50,100,300,50); 
		l6.setBounds(50,140,300,50);
		l2.setBounds(50,180,300,50); 
		l3.setBounds(50,220,300,50); 
		l4.setBounds(50,260,300,50); 
		l5.setBounds(50,320,300,50);
		l7.setBounds(50,360,300,50);
		
		Font f=new Font("Monospaced", Font.PLAIN,18);
		Font f1=new Font("Monospaced", Font.BOLD,25);
		
		l.setFont(f1);
		l1.setFont(f);
		l2.setFont(f);
		l3.setFont(f);
		l4.setFont(f);
		l5.setFont(f);
		l6.setFont(f);
		l7.setFont(f);
		
		t1=new JTextField();
		t2=new JTextField();
		
		t5=new JTextArea();
		t3=new JTextField();
		t4=new JTextField();
		t6=new JTextField();
		t7=new JTextField();
		t1.setBounds(300,110,300,30);
		t2.setBounds(300,150,300,30);
		t3.setBounds(300,190,300,30); 
		t4.setBounds(300,330,300,30);
		t5.setBounds(300,270,300,50);
		c2.add(t1);
		c2.add(t2);
		c2.add(t3);
		c2.add(t4);
		c2.add(t5);
		t6.setBounds(300,230,300,30);
		c2.add(t6);
		t7.setBounds(300,370,300,30);
		c2.add(t7);
		
		b3=new JButton("ADD");
		b4=new JButton("CANCEL");
		b3.setBounds((int)(screensize.getWidth()/2)-70,(int)(screensize.getHeight())-150,100,40);
		b3.addActionListener(this);
		b4.setBounds((int)(screensize.getWidth()/2)+40,(int)(screensize.getHeight())-150,100,40);
		b4.addActionListener(this);
		c2.add(b3);
		c2.add(b4);
		
		c2.add(l);
		c2.add(l1);
		c2.add(l2);
		c2.add(l3);
		c2.add(l4);
		c2.add(l5);
		c2.add(l6);
		c2.add(l7);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,(int)screensize.getWidth(),(int)screensize.getHeight());
		setLayout(null);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AddStudent(0);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getActionCommand()=="ADD")
		{
			JOptionPane.showMessageDialog(null,"Add Details");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amdb","root","");
			PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?,?,?,?,?)");
		    ps.setString(1,t1.getText());
		    ps.setString(2,t2.getText());
		    ps.setInt(3,Integer.parseInt(t3.getText()));
		    ps.setInt(4,Integer.parseInt(t6.getText()));
		    ps.setString(5,t5.getText());
		    ps.setString(6,t4.getText());
		    ps.setInt(7,Integer.parseInt(t7.getText()));
			ps.executeUpdate();
			ps=con.prepareStatement("insert into attendence values(?,?,?,?,?,?)");
		    ps.setInt(1,Integer.parseInt(t6.getText()));
		    ps.setString(2,t1.getText());
		    ps.setInt(3,0);
		    ps.setInt(4,0);
		    ps.setInt(5,0);
		    ps.setInt(6,Integer.parseInt(t3.getText()));
			ps.executeUpdate();
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");
			t7.setText("");
			con.close();
	
		}

		catch(Exception e)
		{
			System.out.println(e);
		}
		}
		else 
		{
			new TeacherMenu(x);
			dispose();
		}
	}
	

}
