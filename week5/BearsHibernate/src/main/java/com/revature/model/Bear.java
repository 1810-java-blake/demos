package com.revature.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "bears")
public class Bear {
	@Id
	@Column(name = "bear_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String color;
	private String breed;
	private int legs;

	// @ManyToOne(fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cave_id")
	private Cave cave;

	@ManyToMany
	@JoinTable(name = "parent_cub", joinColumns = @JoinColumn(name = "parent_id"), inverseJoinColumns = @JoinColumn(name = "cub_id"))
	private Set<Bear> cubs = new HashSet<>();

	public Bear() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bear(int id, String color, String breed, int legs, Cave cave, Set<Bear> cubs) {
		super();
		this.id = id;
		this.color = color;
		this.breed = breed;
		this.legs = legs;
		this.cave = cave;
		this.cubs = cubs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}

	public Cave getCave() {
		return cave;
	}

	public void setCave(Cave cave) {
		this.cave = cave;
	}

	public Set<Bear> getCubs() {
		return cubs;
	}

	public void setCubs(Set<Bear> cubs) {
		this.cubs = cubs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + id;
		result = prime * result + legs;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bear other = (Bear) obj;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id != other.id)
			return false;
		if (legs != other.legs)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bear [id=" + id + ", color=" + color + ", breed=" + breed + ", legs=" + legs + ", cave=" + cave + "]";
	}

}
