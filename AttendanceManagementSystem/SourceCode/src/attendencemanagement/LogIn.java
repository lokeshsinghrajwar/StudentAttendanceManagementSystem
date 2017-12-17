package attendencemanagement;
import java.awt.*;
import javax.swing.*;
import javax.tools.Tool;
import java.awt.event.*;
import java.sql.*;

class LogIn extends JFrame implements ActionListener,ItemListener{		     
		Container c; 		
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
		Boolean s=false,t=false;
		JLabel uLabel=new JLabel("User Name");   
		JLabel pLabel =new JLabel("Password");     
		JLabel head=new JLabel("Attendance Management System");
		JTextField uname=new JTextField();  
		JPasswordField pass=new JPasswordField();  
		JRadioButton stu,te;
		ButtonGroup b=new ButtonGroup();
		JButton loginbtn=new JButton("Login");
		JLabel b1,b2;
		static int noc=1;
		LogIn()
		{
			
				c=this.getContentPane();   
				c.setLayout(null); 
                ImageIcon i = new ImageIcon("F:\\JAVA CSB\\attendencemanagement\\src\\attendencemanagement\\login-icon.png");
            	
				b1=new JLabel(i);
				b1.setBounds((int)(screensize.getWidth()/2)-175,70,250,250);
				
				ImageIcon i1 = new ImageIcon("F:\\JAVA CSB\\attendencemanagement\\src\\attendencemanagement\\attendance-banner.png");
				b2=new JLabel(i1);
				b2.setBounds((int)(screensize.getWidth()/2)-778,(int)screensize.getHeight()-400,1549,400);
				c.add(b2);
				
				head.setBounds(650,20,1000,40);
				uLabel.setBounds((int)(screensize.getWidth()/2)-200,310,200,40);     
				uname.setBounds((int)(screensize.getWidth()/2)-200,360,300,40);
				pLabel.setBounds((int)(screensize.getWidth()/2)-200,410,200,40);					
				//c.setBounds((int)(screensize.getWidth()/2)-200,200,200,40);
				pass.setBounds((int)(screensize.getWidth()/2)-200,460,300,40);		
				loginbtn.setBounds((int)(screensize.getWidth()/2)-120,600,140,50); 
				loginbtn.addActionListener(this);      
				
				Font f=new Font("Monospaced", Font.BOLD,20);
				Font f1=new Font("Monospaced", Font.BOLD,35);
				uLabel.setFont(f);
				pLabel.setFont(f);
				head.setFont(f1);
				uname.setFont(f);
				pass.setFont(f);	
				
				loginbtn.setFont(f);
				
				stu=new JRadioButton("Student");
				te=new JRadioButton("Teacher");
				stu.setBounds((int)(screensize.getWidth()/2)-200,530,80,40);
				te.setBounds((int)(screensize.getWidth()/2)-200,500,80,40);
				b.add(stu);
				b.add(te);
				stu.addItemListener(this);
				te.addItemListener(this);
				
				c.add(b1);
				c.add(uLabel);
				c.add(pLabel);
				c.add(head);
				c.add(uname);
				c.add(pass);
				c.add(loginbtn);
				c.add(stu);
				c.add(te);
				
				  
				setVisible(true);
				setTitle("Attendance Management");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				setBounds(0,0,(int)screensize.getWidth(),(int)screensize.getHeight());
		}		

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==loginbtn)
		{
			String username=uname.getText();    
			String password=pass.getText();   
			if(s==true)
			{
			try
			{
				boolean f=true;
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amdb","root","");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from teacher");
				while(rs.next())
				{
					
					if(username.equals(rs.getString("name")) && password.equals(rs.getString("password")))     
					{
							noc=rs.getInt("noofclass");
							new TeacherMenu(rs.getInt("Sno"));
							f=false;
						    dispose();
					}
				}
				if(f)
				JOptionPane.showMessageDialog(null,"Invalid UserName or Password");// msg=new JOptionPane()
			con.close(); 	
			}
			catch(Exception e2)
			{
				System.out.println(e2);
			}
		}
			else if(t==true)
			{
				try
				{
					boolean f=true;
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amdb","root","");
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from student");
					while(rs.next())
					{
						
						if(username.equals(rs.getString("name")) && password.equals(rs.getInt("regno")+""))     
						{
								new StudentMenu(rs.getInt("regno"));
								f=false;
							    dispose();
						}
					}
					if(f)
					JOptionPane.showMessageDialog(null,"Invalid UserName or Password");// msg=new JOptionPane()
				con.close(); 	
				}
				catch(Exception e2)
				{
					System.out.println(e2);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Select Catogary");
			}
			
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(te.isSelected()==true)
			s=true;
		else
			t=true;
	} 	
	public static void main(String a[])
	{
		new LogIn();
	}
	
}

