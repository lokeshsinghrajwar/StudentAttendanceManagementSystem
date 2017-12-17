package attendencemanagement;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TeacherMenu extends JFrame implements ActionListener{
	JButton addst,delst,genrec,addatt,lgout;
	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
	int x;
	Container c1;
	TeacherMenu(int n)
	{
		x=n;
		c1=getContentPane();   
		c1.setLayout(null); 
		c1.setBackground(Color.yellow);
		String s;
		ImageIcon i = new ImageIcon("F:\\JAVA CSB\\attendencemanagement\\src\\attendencemanagement\\"+x+".jpeg");
		JLabel b1=new JLabel(i);
		b1.setBounds(1370,50,400,450);
		c1.add(b1);
		JLabel l=new JLabel("Menu");
		addst=new JButton("Add Student");
		delst=new JButton("Delete Student");
		genrec=new JButton("Generate Record");
		addatt=new JButton("Add Today's Attendance");
		l.setBounds(50,50,100,50);
		addst.setBounds(50,100,300,50);
		addst.addActionListener(this);
		delst.setBounds(50,160,300,50);
		delst.addActionListener(this);
		genrec.setBounds(50,220,300,50);
		genrec.addActionListener(this);
		addatt.setBounds(50,280,300,50);
		addatt.addActionListener(this);
		Font f=new Font("Monospaced", Font.PLAIN,18);
		Font f1=new Font("Monospaced", Font.BOLD,25);
		
		l.setFont(f1);
		addst.setFont(f);
		delst.setFont(f);
		genrec.setFont(f);
		addatt.setFont(f);
		c1.add(addst);
		c1.add(l);
		c1.add(delst);
		c1.add(addatt);
		c1.add(genrec);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,(int)screensize.getWidth(),(int)screensize.getHeight());
		
		setLayout(null);
		setVisible(true);
		lgout=new JButton("Log Out");
		lgout.setBounds((int)(screensize.getWidth()/2)-70,(int)(screensize.getHeight())-250,200,40);
		lgout.addActionListener(this);
		lgout.setFont(f);
		c1.add(lgout);
	}
	public static void main(String a[])
	{
		new TeacherMenu(0);
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if(a.getSource()==addst)
		{	new AddStudent(x);
			dispose();
		}
		else if(a.getActionCommand()=="Delete Student")
		{	new DeleteStudent(x);
			dispose();
		}
		else if(a.getActionCommand()=="Generate Record")
		{	
			new GenerateRecord(x);
			dispose();
		}
		else if(a.getActionCommand()=="Add Today's Attendance")
		{	new AddAttendance(x);
			dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Are You Sure, you want to LogOut!");
			new LogIn();
			dispose();
		}
	}
}