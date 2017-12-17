package attendencemanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;


public class GenerateRecord extends JFrame implements ActionListener{
	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
	Container c;
	
	Panel P1,P2;
	TextArea ta;
	JButton back,lgout;
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	int x;
	GenerateRecord(int n)
	{
		x=n;
		c=getContentPane();   
		c.setLayout(null); 
		c.setBackground(Color.green);
		Label L1=new Label("STUDENT RECORD");
		L1.setBounds(200,100,200,20);
		ta =new TextArea(70,80);
		ta.setBounds(100,350,500,500);
		ta.setEditable(false);
		ta.setVisible(false);
		c.add(L1);
		c.add(ta);
		

		//*************
		ImageIcon i1 = new ImageIcon("F:\\JAVA CSB\\attendencemanagement\\src\\attendencemanagement\\GR.jpeg");
		JLabel b2=new JLabel(i1);
		b2.setBounds(600,320,1700,400);
		c.add(b2);
		//***************
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		setBounds(0,0,(int)screensize.getWidth(),(int)screensize.getHeight());
		setTitle("LISTGET");
		Font f=new Font("Allegro", Font.PLAIN,18);
		Font f1=new Font("Monospaced", Font.BOLD,25);
		L1.setFont(f);
		
		back=new JButton("<<");
		back.setBounds(1,1,60,60);
		c.add(back);
		back.setFont(f);
		back.addActionListener(this);
		lgout=new JButton("Log Out");
		lgout.setBounds(200,(int)(screensize.getHeight())-150,200,40);
		lgout.addActionListener(this);
		lgout.setFont(f);
		c.add(lgout);
		{
			try{
			ta.setVisible(true);
			ta.setText("");
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amdb","root","");
			stmt=con.createStatement();
			String sql="select * from attendence";
			rs=stmt.executeQuery(sql);
			if(x==1)
			{
			ta.setText("RollNo\tName\tJava\tPercentage\n");
			while(rs.next())
			{
				float f11=(float) (rs.getInt("java"));
				f11/=LogIn.noc;
				f11*=100;
	     	ta.append(rs.getInt("rollno")+"\t"+rs.getString("name")+" \t"+rs.getInt("java")+"\t"+f11+"\n");
			}
			}
			if(x==2)
			{
			ta.setText("RollNo\tName\tDAA\tPercentage\n");
			while(rs.next())
			{
				float f11=(float) (rs.getInt("daa"));
				f11/=LogIn.noc;
				f11*=100;	
	     	ta.append(rs.getInt("rollno")+"\t"+rs.getString("name")+" \t"+rs.getInt("daa")+"\t"+f11+"\n");
			}
			}
			if(x==3)
			{
				float f11=(float) (rs.getInt("os"));
				f11/=LogIn.noc;
				f11*=100;
			ta.setText("RollNo\tName\tOS\tPercentage\n");
			while(rs.next())
			{
	     	ta.append(rs.getInt("rollno")+"\t"+rs.getString("name")+"\t"+rs.getInt("os")+"\t"+f11+"\n");
			}
			}
			con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
	}
	
	public static void main(String args[])
	{
		new GenerateRecord(1);
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if(a.getSource()==lgout)
		{
			JOptionPane.showMessageDialog(null,"Are You Sure, you want to LogOut!");
			new LogIn();
			dispose();
		}
		else
		{
			new TeacherMenu(x);
			dispose();
		}
		
	}
}
