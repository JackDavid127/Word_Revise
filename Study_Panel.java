import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javazoom.jl.player.Player;

public class Study_Panel extends JFrame{
	public Core c;
	public Database base;
	public Question nowq;
	public String list_name;
	public int numq;
	public boolean assured=false; //是否已经进行回忆测试
	public Box box= Box.createVerticalBox();
	public JTextField title=new JTextField(); //
	private JButton counter = 	 new JButton();	
	public JButton dontknow=new JButton("I Don't Remember"); //不认识该单词，直接返回错误
	public JButton donknow=new JButton("I Don't Remember"); //不认识该单词，直接返回错误
	public JButton know =new JButton("I Remember."); //选项A
	public JTextField time=new JTextField("倒计时"); //倒计时、次数？
	public JPanel Ptitle=new JPanel(); //title
	public JPanel Poption=new JPanel(); //option
	public JPanel Pcontent=new JPanel(); //选项+图
	//选择模式
	public JButton option1=new JButton(); //选项A
	public JButton option2=new JButton(); //选项B
	public JButton option3=new JButton(); //选项C
	public JButton option4=new JButton(); //选项D
	public JPanel option1s =new JPanel();
	public JPanel option2s =new JPanel();
	public JPanel option3s =new JPanel();
	public JPanel option4s =new JPanel();
	public Box Pselect=Box.createVerticalBox();
	//填空模式	
	public JTextField spell=new JTextField(""); //拼写框
	public JPanel spells = new JPanel();
	public JButton spellc=new JButton("OK"); //拼写确认框
	public JPanel spellcs = new JPanel();
	public Box Pspell=Box.createVerticalBox(); //拼写主
	ImageIcon img = new ImageIcon("play.jpg");//图片的路径不正确
	JButton pron=new JButton(img);
	int num=0;
	//expression
	public JTextField Chinese =new JTextField(); //
	public JTextField Blank =new JTextField("   "); 
	public JTextField English =new JTextField(); 
	public JButton Next=new JButton("Next");
	public JButton Return=new JButton("Return");
	public JTextField Sentence =new JTextField(); //可扩展为数组
	public JPanel PEng=new JPanel(); //title
	public JPanel PChin=new JPanel(); //title
	public JPanel PSen=new JPanel(); //title
	public JPanel PNext=new JPanel(); //title
	java.util.Timer timer = new java.util.Timer();								//设置倒计时
	private Player player; //音乐播放器			
	
	
	public void Display(int sec){													//更改倒计时显示的时间
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
		//回忆界面
		title=new JTextField(nowq.title); //
		title.setFont(new Font("Buxton Sketch", 80, 80));
		title.setBorder(null);
		title.setEditable(false);
		title.setHorizontalAlignment(JTextField.CENTER);	
		Ptitle.add(title);
		
		counter.setBackground(null);												//设置倒计时的规格
		counter.setContentAreaFilled(false);
		counter.setFont(new Font("Buxton Sketch", 20, 20));
		counter.setSize(150, 75);
		
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
		box.add(Poption); //左右
		box.add(Pcontent); //左右+上下
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
		
		ImageIcon img = new ImageIcon("play.jpg");//图片的路径不正确
		img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	    pron=new JButton(img);
		pron.setContentAreaFilled(false);
		pron.setBorderPainted(false);
		pron.setBorder(BorderFactory.createRaisedBevelBorder()); 
		Ptitle.add(pron);
		Poption.add(dontknow);
		counter.setBackground(null);												//设置倒计时的规格
		counter.setContentAreaFilled(false);
		counter.setFont(new Font("Buxton Sketch", 20, 20));
		counter.setSize(150, 75);
		
		
		Poption.add(counter);
		Pcontent.setLayout(new BorderLayout());
		
		option1=new JButton(nowq.choices[0]); //选项A		
		option2=new JButton(nowq.choices[1]); //选项B
		option3=new JButton(nowq.choices[2]); //选项C
		option4=new JButton(nowq.choices[3]); //选项D
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
		box.add(Poption); //左右
		box.add(Pcontent); //左右+上下
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		setVisible(true);
		pron.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("start");
				Play("song.mp3");
				System.out.println("end");
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
		
		ImageIcon img = new ImageIcon("play.jpg");//图片的路径不正确
		img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		pron=new JButton(img);
		pron.setContentAreaFilled(false);
		pron.setBorderPainted(false);
		pron.setBorder(BorderFactory.createRaisedBevelBorder()); 
		Ptitle.add(pron);
		Poption.add(dontknow);
		
		counter.setBackground(null);												//设置倒计时的规格
		counter.setContentAreaFilled(false);
		counter.setFont(new Font("Buxton Sketch", 20, 20));
		counter.setSize(150, 75);
		
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
		PNext.add(Return);
		Pspell.add(PNext);
		
		Pcontent.add(Pspell);
		
		box.add(Ptitle);
		box.add(Poption); //左右
		box.add(Pcontent); //左右+上下
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		setVisible(true);
		spell.requestFocus();  //设置光标位置
		pron.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("start");
				Play("song.mp3");
				System.out.println("end");
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
		if(assured==false) {
			nowq=getNewQ();
		}
		if (nowq!=null){
			if(nowq.needAssure==true && assured==false){
				Assure();
				timer = new java.util.Timer();	
				timer.schedule(new TimerTask(){												//倒计时的时间10s
					int x = 10;
					public void run(){														//每经过1s，则更新计时器显示
						Display(x);
						x--;
						if(x == -1){														//时间耗尽，则停止计时器的工作,自动判断错误
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
				if(nowq.isSpellTest==true){
					Spell();
				}
				else{
					Choice();
				}
				
				timer = new java.util.Timer();	
				timer.schedule(new TimerTask(){								//倒计时的时间15s
					int x = 15;
					public void run(){										//每经过1s，则更新计时器显示
						Display(x);
						x--;
						if(x == -1){									  //时间耗尽，则停止计时器的工作,自动判断错误
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
			//析构此界面跳到生词表界面
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
		
		ImageIcon img = new ImageIcon("F:\\workspace\\Study\\src\\play.jpg");//图片的路径不正确
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
				System.out.println("baaa");
				Play("song.mp3");
				System.out.println("aaa");
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
	
	public Study_Panel(Database b,String ln,int n){
		base=b;list_name=ln;numq=n;
		c=new Core(b,ln,n);
		
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
				repaint();
				Expression();
			}
		});
		pron.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("start");
				Play("song.mp3");
				System.out.println("end");
			}
		});
		option1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timer.cancel();
				if(nowq.ans==0){
					nowq.obj.Correct();
					repaint();
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
				
				if(Myans.equals(nowq.choices[nowq.ans])) {
					nowq.obj.Correct();
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

}
