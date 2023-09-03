package entity;

public class Reservation {
	
	int reservationId;
	int fieldEnterpriseId;
	Enterprise fieldEnterprise;
	String reservationDay;
	String RegistrationTime;
	int userId;
	int timePeriodId;
	User user;
	TimePeriod timePeriod;
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getFieldEnterpriseId() {
		return fieldEnterpriseId;
	}
	public void setFieldEnterpriseId(int fieldEnterpriseId) {
		this.fieldEnterpriseId = fieldEnterpriseId;
	}
	public Enterprise getFieldEnterprise() {
		return fieldEnterprise;
	}
	public void setFieldEnterprise(Enterprise fieldEnterprise) {
		this.fieldEnterprise = fieldEnterprise;
	}
	public String getReservationDay() {
		return reservationDay;
	}
	public void setReservationDay(String reservationDay) {
		this.reservationDay = reservationDay;
	}
	public String getRegistrationTime() {
		return RegistrationTime;
	}
	public void setRegistrationTime(String registrationTime) {
		RegistrationTime = registrationTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getTimePeriodId() {
		return timePeriodId;
	}
	public void setTimePeriodId(int timePeriodId) {
		this.timePeriodId = timePeriodId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public TimePeriod getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

}
