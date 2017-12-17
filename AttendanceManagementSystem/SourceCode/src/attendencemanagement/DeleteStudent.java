package attendencemanagement;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class DeleteStudent extends JFrame implements ActionListener{
	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
	Container c2;
	JLabel l1,l2,l;
	JTextField t1,t4;
	JButton b3,b4;
	int x;
	DeleteStudent(int n)
	{
		x=n;
		c2=getContentPane();   
		c2.setLayout(null); 
		c2.setBackground(Color.red);
		l=new JLabel("ENTER DETAILS OF STUDENT TO BE DELETED");
		l1=new JLabel("Name");
		l2=new JLabel("Registration No.");
		
		l.setBounds(50,50,1000,50);
		l1.setBounds(50,130,300,50); 
		l2.setBounds(50,210,300,50);
		
		Font f=new Font("Monospaced", Font.PLAIN,18);
		Font f1=new Font("Monospaced", Font.BOLD,25);
		
		l.setFont(f1);
		l1.setFont(f);
		l2.setFont(f);
		
		t1=new JTextField();

		t4=new JTextField();
		
		t1.setBounds(300,140,300,30);
		t4.setBounds(300,220,300,30); 
		c2.add(t1);
		c2.add(t4);
		
		c2.add(l);
		c2.add(l1);
		c2.add(l2);
		
		b3=new JButton("DELETE");
		b4=new JButton("CANCEL");
		b3.setBounds((int)(screensize.getWidth()/2)-100,(int)(screensize.getHeight())-150,100,40);
		b3.addActionListener(this);
		b4.setBounds((int)(screensize.getWidth()/2)+40,(int)(screensize.getHeight())-150,100,40);
		b4.addActionListener(this);
		c2.add(b3);
		c2.add(b4);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,(int)screensize.getWidth(),(int)screensize.getHeight());
		setLayout(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DeleteStudent(0);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="DELETE")
		{
			JOptionPane.showMessageDialog(null,"Are You Sure, you want to Delete!");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amdb","root","");
			Statement stmt=con.createStatement();
			String s=""+Integer.parseInt(t4.getText());
			String sql="delete from student where regno = " + s;
			int r=stmt.executeUpdate(sql);
			sql="delete from attendence where regno = " + s;
			r=stmt.executeUpdate(sql);
			t1.setText("");
			t4.setText("");
			    con.close();
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		}
		else if(e.getActionCommand()=="CANCEL")
		{
			new TeacherMenu(x);
			dispose();
		}
	}

}
