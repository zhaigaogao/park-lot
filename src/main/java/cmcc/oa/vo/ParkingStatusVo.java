package cmcc.oa.vo;

/**
 * @author Administrator
 *
 */
public class ParkingStatusVo {

	/** 区域id */
	private String id;
	/* 当前已停员工车位 */
	private long employeeArea;

	/* 当前已停部门车位 */
	private long departmentArea;

	/* 当前已停访客车位 */
	private long guestArea;

	/* 当前有效的已预约车位 */
	private long areaReserved;

	/* 当前有效的已预约且已使用的车位 */
	private long areaReservedAndUsed;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getEmployeeArea() {
		return employeeArea;
	}

	public void setEmployeeArea(long employeeArea) {
		this.employeeArea = employeeArea;
	}

	public long getDepartmentArea() {
		return departmentArea;
	}

	public void setDepartmentArea(long departmentArea) {
		this.departmentArea = departmentArea;
	}

	public long getGuestArea() {
		return guestArea;
	}

	public void setGuestArea(long guestArea) {
		this.guestArea = guestArea;
	}

	public long getAreaReserved() {
		return areaReserved;
	}

	public void setAreaReserved(long areaReserved) {
		this.areaReserved = areaReserved;
	}

	public long getAreaReservedAndUsed() {
		return areaReservedAndUsed;
	}

	public void setAreaReservedAndUsed(long areaReservedAndUsed) {
		this.areaReservedAndUsed = areaReservedAndUsed;
	}

}
