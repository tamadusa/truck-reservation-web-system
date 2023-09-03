package service;

import java.util.List;

import entity.Authority;
import entity.Enterprise;
import entity.Reservation;
import entity.TimePeriod;
import entity.User;
import repository.Dao;

public class Service {
	
	Dao dao = new Dao();
	
	public List<TimePeriod> getTimePeriodAll(){
		return dao.getTimePeriodAll();
	}
	
	
	public List<Authority> getAuthorityAll(){
		return dao.getAuthorityAll();
	}
	
	public Enterprise getEnterprise(int enterpriseId) {
		return dao.getEnterprise(enterpriseId);
	}
	
	public List<Enterprise> getEenterpriseAll() {
		return dao.getEenterpriseAll();
	}
	
	public void insertEnterprise(Enterprise enterprise) {
		dao.insertEnterprise(enterprise);
	}
	
	public void updateEnterprise(Enterprise enterprise) {
		dao.updateEnterprise(enterprise);
	}
	
	public void deleteEnterprise(int enterpriseId) {
		dao.deleteEnterprise(enterpriseId);
	}
	
	public User getUserAdmin(int userId) {
		return dao.getUserAdmin(userId);
	}
	
	public User getUser(int userId, String password) {
		return dao.getUser(userId, password);
	}
	
	public List<User> getUserAll(int enterpriseId) {
		return dao.getUserAll(enterpriseId);
	}
	
	public void insertUser(User user) {
		dao.insertUser(user);
	}
	
	public void updateUser(User user) {
		dao.updateUser(user);
	}
	
	public void deleteUser(int userId) {
		dao.deleteUser(userId);
	}
	
	public Reservation getReservation(int reservationId) {
		return dao.getReservation(reservationId);
	}
	
	public List<Reservation> getMyReservation(int userId, int fieldEnterpriseId, String reservationDay){
		return dao.getMyReservation(userId,fieldEnterpriseId,reservationDay);
	}
	
	public List<Reservation> getReservationAll(int fieldEnterpriseId, String reservationDay){
		return dao.getReservationAll(fieldEnterpriseId,reservationDay);
	}
	
	public void insertReservation(Reservation reservation) {
		dao.insertReservation(reservation);
	}
	
	public void updateReservation(Reservation reservation) {
		dao.updateReservation(reservation);
	}
	
	public void deleteReservation(int reservationId) {
		dao.deleteReservation(reservationId);
	}

}
