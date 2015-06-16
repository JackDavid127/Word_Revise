import java.util.*;
public class Core{
	private final Database base;
	public final String list_name;
	private final int numw;
	private final int delta[]={1,1,2,3,8};
	private final String rep="______";
	public Word[] Wordlist;
	private boolean[] isok;
	private int countok;
	public Core(Database a,String ln,int b){base=a;list_name=ln;numw=b;}
	public Question getNewQ(){
		if(Wordlist==null){
			Wordlist=base.Prepare(list_name,numw);
			if(Wordlist==null){
				System.out.println("Wordlist null!");
				return null;
			}
			isok=new boolean[Wordlist.length];
			for (int i=0;i<Wordlist.length;i++) isok[i]=false;
			countok=0;
		}
		Random tmp=new Random();
		int tag,i,j;
		//System.out.println("getNewQ: length="+Wordlist.length);
		while (true){
			tag=tmp.nextInt(Wordlist.length);
			if(Wordlist[tag].phase!=4) break;
			else if(!isok[tag]){
				isok[tag]=true;countok++;
				if(countok==Wordlist.length) return null;
			}
		}
		System.out.println("tag="+tag+" name="+Wordlist[tag].name);
		if(Wordlist[tag].phase==3) return new Question(Wordlist[tag].mean,null,Wordlist[tag],0,false);
		String opt[]=new String[4];
		int ans=tmp.nextInt(4);
		int optt[]=new int[4];
		if(Wordlist[tag].phase==0) opt[ans]=Wordlist[tag].mean;
		else opt[ans]=Wordlist[tag].name;
		optt[0]=optt[1]=optt[2]=optt[3]=-1;
		optt[ans]=tag;
		//System.out.
		for (i=0;i<4;i++){
			if(i==ans) continue;
			while (true){
				j=tmp.nextInt(Wordlist.length);
				if(j!=optt[0]&&j!=optt[1]&&j!=optt[2]&&j!=optt[3]) break;
			}
			if(Wordlist[tag].phase==0) opt[i]=Wordlist[j].mean;
			else opt[i]=Wordlist[j].name;
			optt[i]=j;
		}
		if(Wordlist[tag].phase==0) return new Question(Wordlist[tag].name,opt,Wordlist[tag],ans,(Wordlist[tag].proficiency==-1||Wordlist[tag].proficiency>=2));
		else if(Wordlist[tag].phase==1) return new Question(Wordlist[tag].mean,opt,Wordlist[tag],ans,false);
		else{
			String now=Wordlist[tag].expre.replace(Wordlist[tag].name,rep);
			return new Question(now,opt,Wordlist[tag],ans,false);
		}
	}
	public Word[] getWordList(){
		int i,j,cnt=0;
		for (i=0;i<numw;i++) if(Wordlist[i].proficiency==-1) cnt++;
		Word newword[]=new Word[cnt];
		for (i=j=0;i<numw;i++) if(Wordlist[i].proficiency==-1) newword[j++]=Wordlist[i];
		return newword;
	}
	public void Update(Word[] newlist){
		int i,x=newlist.length;
		for (i=0;i<x;i++){
			Calendar tmp=Calendar.getInstance();
			tmp.setTime(new Date());
			tmp.add(Calendar.DATE,delta[newlist[i].proficiency+1]);
			base.UpdateProfic(list_name,newlist[i].name,newlist[i].proficiency+1,tmp.getTime());
		}
	}
	/*
	public static void main(String []args){
		Word a=new Word("apple","applep","applem","applee","apples",new Date(),-1);
		Word b=new Word("orange","orangep","orangem","orangee","oranges",new Date(),-1);
		Word c=new Word("banana","bananap","bananam","bananae","bananas",new Date(),-1);
		Word d=new Word("phone","phonep","phonem","phonee","phones",new Date(),-1);
		Word eee[]=new Word[4];
		eee[0]=a;eee[1]=b;eee[2]=c;eee[3]=d;
		Core att=new Core(null,null,1,eee);
		for (int i=0;i<17;i++){
			Question t=att.getNewQ();
			if(t==null) {System.out.println("null");continue;}
			System.out.println("title="+t.title+" needAssure="+t.needAssure);
			if(t.choices!=null) System.out.println("choices:"+t.choices[0]+t.choices[1]+t.choices[2]+t.choices[3]);
			t.obj.Correct();
		}
	}
	*/
}