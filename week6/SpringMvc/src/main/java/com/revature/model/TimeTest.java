package com.revature.model;

import java.time.LocalDateTime;

public class TimeTest {
	private LocalDateTime time;

	public TimeTest(LocalDateTime time) {
		super();
		this.time = time;
	}

	public TimeTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		TimeTest other = (TimeTest) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TimeTest [time=" + time + "]";
	}

}
