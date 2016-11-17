package cmcc.oa.entity;

public class FeeScale {
	private Integer id;
	
	private Integer freeTime;
	
	private Integer chargePeriod;
	
	private Integer feePeriod;
	
	private Integer dayNight;
	
	private Integer carType;
	
	private Integer monthPay;
	
	private Integer yearPay;

	public Integer getCarType() {
		return carType;
	}

	public Integer getChargePeriod() {
		return chargePeriod;
	}

	public Integer getDayNight() {
		return dayNight;
	}

	public Integer getFeePeriod() {
		return feePeriod;
	}

	public Integer getFreeTime() {
		return freeTime;
	}

	public Integer getId() {
		return id;
	}

	public Integer getMonthPay() {
		return monthPay;
	}

	public Integer getYearPay() {
		return yearPay;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public void setChargePeriod(Integer chargePeriod) {
		this.chargePeriod = chargePeriod;
	}

	public void setDayNight(Integer dayNight) {
		this.dayNight = dayNight;
	}

	public void setFeePeriod(Integer feePeriod) {
		this.feePeriod = feePeriod;
	}

	public void setFreeTime(Integer freeTime) {
		this.freeTime = freeTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMonthPay(Integer monthPay) {
		this.monthPay = monthPay;
	}

	public void setYearPay(Integer yearPay) {
		this.yearPay = yearPay;
	}
}
