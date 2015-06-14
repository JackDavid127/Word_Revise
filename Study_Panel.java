import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javazoom.jl.player.Player;


public class Study_Panel extends JFrame{
	String []ccc={"apple","apple1","qqq","ttt"};
	String []cccc={"rabbit","rrr","aaa","bbb"};
	//public Core c;
	//public Database base;
	//public Question nowq;
	public Word r=new Word("n","p","m","e","s",new Date(),0);
	public String list_name;
	public int numq;
	public Question nowq=new Question("apple",ccc,r,0,true);
	public Question nowq1=new Question("rabbit",cccc,r,0,false);
	public boolean assured=false; //�Ƿ��Ѿ����л������
	public Box box= Box.createVerticalBox();
	public JTextField title=new JTextField(); //
	private JButton counter = 	 new JButton();	
	public JButton dontknow=new JButton("I Don't Remember"); //����ʶ�õ��ʣ�ֱ�ӷ��ش���
	public JButton donknow=new JButton("I Don't Remember"); //����ʶ�õ��ʣ�ֱ�ӷ��ش���
	public JButton know =new JButton("I Remember."); //ѡ��A
	public JTextField time=new JTextField("����ʱ"); //����ʱ��������
	public JPanel Ptitle=new JPanel(); //title
	public JPanel Poption=new JPanel(); //option
	public JPanel Pcontent=new JPanel(); //ѡ��+ͼ
	//ѡ��ģʽ
	public JButton option1=new JButton(); //ѡ��A
	public JButton option2=new JButton(); //ѡ��B
	public JButton option3=new JButton(); //ѡ��C
	public JButton option4=new JButton(); //ѡ��D
	public JPanel option1s =new JPanel();
	public JPanel option2s =new JPanel();
	public JPanel option3s =new JPanel();
	public JPanel option4s =new JPanel();
	public Box Pselect=Box.createVerticalBox();
	//���ģʽ	
	public JTextField spell=new JTextField(""); //ƴд��
	public JPanel spells = new JPanel();
	public JButton spellc=new JButton("OK"); //ƴдȷ�Ͽ�
	public JPanel spellcs = new JPanel();
	public Box Pspell=Box.createVerticalBox(); //ƴд��
	ImageIcon img = new ImageIcon("play.jpg");//ͼƬ��·������ȷ
	JButton pron=new JButton(img);
	int num=0;
	//expression
	public JTextField Chinese =new JTextField(); //
	public JTextField Blank =new JTextField("   "); 
	public JTextField English =new JTextField(); 
	public JButton Next=new JButton("Next");
	public JButton Return=new JButton("Return");
	public JTextField Sentence =new JTextField(); //����չΪ����
	public JPanel PEng=new JPanel(); //title
	public JPanel PChin=new JPanel(); //title
	public JPanel PSen=new JPanel(); //title
	public JPanel PNext=new JPanel(); //title
	java.util.Timer timer = new java.util.Timer();								//���õ���ʱ
	private Player player; //���ֲ�����			
	
	
	public void Display(int sec){													//���ĵ���ʱ��ʾ��ʱ��
		counter.setText("    Time: "+sec+"   ");
	}
	public void Play(String filename){
		try {
            BufferedInputStream buffer = new BufferedInputStream(
                    new FileInputStream(filename));
            player = new Player(buffer);
            player.play();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	public void Assure(){
		//�������
		title=new JTextField(nowq.title); //
		title.setFont(new Font("Buxton Sketch", 80, 80));
		title.setBorder(null);
		title.setEditable(false);
		title.setHorizontalAlignment(JTextField.CENTER);	
		Ptitle.add(title);
		
		//Poption.add(dontknow);
		
		counter.setBackground(null);												//���õ���ʱ�Ĺ��
		counter.setContentAreaFilled(false);
		counter.setFont(new Font("Buxton Sketch", 20, 20));
		counter.setSize(150, 75);
		
		//counter.setEditable(false);
		Poption.add(counter);
		
		
		donknow.setPreferredSize(new Dimension(250,50)); 
		know.setPreferredSize(new Dimension(250,50)); 
		Pselect.add(Box.createVerticalStrut(50));
		option1s.add(donknow);
		option2s.add(know);		
		Pselect.add(option1s);	
		Pselect.add(Box.createVerticalStrut(70));
		Pselect.add(option2s);	
		Pselect.add(Box.createVerticalStrut(70));
		Pcontent.add(Pselect);
			
		box.add(Ptitle);
		box.add(Poption); //����
		box.add(Pcontent); //����+����
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		setVisible(true);
		
		
		
		
	}
	public void Choice(){
		title=new JTextField(nowq.title); //
		title.setFont(new Font("Buxton Sketch", 80, 80));
		title.setBorder(null);
		title.setEditable(false);
		title.setHorizontalAlignment(JTextField.CENTER);	
		Ptitle.add(title);
		
		ImageIcon img = new ImageIcon("play.jpg");//ͼƬ��·������ȷ
		img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	    pron=new JButton(img);
		pron.setContentAreaFilled(false);
		pron.setBorderPainted(false);
		pron.setBorder(BorderFactory.createRaisedBevelBorder()); 
		Ptitle.add(pron);
		Poption.add(dontknow);
		counter.setBackground(null);												//���õ���ʱ�Ĺ��
		counter.setContentAreaFilled(false);
		counter.setFont(new Font("Buxton Sketch", 20, 20));
		counter.setSize(150, 75);
		
		//counter.setEditable(false);
		Poption.add(counter);
		//time.setEditable(false);
		//Poption.add(time);
		Pcontent.setLayout(new BorderLayout());
		
		option1=new JButton(nowq.choices[0]); //ѡ��A		
		option2=new JButton(nowq.choices[1]); //ѡ��B
		option3=new JButton(nowq.choices[2]); //ѡ��C
		option4=new JButton(nowq.choices[3]); //ѡ��D
		option1.setPreferredSize(new Dimension(250,50)); 
		option2.setPreferredSize(new Dimension(250,50)); 
		option3.setPreferredSize(new Dimension(250,50)); 
		option4.setPreferredSize(new Dimension(250,50)); 
		Pselect.add(Box.createVerticalStrut(50));
		option1s.add(option1);
		option2s.add(option2);
		option3s.add(option3);
		option4s.add(option4);
		Pselect.add(option1s);	
		Pselect.add(option2s);	
		Pselect.add(option3s);	
		Pselect.add(option4s);
		PNext.add(Return);
		Pselect.add(PNext);
		Pcontent.add(Pselect);
		box.add(Ptitle);
		box.add(Poption); //����
		box.add(Pcontent); //����+����
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		setVisible(true);
		pron.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Play("song.mp3");
			}
		});
		
	}
	public void Spell(){
		title=new JTextField(nowq.title); //
		title.setFont(new Font("Buxton Sketch", 80, 80));
		title.setBorder(null);
		title.setEditable(false);
		title.setHorizontalAlignment(JTextField.CENTER);	
		Ptitle.add(title);
		
		ImageIcon img = new ImageIcon("play.jpg");//ͼƬ��·������ȷ
		img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		pron=new JButton(img);
		pron.setContentAreaFilled(false);
		pron.setBorderPainted(false);
		pron.setBorder(BorderFactory.createRaisedBevelBorder()); 
		Ptitle.add(pron);
		Poption.add(dontknow);
		//time.setEditable(false);
		//Poption.add(time);
		
		counter.setBackground(null);												//���õ���ʱ�Ĺ��
		counter.setContentAreaFilled(false);
		counter.setFont(new Font("Buxton Sketch", 20, 20));
		counter.setSize(150, 75);
		
		//counter.setEditable(false);
		Poption.add(counter);
		Pcontent.setLayout(new BorderLayout());
		spell.setFont(new Font("Buxton Sketch", 40, 40));
		this.setVisible(true);
		spell.setPreferredSize(new Dimension(250,50)); 
		spellc.setPreferredSize(new Dimension(100,50)); 
		spells.add(spell);
			
		spellcs.add(spellc);
		Pspell.add(Box.createVerticalStrut(120));
		Pspell.add(spells);
		Pspell.add(spellcs);
		//Return.setPreferredSize(new Dimension(100,50)); 
		PNext.add(Return);
		Pspell.add(PNext);
		
		Pcontent.add(Pspell);
		
		box.add(Ptitle);
		box.add(Poption); //����
		box.add(Pcontent); //����+����
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		setVisible(true);
		spell.requestFocus();  //���ù��λ��
		pron.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("baaa");
				Play("song.mp3");
				System.out.println("aaa");
			}
		});
	}
	public void Start(){
		setSize(800,600);
		setTitle("Word_revise");
		setVisible(true);
		box.removeAll();
		Ptitle.removeAll();
		Poption.removeAll();
		Pcontent.removeAll();
		Pselect.removeAll();
		option1s.removeAll();
		option2s.removeAll();
		option3s.removeAll();
		option4s.removeAll();
		spells.removeAll();
		Pspell.removeAll();
		PNext.removeAll();
		spell.setText("");
	//	if(assured==false) {
	//		nowq=getNewQ();
	//	}
		if(num==1) 
			nowq=nowq1;
		if (nowq!=null){
			if(nowq.needAssure==true && assured==false){
				Assure();
				timer = new java.util.Timer();	
				timer.schedule(new TimerTask(){												//����ʱ��ʱ��10s
					int x = 10;
					public void run(){														//ÿ����1s������¼�ʱ����ʾ
						Display(x);
						x--;
						if(x == -1){														//ʱ��ľ�����ֹͣ��ʱ���Ĺ���,�Զ��жϴ���
							cancel();
							nowq.obj.Incorrect();
							num=1;
							setVisible(false);
							Expression();
							
						}
					}
				},0,1000);
				
			}
			else{
				if(nowq.needAssure==true) assured=false;
				if(nowq.choices==null){
					Spell();
				}
				else{
					Choice();
				}
				timer = new java.util.Timer();	
				timer.schedule(new TimerTask(){												//����ʱ��ʱ��10s
					int x = 15;
					public void run(){														//ÿ����1s������¼�ʱ����ʾ
						Display(x);
						x--;
						if(x == -1){														//ʱ��ľ�����ֹͣ��ʱ���Ĺ���,�Զ��жϴ���
							cancel();
							nowq.obj.Incorrect();
							num=1;
							setVisible(false);
							Expression();
							
						}
					}
				},0,1000);
				
			}
			
		}
		else{
			//�����˽����������ʱ����
		}
	}
	public void Expression(){
		PEng.removeAll();
		PChin.removeAll();
		PSen.removeAll();
		PNext.removeAll();
		box.removeAll();
		
		English =new JTextField(nowq.obj.name); 
		English.setFont(new Font("Buxton Sketch", 80, 80));
		English.setBorder(null);
		English.setEditable(false);
		English.setHorizontalAlignment(JTextField.CENTER);
		PEng.add(English);
		
		ImageIcon img = new ImageIcon("play.jpg");//ͼƬ��·������ȷ
		img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		pron=new JButton(img);
		pron.setContentAreaFilled(false);
		pron.setBorderPainted(false);
		pron.setBorder(BorderFactory.createRaisedBevelBorder()); 
		PEng.add(pron);
		
		Chinese.setFont(new Font("Buxton Sketch", 60, 60));
		Chinese.setBorder(null);
		Chinese.setEditable(false);
		Chinese.setHorizontalAlignment(JTextField.CENTER);	
		PChin.add(Chinese);
		Sentence =new JTextField(nowq.obj.expre);
		Sentence.setFont(new Font("Buxton Sketch", 30, 30));
		Sentence.setBorder(null);
		Sentence.setEditable(false);
		Sentence.setHorizontalAlignment(JTextField.CENTER);	
		PSen.add(Sentence);
		Next.setPreferredSize(new Dimension(200,50)); 
		PNext.add(Next);
		Return.setPreferredSize(new Dimension(200,50)); 
		PNext.add(Return);
				
		box.add(PEng);
		box.add(PChin);
		box.add(Box.createVerticalStrut(80));
		box.add(PSen);
		box.add(PNext);
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		setVisible(true);
		setAlwaysOnTop(true);
		pron.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Play("song.mp3");
			}
		});
		Next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				repaint();
				Start();
			}
		});
	}
	public Study_Panel(){
   //public Study_Panel(Database b,String ln,int n){
		//base=b;list_name=ln;numq=n;
		//c=new Core(b,ln,n);
		
		Start();	
		
		donknow.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				timer.cancel();
				nowq.obj.Incorrect();
				num=1;
				setVisible(false);
				repaint();
				Expression();
			}
		});
		know.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				timer.cancel();
				assured=true;
				repaint();
				Start();
			}
		});
		
		dontknow.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timer.cancel();
				nowq.obj.Incorrect();
				num=1;
				repaint();
				Expression();
			}
		});
		pron.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Play("song.mp3");
			}
		});
		option1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timer.cancel();
					System.out.println("ok");
				if(nowq.ans==0){
					nowq.obj.Correct();
					//JOptionPane.showMessageDialog(null,"����ˣ�");
					//getContentPane().remove(box);
					num=1;
				    //repaint();
					Start();
					
				}
				else{
					
					nowq.obj.Incorrect();
					Expression();
				}
			}
		});
		option2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timer.cancel();
				if(nowq.ans==1){
					nowq.obj.Correct();
					Start();
					
				}
				else{
					nowq.obj.Incorrect();
					Expression();
				}
			}
		});
		option3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timer.cancel();
				if(nowq.ans==2){
					nowq.obj.Correct();
					Start();
					
				}
				else{
					nowq.obj.Incorrect();
					Expression();
				}
			}
		});
		option4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timer.cancel();
				if(nowq.ans==3){
					nowq.obj.Correct();
					Start();
					
				}
				else{
					nowq.obj.Incorrect();
					Expression();
				}
			}
		});
		spellc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timer.cancel();
				String Myans = spell.getText();
				if(Myans.equals(nowq.obj.name)) {
					nowq.obj.Correct();
					num=1;
					Start();
				}
				else {
					nowq.obj.Incorrect();
					Expression();
				}
				
			}
		});
		Return.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timer.cancel();
				dispose();
				
			}
		});
	}

	public static void main(String []args){
		//Question q
		Study_Panel gui=new Study_Panel();
		gui.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);//Quit the application
			}
		});
	}
}
