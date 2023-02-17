package org.eclipse.scout.scout.table;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.eclipse.scout.scout.server.parameter.AbstractTablePageService;
import org.eclipse.scout.scout.shared.table.ITablePageService;
import org.eclipse.scout.scout.shared.table.TableTablePageData;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class TablePageService extends AbstractTablePageService implements ITablePageService {

  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TablePageService.class);

  private static final String DB_URL = "jdbc:postgresql://localhost:5432/connector";

  private static final String DB_USER = "postgres";

  private static final String DB_PASSWORD = "admin";

  private static Long id;

  private static String name;

  private static String surname;

  private static long age;

  private static long maxRowCount;

  @Override
  public TableTablePageData getTablePageData() throws Exception {
      TableTablePageData pageData = new TableTablePageData();



      try {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        LOG.info("Connected to Database!");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select id, name, surname, age from dev.database_table dt2 order by id desc;");
        LOG.info("Statement executed!");
        while (rs.next()) {
          TableTablePageData.TableTableRowData row = pageData.createRow();
          id = rs.getLong("id");
          name = rs.getString("name");
          surname = rs.getString("surname");
          age = rs.getLong("age");


          LOG.info("Checking if pageData has over: " + maxRowCount + " Rows, with Logger" + LOG);
          if (!isRowCountOverMaxRowCount(pageData)) {
            LOG.info("PageData has under: " + maxRowCount + " Rows! INSERTING!");
            if (id != null) {
              row.setIdNr(id);
              row.setName(name);
              row.setSurname(surname);
              row.setAge(age);
              LOG.info("Setting Data to Row:" + " ID: " + id + " Name: " + name + " Surname: " + surname + " Age: " + age);

              pageData.addRow(row);
              LOG.info("Row addet to Pagedata!" + " Pagedata: " + pageData + " RowIndex: " + row);
            }
          }
          else {
            LOG.info("PageData has over: " + maxRowCount + " Row! NOT INSERTING!");
            break;
          }
        }
      }catch (Exception e){}



    //TODO QDL api



    return checkLimited(pageData);
  }

  @Override
  public long getMaxRowCount() {
    return setMaxRowCount();
  }

  private static boolean isRowCountOverMaxRowCount(TableTablePageData pageData){
    if (pageData.getRowCount() > maxRowCount){
      return true;
    } else return false;
  }

  public long setMaxRowCount(){
    maxRowCount = 1000;
    return maxRowCount;
  }

  private static void  getConsoleLog(InputStream in, Channel channel) throws IOException, InterruptedException {
    byte[] tmp=new byte[1024];

    while(channel.isConnected()){
      while(in.available()>0){
        int i=in.read(tmp, 0, 1024);
        if(i<0)break;
        System.out.print(new String(tmp, 0, i));
      }
      if(channel.isClosed()){
        System.out.println("exit-status: "+channel.getExitStatus());
        break;
      } else {
        Thread.sleep(1000);
        break;
      }
    }
  }

  public static void tryToDoJSchConn(){

    String username = "api";
    String hostname = "116.203.205.128";
    String password = "8452";
    int port = 22;


    Session session = null;
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

/*      channel = session.openChannel("exec");
      ((ChannelExec)channel).setCommand("su root");
      channel.setInputStream(null);
      ((ChannelExec)channel).setErrStream(System.err);
      channel.connect();*/

      channel = session.openChannel("exec");
      ((ChannelExec)channel).setCommand("su root \n");
      channel.setInputStream(null);
      ((ChannelExec)channel).setErrStream(System.err);
      channel.connect();

      getConsoleLog(channel.getInputStream(), channel);
      Thread.sleep(1000);

      ((ChannelExec)channel).setCommand("8452");
      getConsoleLog(channel.getInputStream(), channel);

      channel = session.openChannel("exec");
      ((ChannelExec)channel).setCommand("mkdir test");
      channel.setInputStream(null);
      ((ChannelExec)channel).setErrStream(System.err);
      channel.connect();

      channel = session.openChannel("exec");
      ((ChannelExec)channel).setCommand("mkdir test2");
      channel.setInputStream(null);
      ((ChannelExec)channel).setErrStream(System.err);
      channel.connect();

      channel.disconnect();

      channel.disconnect();
      session.disconnect();
      System.out.print("DONE");
    } catch (Exception e){
      e.printStackTrace();
    }

  }

}
