package attendencemanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;


public class AddAttendance extends JFrame implements ActionListener{
	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
	Container c;
	List l;
	Panel P1,P2;
	TextArea ta;
	JButton back,lgout;
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	int x;
	AddAttendance(int n)
	{
		x=n;
		c=getContentPane();   
		c.setLayout(null); 
		c.setBackground(Color.blue);
		Label L1=new Label("Select Present Students");
		L1.setBounds(100,50,200,20);
		l=new List(10);
		l.setBounds(100,80,200,200);
		ta =new TextArea(70,80);
		ta.setBounds(100,350,500,500);
		ta.setEditable(false);
		ta.setVisible(false);
		c.add(L1);
		c.add(l);
		c.add(ta);
		

		//*************
		ImageIcon i1 = new ImageIcon("F:\\JAVA CSB\\attendencemanagement\\src\\attendencemanagement\\ad.jpeg");
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
		
		l.addActionListener(this);
		L1.setFont(f);
		
		back=new JButton("<<");
		back.setBounds(1,1,60,60);
		c.add(back);
		back.setFont(f);
		back.addActionListener(this);
		lgout=new JButton("Log Out");
		lgout.setBounds(70,(int)(screensize.getHeight())-150,200,40);
		lgout.addActionListener(this);
		lgout.setFont(f);
		c.add(lgout);
		System.out.println(LogIn.noc);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amdb","root","");
			Statement stmt=con.createStatement();
			PreparedStatement ps=con.prepareStatement("update teacher set noofclass ="+(LogIn.noc+1)+" where Sno="+n);
			ps.executeUpdate();	
			ResultSet rs=stmt.executeQuery("select  rollno,name  from attendence");
			while(rs.next())
				l.add(rs.getString("rollno")+" "+rs.getString("name"));
			    con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public static void main(String args[])
	{
		new AddAttendance(0);
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
		else if(a.getSource()==back)
		{
			new TeacherMenu(x);
			dispose();
		}
		else
		{
		try{
		ta.setVisible(true);
		ta.setText("");
	    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amdb","root","");
		stmt=con.createStatement();
		int i =l.getSelectedIndex()+1;
		String sql="select * from attendence where rollno = " + i;
		rs=stmt.executeQuery(sql);
		if(x==1)
		{
		ta.setText("RollNo\tName\tJava\n");
		rs.next();
     	ta.append(rs.getInt("rollno")+"\t"+rs.getString("name")+" \t"+(rs.getInt("java")+1)+"\n");
     	int a1=(rs.getInt("java")+1);
    	PreparedStatement ps=con.prepareStatement("update attendence set java ="+a1+" where rollno="+rs.getInt("rollno"));
		ps.executeUpdate();	
		}
		if(x==2)
		{
		ta.setText("RollNo\tName\tDAA\n");
		rs.next();
     	ta.append(rs.getInt("rollno")+"\t"+rs.getString("name")+" \t"+rs.getInt("daa")+"\n");
     	int a1=(rs.getInt("daa")+1);
    	PreparedStatement ps=con.prepareStatement("update attendence set daa ="+a1+" where rollno="+rs.getInt("rollno"));
		ps.executeUpdate();	
		}
		if(x==3)
		{
		ta.setText("RollNo\tName\tOS\n");
		rs.next();
     	ta.append(rs.getInt("rollno")+"\t"+rs.getString("name")+"\t"+rs.getInt("os")+"\n");
     	int a1=(rs.getInt("os")+1);
    	PreparedStatement ps=con.prepareStatement("update attendence set os ="+a1+" where rollno="+rs.getInt("rollno"));
		ps.executeUpdate();	
		}
		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		}
	}
}
