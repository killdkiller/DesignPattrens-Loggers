package org.epam.model;


public class PassengerDetails {
		private int adult;
		private int child;
		private int infant;
		
		public PassengerDetails()
		{
			this.adult = 1;
			this.child = 0;
			this.infant = 0;
		}
		
		public PassengerDetails withChild(int numOfChilds)
		{
			this.setChild(numOfChilds);
			return this;
		}
		
		public PassengerDetails withInfant(int numOfInfant)
		{
			this.setInfant(numOfInfant);
			return this;
		}
		
		public PassengerDetails withAdults(int numOfAdults)
		{
			this.setAdult(numOfAdults);
			return this;
		}
		
		
		public int getAdult() {
			return adult;
		}
		public void setAdult(int adult) {
			this.adult = adult;
		}
		public int getChild() {
			return child;
		}
		public void setChild(int child) {
			this.child = child;
		}
		public int getInfant() {
			return infant;
		}
		public void setInfant(int infant) {
			this.infant = infant;
		}
	}

