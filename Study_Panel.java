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
	public boolean assured=false; //聽芦鈭懨掆�溾�斆︹墵惟炉鈥撯�撀库�溾�扳墹鈥毬犫��
	public Box box= Box.createVerticalBox();
	public JTextField title=new JTextField(); //
	private JButton counter = 	 new JButton();	
	public JButton dontknow=new JButton("I Don't Remember"); //鈮ぢ慌撀犫垈鈭忊垰碌鈥⒙ヂ犅Ｂ仿蔽┾�濃垜碌陋每楼脤艗脹
	public JButton donknow=new JButton("I Don't Remember"); //鈮ぢ慌撀犫垈鈭忊垰碌鈥⒙ヂ犅Ｂ仿蔽┾�濃垜碌陋每楼脤艗脹
	public JButton know =new JButton("I Remember."); //鈥斅芭撁揂
	public JTextField time=new JTextField("碌蟺潞鈭喡犅�"); //碌蟺潞鈭喡犅甭奥⒙ヅ捖犓澛Ｃ�
	public JPanel Ptitle=new JPanel(); //title
	public JPanel Poption=new JPanel(); //option
	public JPanel Pcontent=new JPanel(); //鈥斅芭撁�+脮潞
	//鈥斅扳�樏捚捖Ｂ犖�
	public JButton option1=new JButton(); //鈥斅芭撁揂
	public JButton option2=new JButton(); //鈥斅芭撁揃
	public JButton option3=new JButton(); //鈥斅芭撁揅
	public JButton option4=new JButton(); //鈥斅芭撁揇
	public JPanel option1s =new JPanel();
	public JPanel option2s =new JPanel();
	public JPanel option3s =new JPanel();
	public JPanel option4s =new JPanel();
	public Box Pselect=Box.createVerticalBox();
	//脙脫酶鈥櫰捖Ｂ犖�	
	public JTextField spell=new JTextField(""); //鈭喡モ�撀ッ该�
	public JPanel spells = new JPanel();
	public JButton spellc=new JButton("OK"); //鈭喡モ�撀ヂ烩垜禄艙酶脷
	public JPanel spellcs = new JPanel();
	public Box Pspell=Box.createVerticalBox(); //鈭喡モ�撀ッ匪�
	ImageIcon img = new ImageIcon("play.jpg");//脮潞鈭喡灯捖垜忙鈭傗墹陋鈥櫵澛烩垜
	JButton pron=new JButton(img);
	int num=0;
	//expression
	public JTextField Chinese =new JTextField(); //
	public JTextField Blank =new JTextField("   "); 
	public JTextField English =new JTextField(); 
	public JButton Next=new JButton("Next");
	public JButton Return=new JButton("Return");
	public JTextField Sentence =new JTextField(); //酶鈥β柯┾�櫹�艗鈩⒙犓濃棅脠
	public JPanel PEng=new JPanel(); //title
	public JPanel PChin=new JPanel(); //title
	public JPanel PSen=new JPanel(); //title
	public JPanel PNext=new JPanel(); //title
	private String audio_path;
	java.util.Timer timer = new java.util.Timer();								//鈥γ嬅封垰碌蟺潞鈭喡犅�
	private Player player; //鈥溍櫬棵封墹鈥⑩垜鈮堚垎藴			
	
	
	public void Display(int sec){													//鈭徛糕垙茠碌蟺潞鈭喡犅迸撯�樎犆β灯捖犅甭衡��
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
		//陋每鈥溾�拔┟佲垰脢
		title=new JTextField(nowq.title); //
		title.setFont(new Font("Buxton Sketch", 80, 80));
		title.setBorder(null);
		title.setEditable(false);
		title.setHorizontalAlignment(JTextField.CENTER);
		Ptitle.add(title);
		
		
		counter.setBackground(null);												//鈥γ嬅封垰碌蟺潞鈭喡犅甭灯捪�脢鈭徝�
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
		box.add(Poption); //鈼娒涒�濃��
		box.add(Pcontent); //鈼娒涒�濃��+鈥ε撆撀�
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
		
		ImageIcon img = new ImageIcon("play.jpg");//脮潞鈭喡灯捖垜忙鈭傗墹陋鈥櫵澛烩垜
		img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	    pron=new JButton(img);
		pron.setContentAreaFilled(false);
		pron.setBorderPainted(false);
		pron.setBorder(BorderFactory.createRaisedBevelBorder()); 
		Ptitle.add(pron);
		Poption.add(dontknow);
		counter.setBackground(null);												//鈥γ嬅封垰碌蟺潞鈭喡犅甭灯捪�脢鈭徝�
		counter.setContentAreaFilled(false);
		counter.setFont(new Font("Buxton Sketch", 20, 20));
		counter.setSize(150, 75);
		
		Poption.add(counter);
		Pcontent.setLayout(new BorderLayout());
		
		option1=new JButton(nowq.choices[0]); //鈥斅芭撁揂		
		option2=new JButton(nowq.choices[1]); //鈥斅芭撁揃
		option3=new JButton(nowq.choices[2]); //鈥斅芭撁揅
		option4=new JButton(nowq.choices[3]); //鈥斅芭撁揇
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
		box.add(Poption); //鈼娒涒�濃��
		box.add(Pcontent); //鈼娒涒�濃��+鈥ε撆撀�
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		setVisible(true);
		
		
	}
	public void Spell(){
		title=new JTextField(nowq.title); //
		title.setFont(new Font("Buxton Sketch", 80, 80));
		title.setBorder(null);
		title.setEditable(false);
		title.setHorizontalAlignment(JTextField.CENTER);	
		Ptitle.add(title);
		
		ImageIcon img = new ImageIcon("play.jpg");//脮潞鈭喡灯捖垜忙鈭傗墹陋鈥櫵澛烩垜
		img.setImage(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		pron=new JButton(img);
		pron.setContentAreaFilled(false);
		pron.setBorderPainted(false);
		pron.setBorder(BorderFactory.createRaisedBevelBorder()); 
		Ptitle.add(pron);
		Poption.add(dontknow);
		
		counter.setBackground(null);												//鈥γ嬅封垰碌蟺潞鈭喡犅甭灯捪�脢鈭徝�
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
		//Return.setPreferredSize(new Dimension(100,50)); 
		PNext.add(Return);
		Pspell.add(PNext);
	
		Pcontent.add(Pspell);
		box.add(Ptitle);
		box.add(Poption); //鈼娒涒�濃��
		box.add(Pcontent); //鈼娒涒�濃��+鈥ε撆撀�
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		setVisible(true);
		spell.requestFocus();  //鈥γ嬅封垰蟺鈥毬泵嵟捖封垰
		
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
			nowq=c.getNewQ();
		}
		if (nowq!=null){
			if(nowq.needAssure==true && assured==false){
				Assure();
				timer = new java.util.Timer();	
				timer.schedule(new TimerTask(){												//碌蟺潞鈭喡犅甭灯捖犅甭衡��10s
					int x = 10;
					public void run(){														//鈭毭该︹墵蟺藵1s拢篓鈥樏氣垙赂鈥撀衡垎聽卤鈭喫溑撯�樎犆�
						Display(x);
						x--;
						if(x == -1){														//聽卤潞鈥扳埆茠忙掳拢篓鈥樏毭暵Ｃ废�潞鈭喡犅扁垎藴碌茠蟺搂鈼娝�,鈼娾�樷垈脴鈮堚�撯垈艙楼脤艗脹
							cancel();
							nowq.obj.Incorrect();
							num++;
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
				timer.schedule(new TimerTask(){												//碌蟺潞鈭喡犅甭灯捖犅甭衡��10s
					int x = 15;
					public void run(){														//鈭毭该︹墵蟺藵1s拢篓鈥樏氣垙赂鈥撀衡垎聽卤鈭喫溑撯�樎犆�
						Display(x);
						x--;
						if(x == -1){														//聽卤潞鈥扳埆茠忙掳拢篓鈥樏毭暵Ｃ废�潞鈭喡犅扁垎藴碌茠蟺搂鈼娝�,鈼娾�樷垈脴鈮堚�撯垈艙楼脤艗脹
							cancel();
							nowq.obj.Incorrect();
							num++;
							setVisible(false);
							Expression();
							
						}
					}
				},0,1000);
				
			}
			
		}
		else{
			//艗藛蟺蟺楼脌惟脕鈭毭娒兟滴┾�λ櫬ヂ犅泵屛┟佲垰脢
			//Word[] WordHead = getWordList();
			Word[] WordHead={new Word("aaa","bbb","aaa","aaa","aaa",new Date(),1)};
			//dispose();
			System.out.println("end");
			int n=1;
			//repaint();
			Final_Panel final_panel = new Final_Panel(WordHead,n);
			getContentPane().add(final_panel, BorderLayout.CENTER);
			setVisible(true);
			System.out.println("aaa");
			
			
		}
		
		
		
		donknow.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				timer.cancel();
				nowq.obj.Incorrect();
				num++;
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
				num++;
				repaint();
				Expression();
			}
		});
		pron.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				audio_path = "./audio/";
				Play(audio_path+nowq.title+".mp3");
			}
		});
		option1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timer.cancel();
					System.out.println("ok");
				if(nowq.ans==0){
					nowq.obj.Correct();
					//JOptionPane.showMessageDialog(null,"楼铮库垈鈥樎∶�拢掳");
					//getContentPane().remove(box);
					num++;
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
					num++;
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
		//English.setBackground(Color.);
		//PEng.setBackground(Color.);
		
		ImageIcon img = new ImageIcon("play.jpg");//脮潞鈭喡灯捖垜忙鈭傗墹陋鈥櫵澛烩垜
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
	
   public Study_Panel(Database b,String ln,int n){
		base=b;list_name=ln;numq=n;
		c=new Core(b,ln,n);	
		Start();	
	}
	class Final_Panel extends Panel{
		private Box north_box = Box.createVerticalBox();
		private JTextField title = new JTextField("Congratulations! You've finished task today!");
		private JTextField note = new JTextField("          Please choose words you want to revise again:");
		private ScrollPane list_pane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
		private Box list_box = Box.createVerticalBox();
		private Box east_box = Box.createVerticalBox();
		private Box west_box = Box.createHorizontalBox();
		private JButton submit_button = new JButton("Submit");
		private Box south_box = Box.createHorizontalBox();
		private JTextField continue_text = new JTextField("          Do you want to continue?");
		private JButton yes_button = new JButton("Yes");
		private JButton no_button = new JButton("No");
		
		Final_Panel(Word[] final_words, int n){
			this.setLayout(new BorderLayout());
			//this.setBackground(Color.);
			title.setFont(new Font("Buxton Sketch",30,30));
			title.setBorder(null);
			title.setEditable(false);
			title.setHorizontalAlignment(JTextField.CENTER);
			//title.setBackground(Color.);
			
			note.setFont(new Font("Buxton Sketch",16,16));
			note.setBorder(null);
			note.setEditable(false);
			note.setHorizontalAlignment(JTextField.LEFT);
			//note.setBackground(Color.);
			
			//north_box.setBackground(Color.);
			north_box.add(Box.createVerticalStrut(20));
			north_box.add(title);
			north_box.add(Box.createVerticalStrut(20));
			north_box.add(note);
		    this.add(north_box, BorderLayout.NORTH);
		    
		    east_box.add(Box.createRigidArea(new Dimension(200, 200)));
		    east_box.add(submit_button);
		    this.add(east_box, BorderLayout.EAST);
		    
		    west_box.add(Box.createHorizontalStrut(50));
		    this.add(west_box, BorderLayout.WEST);
		    
		    continue_text.setFont(new Font("Buxton Sketch",16,16));
		    continue_text.setBorder(null);
		    continue_text.setEditable(false);
		    continue_text.setHorizontalAlignment(JTextField.LEFT);
		    //continue_text.setBackground(Color.);
			south_box.add(continue_text);
		    south_box.add(yes_button);
		    south_box.add(Box.createRigidArea(new Dimension(100, 100)));
		    south_box.add(no_button);
		    south_box.add(Box.createHorizontalStrut(200));
		    this.add(south_box, BorderLayout.SOUTH);
		    
		    for(int i = 0; i < n; i++)
		    {
		    	if(final_words[i].wrongCount != 0)
		    	    list_box.add(new Checkbox(final_words[i].name, true));
		    	else
		    		 list_box.add(new Checkbox(final_words[i].name, false));
		    }
		    
		    list_pane.add(list_box);
		    this.add(list_pane, BorderLayout.CENTER);
		    
		    submit_button.addActionListener(new ActionListener(){
				@Override
			    public void actionPerformed(ActionEvent e){
					
				}
			});
		    yes_button.addActionListener(new ActionListener(){
				@Override
			    public void actionPerformed(ActionEvent e){
					dispose();
					new Word_Frame();
				}
			});
		    no_button.addActionListener(new ActionListener(){
				@Override
			    public void actionPerformed(ActionEvent e){
					System.exit(0);
				}
			});
		}
	}

	/*public static void main(String []args){
		//Question q
		Study_Panel gui=new Study_Panel();
		gui.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);//Quit the application
			}
		});
	}*/
}