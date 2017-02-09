package reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	//JDBCドライバの登録
    String driver;
    // データベースの指定
    String server, dbname, url, user, password;
    Connection con;
    Statement stmt;
    ResultSet rs;
	public MySQL() {
		this.driver  = "org.gjt.mm.mysql.Driver";
		this.server  = "ms000.sist.ac.jp";      // MySQLサーバ ( IP または ホスト名 );
		this.dbname  = "java2016";         // データベース名;
		this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
		this.user = "java2016";         // データベース作成ユーザ名;
		this.password  = "java2016";     // データベース作成ユーザパスワード;
	
		try{
			this.con = DriverManager.getConnection(url, user, password);
			this.stmt = con.createStatement ();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
		Class.forName (driver);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public void close(){
		try{
			rs.close();
			stmt.close();
			con.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public ResultSet selectAll(){
		String sql = "SELECT * FROM `50516041`";
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery (sql);  //try catchで囲む
			
		}catch(SQLException e){
			e.printStackTrace();
		
		}
		return rs;
	}
	public ResultSet selectReservation(String rdate, String facility){
		String sql = "SELECT * FROM reservation WHERE date ='" + rdate + "' AND facility_name = '"+ facility +"' ORDER BY start_time;";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery (sql);  //try catchで囲む
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet selectUser(String userid){
		String sql = "SELECT * FROM user WHERE user_id ='" + userid + "';";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery (sql);  //try catchで囲む
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public int insertReservation(String rdate, String facility, String st, String et, String userid){
		String sql = "INSERT INTO reservation (date,start_time,end_time,user_id,facility_name) VALUES ( '"
	    		  + rdate +"', '"  + st +"','" + et + "','" + userid +"','" + facility +"');";
		int rs_int = 0;
		try {
			rs_int = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs_int;
	}
	public ResultSet gaiyou(){
		ResultSet rs = null;
		String sql = "SELECT * FROM `facility`";
		try{
			rs = stmt.executeQuery (sql);  //try catchで囲む
			
		}catch(SQLException e){
			e.printStackTrace();
		
		}		
		return rs;
	}
	public ResultSet kakunin(){
		ResultSet rs = null;
		String sql = "SELECT * FROM `reservation` ";
		try{
			rs = stmt.executeQuery (sql);  //try catchで囲む
			
		}catch(SQLException e){
			e.printStackTrace();
		
		}		
		return rs;
	}
	public int deleate_Reservation(String reservation_userid){
		String sql = "DELETE FROM `reservation` WHERE user_id ='" + reservation_userid + "';";

		int resalt = 0;
		try {
			resalt = stmt.executeUpdate (sql); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resalt;	
	}
	public ResultSet getReservation_user(String reservation_userid){
		
		String sql = "SELECT * FROM reservation WHERE user_id ='" + reservation_userid + "'ORDER BY date, start_time;";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery (sql); 
		} catch (SQLException e) {
			try {
				rs = stmt.executeQuery (sql);
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} 
		}
		return rs;
		
	}
}
