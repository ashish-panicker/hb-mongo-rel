package com;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Post;
import model.User;

public class Main {

	private final static String SALT = "WHUFjju77(#$kk$%#";

	private static EntityManagerFactory emf;

	public static String hashPassword(String input) throws NoSuchAlgorithmException {
		StringBuilder hash = new StringBuilder();
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		byte[] hashedBytes = sha.digest(input.getBytes());
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < hashedBytes.length; idx++) {
			byte b = hashedBytes[idx];
			hash.append(digits[(b & 0xf0) >> 4]);
			hash.append(digits[b & 0x0f]);
		}
		return hash.toString();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		emf = HibernateUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		User user = new User("ashish@gmail.com", hashPassword("password" + SALT), new Date());

		Post p1 = new Post("sample message 1", new Date(), user);
		Post p2 = new Post("sample message 2", new Date(), user);
		Post p3 = new Post("sample message 3", new Date(), user);
		
		List<Post> posts = new ArrayList<>();
		
		posts.add(p1);
		posts.add(p2);
		posts.add(p3);
		
		user.setPosts(posts);
		
//		em.persist(user);
		
		user = em.find(User.class, "6e74c124-b36a-4d01-9b2a-8fa3b6b2003c");
		posts = user.getPosts();
		
		for (Post p : posts) {
			System.out.println(p);
		}

		em.getTransaction().commit();

		System.out.println("done...");

	}

}
