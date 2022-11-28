package com.gradebook.gradebook;

public class Assignment {
	private String name;
	private double pointsEarned;
	private double pointsTotal;

	public Assignment(String name, double pointsEarned, double pointsTotal) {
		this.name = name;
		this.pointsEarned = pointsEarned;
		this.pointsTotal = pointsTotal;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pointsEarned
	 */
	public double getPointsEarned() {
		return pointsEarned;
	}

	/**
	 * @param pointsEarned the pointsEarned to set
	 */
	public void setPointsEarned(double pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	/**
	 * @return the pointsTotal
	 */
	public double getPointsTotal() {
		return pointsTotal;
	}

	/**
	 * @param pointsTotal the pointsTotal to set
	 */
	public void setPointsTotal(double pointsTotal) {
		this.pointsTotal = pointsTotal;
	}

}
