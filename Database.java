import java.sql.*;
import java.text.SimpleDateFormat;
public class Database{
	/*public static void main(String args[]){
		Database db = new Database();
		db.creatNewList("step");
		Word a = new Word("happy", "hp", "love", "pleasant", "lalala", Calendar.getInstance().getTime(), 2);
		db.addNewEntry("step", a);
		db.UpdateProfic("step", "happy", 5, Calendar.getInstance().getTime());
	}*/
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	SimpleDateFormat conv=new SimpleDateFormat("yyyy-MM-dd");
	public Database(){
		  String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //鍔犺浇JDBC椹卞姩
		  String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=test";  //杩炴帴鏈嶅姟鍣ㄥ拰鏁版嵁搴搕est
		  String userName = "sa";  //榛樿鐢ㄦ埛鍚�
		  String userPwd = "899";  //瀵嗙爜
		try{
			Class.forName(driverName);
			con = DriverManager.getConnection(dbURL, userName, userPwd);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}catch(Exception e){
			System.err.println("Error occured in Constructor.");
		}
	}
	@Override
	public void finalize(){
		try{
			con.close();
		}catch(Exception e){
			System.err.println("Error occured in finalize()");
		}
	}
	public boolean isStored(String lname){
		try{
			rs=stmt.executeQuery("SELECT name FROM SysObjects Where XType='U' ORDER BY Name");
			//System.out.println("isStored: Start printing");
			while (rs.next()){
				String t=rs.getString("name");
				//System.out.println("isStored: name="+t);
				if(t.equals(lname.toLowerCase())) return true;
			}
			return false;
		}catch(Exception e){
			System.err.println("Error occured in isStored()");
			e.printStackTrace();
			return false;
		}
	}
	public Word[] Prepare(String lname,int numq){
		try{
			//rs=stmt.executeQuery("SELECT TOP "+numq+" * FROM "+lname+" WHERE nextDate <= \'"+conv.format(new java.util.Date())+"\'");
			//System.out.println("Prepare: numq="+numq);
			rs=stmt.executeQuery("SELECT TOP "+numq+" * FROM "+lname);
			rs.last();
			int nrow=rs.getRow();
			//System.out.println("Prepare: nrow="+nrow);
			Word ans[]=new Word[nrow];
			rs.first();
			for (int i=0;i<nrow;i++){
				String n=rs.getString("name");
				String p=rs.getString("pron");
				String m=rs.getString("mean");
				String e=rs.getString("expre");
				String s=rs.getString("synonym_");
				java.util.Date nd=conv.parse(rs.getString("nextDate"));
				int pr=rs.getInt("proficiency");
				ans[i]=new Word(n,p,m,e,s,nd,pr);
				rs.next();
			}
			return ans;
		}catch(Exception e){
			System.err.println("Error occured in Prepare()");
			e.printStackTrace();
			return null;
		}
	}
	public void UpdateProfic(String lname,String name,int newp,java.util.Date newdate){
		try{
			stmt.executeUpdate("UPDATE "+lname+" SET proficiency = "+newp+", nextDate = \'"+conv.format(newdate)+"\' WHERE name = \'"+name + "\'");
		}catch(Exception e){
			System.err.println("Error occured in UpdateProfic");
			e.printStackTrace();
		}
	}
	public void creatNewList(String lname){
		/*
		try{
			stmt.executeUpdate("DROP TABLE [dbo].["+lname+"]");
		}catch(Exception e){
			System.out.println("No existing table to delete");
		}
		*/
		try{
			stmt.executeUpdate("CREATE TABLE [dbo].["+lname+"]([name] NVARCHAR(20) NOT NULL PRIMARY KEY, "
								+"[pron] NVARCHAR(40) NULL, [mean] NVARCHAR(40) NULL, [expre] NTEXT NULL, "
								+"[synonym_] NVARCHAR(20) NULL, [nextDate] DATE NOT NULL, [proficiency] SMALLINT NOT NULL)");
		}catch(Exception e){
			System.err.println("Error occured in creatNewList");
		}
	}
	public void addNewEntry(String lname,Word a){
		try{
			stmt.executeUpdate("INSERT INTO "+lname+" VALUES(\'"+a.name+"\', \'"+a.pron+"\', \'"+a.mean+"\', \'"+a.expre
			+"\', \'"+a.synonym+"\', \'"+conv.format(a.nextDate)+"\', "+a.proficiency+")");
			//rs=stmt.executeQuery("SELECT * FROM "+lname);
			//rs.last();
			//System.out.println("addNewEntry: getRow()="+rs.getRow());
		}catch(Exception e){
			System.err.println("Error occured in addNewEntry(Word a)");
			e.printStackTrace();
			System.err.println("name="+a.name+" pron="+a.pron+" expre="+a.expre+" mean="+a.mean+" syn="+a.synonym);
		}
	}
}
