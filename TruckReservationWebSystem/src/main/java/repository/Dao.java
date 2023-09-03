package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entity.Authority;
import entity.Enterprise;
import entity.Reservation;
import entity.TimePeriod;
import entity.User;

public class Dao {

	String jdbcUrl = "jdbc:mysql://localhost:3306/truckreservationsystem?characterEncoding=utf8";
	String user = "root";
	String pass = "3001";

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	private void initialize() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl,user,pass);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void terminate() {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		if(ps != null) {
			try {
				ps.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		if(con != null) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public User getUser(int userId, String password) {
		
		initialize();
		User user = new User();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" user.userId,user.userName,user.password,user.userNumber,user.userEmail,user.enterpriseId,user.authorityId,");
			buf.append(" enterprise.enterpriseName,enterprise.enterpriseNumber,enterprise.enterpriseEmail,authority.authority ");
			buf.append(" FROM ");
			buf.append(" user INNER JOIN enterprise ON user.enterpriseId = enterprise.enterpriseId ");
			buf.append(" INNER JOIN authority ON user.authorityId = authority.authorityId ");
			buf.append(" WHERE ");
			buf.append(" user.userId ");
			buf.append(" = ");
			buf.append(" ? ");
			buf.append(" AND ");
			buf.append(" user.password ");
			buf.append(" = ");
			buf.append(" ? ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, userId);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			Enterprise enterprise = new Enterprise();
			
			enterprise.setEnterpriseId(rs.getInt("user.enterpriseId"));
			enterprise.setEnterpriseName(rs.getString("enterprise.enterpriseName"));
			enterprise.setEnterpriseNumber(rs.getString("enterprise.enterpriseNumber"));
			enterprise.setEnterpriseEmail(rs.getString("enterprise.enterpriseEmail"));
			
			Authority authority = new Authority();
			
			authority.setAuthorityId(rs.getInt("user.authorityId"));
			authority.setAuthorityName(rs.getString("authority.authority"));
			
			user.setUserId(rs.getInt("user.userId"));
			user.setUserName(rs.getString("user.userName"));
			user.setPassword(rs.getString("user.password"));
			user.setUserNumber(rs.getString("user.userNumber"));
			user.setUserEmail(rs.getString("user.userEmail"));
			user.setEnterpriseId(rs.getInt("user.enterpriseId"));
			user.setAuthorityId(rs.getInt("user.authorityId"));
			
			user.setEnterprise(enterprise);
			user.setAuthority(authority);
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return user;
	}
	
	
	public User getUserAdmin(int userId) {
		
		initialize();
		User user = new User();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" user.userId,user.userName,user.password,user.userNumber,user.userEmail,user.enterpriseId,user.authorityId,");
			buf.append(" enterprise.enterpriseName,enterprise.enterpriseNumber,enterprise.enterpriseEmail,authority.authority ");
			buf.append(" FROM ");
			buf.append(" user INNER JOIN enterprise ON user.enterpriseId = enterprise.enterpriseId ");
			buf.append(" INNER JOIN authority ON user.authorityId = authority.authorityId ");
			buf.append(" WHERE ");
			buf.append(" user.userId ");
			buf.append(" = ");
			buf.append(" ? ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			Enterprise enterprise = new Enterprise();
			
			enterprise.setEnterpriseId(rs.getInt("user.enterpriseId"));
			enterprise.setEnterpriseName(rs.getString("enterprise.enterpriseName"));
			enterprise.setEnterpriseNumber(rs.getString("enterprise.enterpriseNumber"));
			enterprise.setEnterpriseEmail(rs.getString("enterprise.enterpriseEmail"));
			
			Authority authority = new Authority();
			
			authority.setAuthorityId(rs.getInt("user.authorityId"));
			authority.setAuthorityName(rs.getString("authority.authority"));
			
			user.setUserId(rs.getInt("user.userId"));
			user.setUserName(rs.getString("user.userName"));
			user.setPassword(rs.getString("user.password"));
			user.setUserNumber(rs.getString("user.userNumber"));
			user.setUserEmail(rs.getString("user.userEmail"));
			user.setEnterpriseId(rs.getInt("user.enterpriseId"));
			user.setAuthorityId(rs.getInt("user.authorityId"));
			
			user.setEnterprise(enterprise);
			user.setAuthority(authority);
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return user;
		
	}
	
	
	public List<Enterprise> getEenterpriseAll(){
		
		initialize();
		List<Enterprise> enterpriseList = new ArrayList<>();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" enterpriseId ,");
			buf.append(" enterpriseName ,");
			buf.append(" enterpriseNumber ,");
			buf.append(" enterpriseEmail ");
			buf.append(" FROM ");
			buf.append(" enterprise ");
			
			ps = con.prepareStatement(buf.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Enterprise enterprise = new Enterprise();
				enterprise.setEnterpriseId(rs.getInt("enterpriseId"));
				enterprise.setEnterpriseName(rs.getString("enterpriseName"));
				enterprise.setEnterpriseNumber(rs.getString("enterpriseNumber"));
				enterprise.setEnterpriseEmail(rs.getString("enterpriseEmail"));
				enterpriseList.add(enterprise);
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return enterpriseList;
	}
	
	
	public List<Authority> getAuthorityAll(){
		
		initialize();
		List<Authority> authorityList = new ArrayList<>();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" authorityId ,");
			buf.append(" authority ");
			buf.append(" FROM ");
			buf.append(" authority ");
			
			ps = con.prepareStatement(buf.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Authority authority = new Authority();
				authority.setAuthorityId(rs.getInt("authorityId"));
				authority.setAuthorityName(rs.getString("authority"));
				authorityList.add(authority);
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return authorityList;
	}
	
	
	public List<User> getUserAll(int enterpriseId){
		
		initialize();
		List<User> userList = new ArrayList<>();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" user.userId,user.userName,user.password,user.userNumber,user.userEmail,user.enterpriseId,user.authorityId,");
			buf.append(" enterprise.enterpriseName,enterprise.enterpriseNumber,enterprise.enterpriseEmail,authority.authority ");
			buf.append(" FROM ");
			buf.append(" user INNER JOIN enterprise ON user.enterpriseId = enterprise.enterpriseId ");
			buf.append(" INNER JOIN authority ON user.authorityId = authority.authorityId ");
			buf.append(" WHERE ");
			buf.append(" user.enterpriseId ");
			buf.append(" = ");
			buf.append(" ? ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, enterpriseId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Enterprise enterprise = new Enterprise();
				
				enterprise.setEnterpriseId(rs.getInt("user.enterpriseId"));
				enterprise.setEnterpriseName(rs.getString("enterprise.enterpriseName"));
				enterprise.setEnterpriseNumber(rs.getString("enterprise.enterpriseNumber"));
				enterprise.setEnterpriseEmail(rs.getString("enterprise.enterpriseEmail"));
				
				Authority authority = new Authority();
				
				authority.setAuthorityId(rs.getInt("user.authorityId"));
				authority.setAuthorityName(rs.getString("authority.authority"));
				
				User user = new User();
				
				user.setUserId(rs.getInt("user.userId"));
				user.setUserName(rs.getString("user.userName"));
				user.setPassword(rs.getString("user.password"));
				user.setUserNumber(rs.getString("user.userNumber"));
				user.setUserEmail(rs.getString("user.userEmail"));
				user.setEnterpriseId(rs.getInt("user.enterpriseId"));
				user.setAuthorityId(rs.getInt("user.authorityId"));
				
				user.setEnterprise(enterprise);
				user.setAuthority(authority);
				userList.add(user);
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return userList;
		
	}
	
	
	public Enterprise getEnterprise(int enterpriseId){
		
		initialize();
		Enterprise enterprise = new Enterprise();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" enterpriseId ,");
			buf.append(" enterpriseName ,");
			buf.append(" enterpriseNumber ,");
			buf.append(" enterpriseEmail ");
			buf.append(" FROM ");
			buf.append(" enterprise ");
			buf.append(" WHERE ");
			buf.append(" enterpriseId ");
			buf.append(" = ");
			buf.append(" ? ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, enterpriseId);
			
			rs = ps.executeQuery();
			
			rs.next();
			enterprise.setEnterpriseId(rs.getInt("enterpriseId"));
			enterprise.setEnterpriseName(rs.getString("enterpriseName"));
			enterprise.setEnterpriseNumber(rs.getString("enterpriseNumber"));
			enterprise.setEnterpriseEmail(rs.getString("enterpriseEmail"));
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return enterprise;
	}
	
	
	public void insertEnterprise(Enterprise enterprise) {
		
		initialize();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT ");
			buf.append(" INTO ");
			buf.append(" enterprise ");
			buf.append(" ( ");
			buf.append(" enterpriseName ,");
			buf.append(" enterpriseNumber ,");
			buf.append(" enterpriseEmail ");
			buf.append(" ) ");
			buf.append(" VALUES ");
			buf.append(" ( ");
			buf.append(" ? , ");
			buf.append(" ? , ");
			buf.append(" ?  ");
			buf.append(" ) ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setString(1, enterprise.getEnterpriseName());
			ps.setString(2, enterprise.getEnterpriseNumber());
			ps.setString(3, enterprise.getEnterpriseEmail());
			
			ps.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
	}
	
	
	public void updateEnterprise(Enterprise enterprise) {

		initialize();

		try {

			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE ");
			buf.append(" enterprise ");
			buf.append(" SET ");
			buf.append(" enterpriseId ");
			buf.append(" = ");
			buf.append(" ? ,");
			buf.append(" enterpriseName ");
			buf.append(" = ");
			buf.append(" ? ,");
			buf.append(" enterpriseNumber ");
			buf.append(" = ");
			buf.append(" ? ,");
			buf.append(" enterpriseEmail ");
			buf.append(" = ");
			buf.append(" ? ");
			buf.append(" WHERE ");
			buf.append(" enterpriseId ");
			buf.append(" = ");
			buf.append(" ? ");

			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, enterprise.getEnterpriseId());
			ps.setString(2, enterprise.getEnterpriseName());
			ps.setString(3, enterprise.getEnterpriseNumber());
			ps.setString(4, enterprise.getEnterpriseEmail());
			ps.setInt(5, enterprise.getEnterpriseId());

			ps.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
	}
	
	
	public void deleteEnterprise(int enterpriseId) {
		
		initialize();

		try {

			StringBuffer buf = new StringBuffer();
			buf.append(" DELETE ");
			buf.append(" FROM ");
			buf.append(" enterprise");
			buf.append(" WHERE ");
			buf.append(" enterpriseId ");
			buf.append(" = ");
			buf.append(" ? ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, enterpriseId);
			
			ps.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
	}
	
	
	public void insertUser(User user) {

		initialize();

		try {

			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT ");
			buf.append(" INTO ");
			buf.append(" user ");
			buf.append(" ( ");
			buf.append(" userName ,");
			buf.append(" password ,");
			buf.append(" userNumber ,");
			buf.append(" userEmail ,");
			buf.append(" enterpriseId ,");
			buf.append(" authorityId ");
			buf.append(" ) ");
			buf.append(" VALUES ");
			buf.append(" ( ");
			buf.append(" ? , ");
			buf.append(" ? , ");
			buf.append(" ? , ");
			buf.append(" ? , ");
			buf.append(" ? , ");
			buf.append(" ?  ");
			buf.append(" ) ");

			ps = con.prepareStatement(buf.toString());
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getUserNumber());
			ps.setString(4, user.getUserEmail());
			ps.setInt(5, user.getEnterpriseId());
			ps.setInt(6, user.getAuthorityId());

			ps.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}

	}
	
	
	public void updateUser(User user) {
		
		initialize();

		try {

			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE ");
			buf.append(" user ");
			buf.append(" SET ");
			buf.append(" userId ");
			buf.append(" = ");
			buf.append(" ? ,");
			buf.append(" userName ");
			buf.append(" = ");
			buf.append(" ? ,");
			buf.append(" password ");
			buf.append(" = ");
			buf.append(" ? , ");
			buf.append(" userNumber ");
			buf.append(" = ");
			buf.append(" ? ,");
			buf.append(" userEmail ");
			buf.append(" = ");
			buf.append(" ? , ");
			buf.append(" enterpriseId ");
			buf.append(" = ");
			buf.append(" ? , ");
			buf.append(" authorityId ");
			buf.append(" = ");
			buf.append(" ? ");
			buf.append(" WHERE ");
			buf.append(" userId ");
			buf.append(" = ");
			buf.append(" ? ");

			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getUserNumber());
			ps.setString(5, user.getUserEmail());
			ps.setInt(6, user.getEnterpriseId());
			ps.setInt(7, user.getAuthorityId());
			ps.setInt(8, user.getUserId());

			ps.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
	}
	
	
	public void deleteUser(int userId) {
		
		initialize();

		try {

			StringBuffer buf = new StringBuffer();
			buf.append(" DELETE ");
			buf.append(" FROM ");
			buf.append(" user ");
			buf.append(" WHERE ");
			buf.append(" userId ");
			buf.append(" = ");
			buf.append(" ? ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, userId);
			
			ps.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
	}
	
	
	public List<TimePeriod> getTimePeriodAll(){
		
		initialize();
		List<TimePeriod> timePeriodList = new ArrayList<>();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" timePeriodId ,");
			buf.append(" timePeriod ");
			buf.append(" FROM ");
			buf.append(" timePeriod ");
			
			ps = con.prepareStatement(buf.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				TimePeriod timePeriod = new TimePeriod();
				timePeriod.setTimePeriodId(rs.getInt("timePeriodId"));
				timePeriod.setTimePeriod(rs.getString("timePeriod"));
				timePeriodList.add(timePeriod);
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return timePeriodList;
	}
	
	
	public Reservation getReservation(int reservationId) {
		
		initialize();
		Reservation reservation = new Reservation();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" * ");
			buf.append(" FROM ");
			buf.append(" timePeriod ");
			buf.append(" INNER JOIN reservation ON ");
			buf.append(" timePeriod.timePeriodId ");
			buf.append(" = ");
			buf.append(" reservation.timePeriodId ");
			buf.append(" INNER JOIN user ON ");
			buf.append(" reservation.userId ");
			buf.append(" = ");
			buf.append(" user.userId ");
			buf.append(" INNER JOIN enterprise ON ");
			buf.append(" user.enterpriseId ");
			buf.append(" = ");
			buf.append(" enterprise.enterpriseId ");
			buf.append(" WHERE ");
			buf.append(" reservation.reservationId ");
			buf.append(" = ");
			buf.append(" ? ");
			buf.append(" ORDER BY ");
			buf.append(" timePeriod.timeperiodId ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, reservationId);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			reservation.setReservationId(rs.getInt("reservation.reservationId"));
			reservation.setFieldEnterpriseId(rs.getInt("reservation.fieldEnterpriseId"));
			
			Date sqlDate = rs.getDate("reservation.reservationDay");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = sqlDate.toLocalDate();
			reservation.setReservationDay(localDate.format(formatter1));
			
			Timestamp timestamp = rs.getTimestamp("reservation.registrationTime");
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime localDateTime = timestamp.toLocalDateTime();
			reservation.setRegistrationTime(localDateTime.format(formatter2));
			
			reservation.setUserId(rs.getInt("reservation.userId"));
			
			reservation.setTimePeriodId(rs.getInt("reservation.timePeriodId"));
			
			TimePeriod timePeriod = new TimePeriod();
			timePeriod.setTimePeriodId(rs.getInt("reservation.timePeriodId"));
			timePeriod.setTimePeriod(rs.getString("timePeriod.timePeriod"));
			
			reservation.setTimePeriod(timePeriod);
			
			User user = new User();
			user.setUserId(rs.getInt("user.userId"));
			user.setUserName(rs.getString("user.userName"));
			user.setPassword(rs.getString("user.password"));
			user.setUserNumber(rs.getString("user.userNumber"));
			user.setUserEmail(rs.getString("user.userEmail"));
			user.setEnterpriseId(rs.getInt("user.enterpriseId"));
			user.setAuthorityId(rs.getInt("user.authorityId"));
			
			Enterprise enterprise = new Enterprise();
			
			enterprise.setEnterpriseId(rs.getInt("user.enterpriseId"));
			enterprise.setEnterpriseName(rs.getString("enterprise.enterpriseName"));
			enterprise.setEnterpriseNumber(rs.getString("enterprise.enterpriseNumber"));
			enterprise.setEnterpriseEmail(rs.getString("enterprise.enterpriseEmail"));
			
			user.setEnterprise(enterprise);
			
			reservation.setUser(user);
			
			

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return reservation;
		
	}
	
	
	public List<Reservation> getMyReservation(int userId, int fieldEnterpriseId, String reservationDay){
		
		initialize();
		List<Reservation> reservationList = new ArrayList<>();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" * ");
			buf.append(" FROM ");
			buf.append(" timePeriod ");
			buf.append(" INNER JOIN reservation ON ");
			buf.append(" timePeriod.timePeriodId ");
			buf.append(" = ");
			buf.append(" reservation.timePeriodId ");
			buf.append(" INNER JOIN user ON ");
			buf.append(" reservation.userId ");
			buf.append(" = ");
			buf.append(" user.userId ");
			buf.append(" INNER JOIN enterprise ON ");
			buf.append(" user.enterpriseId ");
			buf.append(" = ");
			buf.append(" enterprise.enterpriseId ");
			buf.append(" WHERE ");
			buf.append(" reservation.userId ");
			buf.append(" = ");
			buf.append(" ? ");
			buf.append(" AND ");
			buf.append(" reservation.fieldEnterpriseId ");
			buf.append(" = ");
			buf.append(" ? ");
			buf.append(" AND ");
			buf.append(" reservation.reservationDay ");
			buf.append(" = ");
			buf.append(" ? ");
			buf.append(" ORDER BY ");
			buf.append(" timePeriod.timeperiodId ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, userId);
			ps.setInt(2, fieldEnterpriseId);
			ps.setString(3, reservationDay);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Reservation reservation = new Reservation();
				
				reservation.setReservationId(rs.getInt("reservation.reservationId"));
				reservation.setFieldEnterpriseId(rs.getInt("reservation.fieldEnterpriseId"));
				
				Date sqlDate = rs.getDate("reservation.reservationDay");
				DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = sqlDate.toLocalDate();
				reservation.setReservationDay(localDate.format(formatter1));
				
				Timestamp timestamp = rs.getTimestamp("reservation.registrationTime");
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime localDateTime = timestamp.toLocalDateTime();
				reservation.setRegistrationTime(localDateTime.format(formatter2));
				
				reservation.setUserId(rs.getInt("reservation.userId"));
				
				reservation.setTimePeriodId(rs.getInt("reservation.timePeriodId"));
				
				TimePeriod timePeriod = new TimePeriod();
				timePeriod.setTimePeriodId(rs.getInt("reservation.timePeriodId"));
				timePeriod.setTimePeriod(rs.getString("timePeriod.timePeriod"));
				
				reservation.setTimePeriod(timePeriod);
				
				User user = new User();
				user.setUserId(rs.getInt("user.userId"));
				user.setUserName(rs.getString("user.userName"));
				user.setPassword(rs.getString("user.password"));
				user.setUserNumber(rs.getString("user.userNumber"));
				user.setUserEmail(rs.getString("user.userEmail"));
				user.setEnterpriseId(rs.getInt("user.enterpriseId"));
				user.setAuthorityId(rs.getInt("user.authorityId"));
				
				Enterprise enterprise = new Enterprise();
				
				enterprise.setEnterpriseId(rs.getInt("user.enterpriseId"));
				enterprise.setEnterpriseName(rs.getString("enterprise.enterpriseName"));
				enterprise.setEnterpriseNumber(rs.getString("enterprise.enterpriseNumber"));
				enterprise.setEnterpriseEmail(rs.getString("enterprise.enterpriseEmail"));
				
				user.setEnterprise(enterprise);
				
				reservation.setUser(user);
				
				reservationList.add(reservation);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return reservationList;
	}
	
	
	public List<Reservation> getReservationAll(int fieldEnterpriseId, String reservationDay){
		
		
		initialize();
		List<Reservation> reservationList = new ArrayList<>();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT ");
			buf.append(" * ");
			buf.append(" FROM ");
			buf.append(" timePeriod ");
			buf.append(" INNER JOIN reservation ON ");
			buf.append(" timePeriod.timePeriodId ");
			buf.append(" = ");
			buf.append(" reservation.timePeriodId ");
			buf.append(" INNER JOIN user ON ");
			buf.append(" reservation.userId ");
			buf.append(" = ");
			buf.append(" user.userId ");
			buf.append(" INNER JOIN enterprise ON ");
			buf.append(" user.enterpriseId ");
			buf.append(" = ");
			buf.append(" enterprise.enterpriseId ");
			buf.append(" WHERE ");
			buf.append(" reservation.fieldEnterpriseId ");
			buf.append(" = ");
			buf.append(" ? ");
			buf.append(" AND ");
			buf.append(" reservation.reservationDay ");
			buf.append(" = ");
			buf.append(" ? ");
			buf.append(" ORDER BY ");
			buf.append(" timePeriod.timeperiodId ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, fieldEnterpriseId);
			ps.setString(2, reservationDay);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Reservation reservation = new Reservation();
				
				reservation.setReservationId(rs.getInt("reservation.reservationId"));
				reservation.setFieldEnterpriseId(rs.getInt("reservation.fieldEnterpriseId"));
				
				Date sqlDate = rs.getDate("reservation.reservationDay");
				DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = sqlDate.toLocalDate();
				reservation.setReservationDay(localDate.format(formatter1));
				
				Timestamp timestamp = rs.getTimestamp("reservation.registrationTime");
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime localDateTime = timestamp.toLocalDateTime();
				reservation.setRegistrationTime(localDateTime.format(formatter2));
				
				reservation.setUserId(rs.getInt("reservation.userId"));
				
				reservation.setTimePeriodId(rs.getInt("reservation.timePeriodId"));
				
				TimePeriod timePeriod = new TimePeriod();
				timePeriod.setTimePeriodId(rs.getInt("reservation.timePeriodId"));
				timePeriod.setTimePeriod(rs.getString("timePeriod.timePeriod"));
				
				reservation.setTimePeriod(timePeriod);
				
				User user = new User();
				user.setUserId(rs.getInt("user.userId"));
				user.setUserName(rs.getString("user.userName"));
				user.setPassword(rs.getString("user.password"));
				user.setUserNumber(rs.getString("user.userNumber"));
				user.setUserEmail(rs.getString("user.userEmail"));
				user.setEnterpriseId(rs.getInt("user.enterpriseId"));
				user.setAuthorityId(rs.getInt("user.authorityId"));
				
				Enterprise enterprise = new Enterprise();
				
				enterprise.setEnterpriseId(rs.getInt("user.enterpriseId"));
				enterprise.setEnterpriseName(rs.getString("enterprise.enterpriseName"));
				enterprise.setEnterpriseNumber(rs.getString("enterprise.enterpriseNumber"));
				enterprise.setEnterpriseEmail(rs.getString("enterprise.enterpriseEmail"));
				
				user.setEnterprise(enterprise);
				
				reservation.setUser(user);
				
				reservationList.add(reservation);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		return reservationList;
		
		
	}
	
	
	public void insertReservation(Reservation reservation) {
		
		
		initialize();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT ");
			buf.append(" INTO ");
			buf.append(" reservation ");
			buf.append(" ( ");
			buf.append(" fieldEnterpriseId,");
			buf.append(" reservationDay ,");
			buf.append(" registrationTime , ");
			buf.append(" userId , ");
			buf.append(" timePeriodId  ");
			buf.append(" ) ");
			buf.append(" VALUES ");
			buf.append(" ( ");
			buf.append(" ? , ");
			buf.append(" ? , ");
			buf.append(" ? , ");
			buf.append(" ? , ");
			buf.append(" ?  ");
			buf.append(" ) ");
			
			String day = reservation.getReservationDay();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(day, formatter);
			Date sqlDate = Date.valueOf(localDate);
			
			Timestamp time = new Timestamp(System.currentTimeMillis());
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, reservation.getFieldEnterpriseId());
			ps.setDate(2, sqlDate);
			ps.setTimestamp(3, time);
			ps.setInt(4, reservation.getUserId());
			ps.setInt(5, reservation.getTimePeriodId());
			
			ps.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		
		
	}
	
	
	public void updateReservation(Reservation reservation) {
		
		
		initialize();
		
		try {
			
			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE ");
			buf.append(" reservation ");
			buf.append(" SET ");
			buf.append(" reservationId = ? , ");
			buf.append(" fieldEnterpriseId = ? , ");
			buf.append(" reservationDay  = ? , ");
			buf.append(" registrationTime = ? , ");
			buf.append(" userId = ? , ");
			buf.append(" timePeriodId  = ?  ");
			buf.append(" WHERE ");
			buf.append(" reservationId = ? ");
			
			String day = reservation.getReservationDay();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(day, formatter);
			Date sqlDate = Date.valueOf(localDate);
			
			Timestamp time = new Timestamp(System.currentTimeMillis());
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, reservation.getReservationId());
			ps.setInt(2, reservation.getFieldEnterpriseId());
			ps.setDate(3, sqlDate);
			ps.setTimestamp(4, time);
			ps.setInt(5, reservation.getUserId());
			ps.setInt(6, reservation.getTimePeriodId());
			ps.setInt(7, reservation.getReservationId());
			
			ps.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		
		
	}
	
	
	public void deleteReservation(int reservationId) {
		
		initialize();

		try {

			StringBuffer buf = new StringBuffer();
			buf.append(" DELETE ");
			buf.append(" FROM ");
			buf.append(" reservation ");
			buf.append(" WHERE ");
			buf.append(" reservationId ");
			buf.append(" = ");
			buf.append(" ? ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, reservationId);
			
			ps.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			terminate();
		}
		
	}

	

}
 