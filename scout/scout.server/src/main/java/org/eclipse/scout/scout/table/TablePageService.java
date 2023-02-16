package org.eclipse.scout.scout.table;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.eclipse.scout.scout.shared.table.ITablePageService;
import org.eclipse.scout.scout.shared.table.TableTablePageData;

import java.io.InputStream;
import java.util.Properties;

public class TablePageService implements ITablePageService {

  private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

  private static final String DB_USER = "postgres";

  private static final String DB_PASSWORD = "admin";


  @Override
  public TableTablePageData getTablePageData() throws Exception {
      TableTablePageData tableData = new TableTablePageData();

    String username = "api";
    String hostname = "116.203.205.128";
    String password = "8452";
    int port = 22;

    Session session = null;
    ChannelExec channelExec = null;
    Channel channel = null;
    Channel channel1 = null;
    JSch jSch = new JSch();
    java.util.Properties config = new Properties();
    String query = "\"INSERT INTO dev.database_table (name, surname, age) VALUES('gdfhfd', 'gdhf', 9999);\"";

    try {
      config.put("StrictHostKeyChecking", "no");
      session = jSch.getSession(username, hostname, port);
      session.setPassword(password);
      session.setConfig(config);
      session.connect();

      channel = session.openChannel("exec");
      ((ChannelExec)channel).setCommand("su root");
      channel.setInputStream(null);
      ((ChannelExec)channel).setErrStream(System.err);
      channel.connect();
      channel.disconnect();

      channel = session.openChannel("exec");
      ((ChannelExec)channel).setCommand("8452");
      channel.setInputStream(null);
      ((ChannelExec)channel).setErrStream(System.err);
      channel.connect();
      channel.disconnect();

      channel = session.openChannel("exec");
      ((ChannelExec)channel).setCommand("docker exec -it connector psql -U postgres -d db_connector -c " + query);
      channel.setInputStream(null);
      ((ChannelExec)channel).setErrStream(System.err);
      channel.connect();



      InputStream in = channel.getInputStream();
      byte[] tmp=new byte[1024];

      while(true){
        while(in.available()>0){
          int i=in.read(tmp, 0, 1024);
          if(i<0)break;
          System.out.print(new String(tmp, 0, i));
        }
        if(channel.isClosed()){
          System.out.println("exit-status: "+channel.getExitStatus());
          break;
        }
        try{Thread.sleep(1000);}catch(Exception ee){}
      }

      /*Object[][] queryResult = SQL.select("SELECT ID, Name, Surname FROM dev.database_table");*/
      /*      System.out.println(queryResult);*/

/*      try {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name, surname, age FROM dev.database_table");
        while (rs.next()){
          String name = rs.getString("name");
          String surname = rs.getString("surname");
          long id = rs.getLong("age");
        }*/
      channel.disconnect();
      session.disconnect();
      System.out.print("DONE");
    } catch (Exception e){
      e.printStackTrace();
    }


    //TODO QDL api



    return tableData;
  }

}
