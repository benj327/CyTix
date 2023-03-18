package com.cytix.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="user_id")
        private Long id;
        
        @Column(name="user_name")
        private String userName;
        
        @Column(name="user_email")
        private String userEmail;
        
        @Column(name="account_type")
        private String accountType;
        
        @Column(name="password")
        private String password;
        
        @ManyToOne
        @JoinColumn(name = "user_id", updatable=false, insertable=false) //insertable/updatable needed? From error line
        @JsonIgnore
        private Event event;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Event getEvent() {
			return event;
		}

		public void setEvent(Event event) {
			this.event = event;
		}
        
}
