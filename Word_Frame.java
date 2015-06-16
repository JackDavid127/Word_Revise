import java.io.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.ItemListener;


import javax.swing.*;
public class Word_Frame extends JFrame {
	private JTextField title = new JTextField("Word Revise");
	private Box list_box = Box.createVerticalBox();
	private Panel import_panel = new Panel();
	private Choice import_list = new Choice();
	private JTextField import_text=new JTextField("Import New Word List");
	
	private Panel change_panel = new Panel();
	private Choice change_list = new Choice();
	private JTextField change_text=new JTextField("Change Word List");
	
	private Panel task_panel = new Panel();
	private Choice task_list = new Choice();
	private JTextField task_text=new JTextField("Task today");
	private int n;
	private String bookname;
	private Study_Panel study_panel; 
	private String import_name;
	private Catch catch_example;
	//public Panel list_set = new Panel();//缺少当前状态以及从study返回主界面导入新单词库
	//public JTextArea list_status=new JTextArea();//
	
	private Panel start_panel = new Panel();
	public JButton start_study = new JButton("Start Studying");
	
	public Word_Frame(){
		setSize(800, 600);
		setTitle("Word Revise");
		
		title.setFont(new Font("Buxton Sketch",50,100));
		title.setBackground(Color.white);
		title.setBorder(null);
		title.setEditable(false);
		title.setHorizontalAlignment(JTextField.CENTER);
	    this.add(title, BorderLayout.NORTH);
	    
	    
	    import_text.setFont(new Font("Buxton Sketch", 20,20));
	    import_text.setBorder(null);
	    import_text.setEditable(false);
	    import_text.setHorizontalAlignment(JTextField.CENTER);
	    import_list.add("四级词汇书");
	    import_list.add("六级词汇书");//
	    import_panel.setBackground(Color.white);
	    import_panel.add(import_text);
	    import_panel.add(import_list);
	    
	    change_text.setFont(new Font("Buxton Sketch", 20,20));
	    change_text.setBorder(null);
	    change_text.setEditable(false);
	    change_text.setHorizontalAlignment(JTextField.CENTER);
	    change_list.add("CET4");
	    change_list.add("CET6");//
	    change_panel.setBackground(Color.white);
	    change_panel.add(change_text);
	    change_panel.add(change_list);
	    
	    task_text.setFont(new Font("Buxton Sketch", 20,20));
	    task_text.setBorder(null);
	    task_text.setEditable(false);
	    task_text.setHorizontalAlignment(JTextField.CENTER);
	    for(int i = 10; i <= 200; i += 10)
	    	task_list.add(Integer.toString(i));
	    task_panel.setBackground(Color.white);
	    task_panel.add(task_text);
	    task_panel.add(task_list);
	    
	    start_panel.setBackground(Color.white);
	    start_panel.add(start_study);  //start Button
	    
	    list_box.add(import_panel);
	    list_box.add(change_panel);
	    list_box.add(task_panel);
	    list_box.add(start_panel);
	    this.add(list_box, BorderLayout.CENTER);
	    setVisible(true);
		import_list.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				import_name = import_list.getSelectedItem();//String 
			}
		});
		change_list.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				bookname = change_list.getSelectedItem();
			}
		});		
		task_list.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				String task = task_list.getSelectedItem();
				n = Integer.parseInt(task);
			}
		});	
		start_study.addActionListener(new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e){
				dispose();
			    Database b = new Database();
				study_panel = new Study_Panel(b, bookname, n);
				study_panel.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						System.exit(0);//Quit the application
					}
				});
				catch_example = new Catch();
				b.creatNewList(bookname);
				try {
					catch_example.loadBook(b, bookname, import_name);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.err.println("Error occured in catch_example");
					e1.printStackTrace();
				}
			}
		});
		
		}
	public static void main(String []args){
		Word_Frame gui= new Word_Frame();
		gui.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);//Quit the application
			}
		});
	}


}
