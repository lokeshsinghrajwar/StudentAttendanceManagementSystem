package attendencemanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import javax.swing.*;

public class StudentMenu extends JFrame implements ActionListener{
	JButton lgout;
	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
	JTextArea t1;
	Container c1;
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	StudentMenu(int n)
	{
		
		c1=getContentPane();   
		c1.setLayout(null); 
		JLabel l=new JLabel("YOUR ATTENDENCE");
		
		ImageIcon i = new ImageIcon("F:\\JAVA CSB\\attendencemanagement\\src\\attendencemanagement\\login-icon.png");
		JLabel b1=new JLabel(i);
		b1.setBounds((int)(screensize.getWidth())-270,10,250,250);
		c1.add(b1);
		c1.setBackground(Color.cyan);
		l.setBounds(50,50,500,150);
		
		Font f=new Font("Allegro", Font.PLAIN,18);
		Font f1=new Font("Monospaced", Font.BOLD,25);
		
		l.setFont(f1);		
		t1=new JTextArea(250,20);
		t1.setBounds(50,280,650,250);
		t1.setFont(f);
		t1.setEditable(false);
		lgout=new JButton("Log Out");
		lgout.setBounds((int)(screensize.getWidth()/2)-70,(int)(screensize.getHeight())-150,100,40);
		lgout.addActionListener(this);
		lgout.setFont(f);
		c1.add(lgout);
		c1.add(l);
		c1.add(t1);
		t1.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,(int)screensize.getWidth(),(int)screensize.getHeight());
		
		setLayout(null);
		setVisible(true);
		try{
			t1.setVisible(true);
			t1.setText("");
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amdb","root","");
			stmt=con.createStatement();
			String sql="select * from attendence where regno = " + n;
			rs=stmt.executeQuery(sql);
			t1.setText("RollNo\tName\tJava\tDAA\tOS\tRegno\n");
			while(rs.next())
	     	t1.append(rs.getInt("rollno")+"\t"+rs.getString("name")+"\t"+rs.getInt("Java")+"\t"+rs.getInt("daa")+"\t"+rs.getInt("os")+"\t"+rs.getInt("regno")+"\n");
		    	con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
	}
	public static void main(String a[])
	{
		new StudentMenu(0);
	}
	@Override
	public void actionPerformed(ActionEvent a) {

			new LogIn();
			dispose();
		
	}
}