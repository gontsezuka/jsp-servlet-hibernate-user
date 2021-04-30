package com.zukalover.management.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zukalover.management.model.User;
import com.zukalover.management.util.HibernateUtil;

public class UserDao {

	
	public void saveUser(User user)
	{
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		}
		catch(Exception e)
		{
			if(transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user)
	{
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			
		}catch(Exception e)
		{
			if(transaction != null)
			{
				transaction.rollback();
			}
			
			e.printStackTrace();
		}
	}
	
	public void deleteUser(Long userId)
	{
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction= session.beginTransaction();
			
			User user = session.get(User.class, userId);
			
			if(user != null)
			{
				session.delete(user);
				System.out.println("User Deleted");
			}
			
		}catch(Exception e)
		{
			if(transaction !=null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	public User getUser(Long userId)
	{
		Transaction transaction = null;
		User user = new User();
		try (Session session= HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			user=session.get(User.class, userId);
			transaction.commit();
		}catch(Exception e)
		{
			if(transaction != null)
			{
				transaction.rollback();
			}
			
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	public List<User> getAllUsers()
	{
		Transaction transaction = null;
		List<User> userList = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			userList = session.createQuery("from User").getResultList();
			transaction.commit();
			
		}catch(Exception e)
		{
			if(transaction !=null)
			{
				transaction.rollback();
			}
			
			e.printStackTrace();
		}
		
		return userList;
	}
}
