
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class RunnersDB implements DataSource {

	private Connection getConnection() {
		Connection connection = null;

		try {
			String dbDirectory = "Resources/RunnersDB";
			System.setProperty("derby.system.home", dbDirectory);
			String dbUrl = "jdbc:derby:RunnersDB;create=true"; // database url
			String username = ""; // database username
			String password = ""; // database password
			connection = DriverManager.getConnection(dbUrl, username, password); // connect database
			
		} catch (SQLException e) {
			System.err.println("Error loading database driver\n");
			for (Throwable t : e) {
				t.printStackTrace();
			}
		}
		return connection;
	}

	public RunnersDB() {
		  
		    try {
		      Connection con = DriverManager.getConnection
		        ("jdbc:derby:RunnersDB;create=true");  
		 
		      Statement stmt = con.createStatement();
		      stmt.executeUpdate("DROP TABLE RunnersStats");
		 
		      stmt.executeUpdate("CREATE TABLE RunnersStats (Name VARCHAR(22), RunnersSpeed DOUBLE, RestPercentage DOUBLE)");
		 
		      stmt.executeUpdate("INSERT INTO RunnersStats VALUES ('Tortoise', 10, 0), ('Hare', 100, 90), ('Dog', 50, 40), ('cat', 30, 75)");
		 		 
		    } catch(SQLException e) {
		      System.out.println("SQL exception occured" + e.toString() );
		    }
		  
		}

	public ArrayList<RunnersData> getRunnersData() {
		String sql = "SELECT Name, RunnersSpeed, RestPercentage " + "FROM RunnersStats";
		ArrayList<RunnersData> RunnersList = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				String name = rs.getString("Name");
				double speed = rs.getDouble("RunnersSpeed");
				double rest = rs.getDouble("RestPercentage");

				RunnersData runner = new RunnersData(name, speed, rest);
				RunnersList.add(runner);
			}
			rs.close();
			ps.close();
			connection.close();

			if (disconnect()) { // disconnect database
				System.out.println("Disconnected successfully from DB");
			}
			System.gc();
			return RunnersList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean disconnect() {
		try {
			String shutdownUrl = "jdbc:derby:RunnersDB;shutdown=true";
			DriverManager.getConnection(shutdownUrl);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
