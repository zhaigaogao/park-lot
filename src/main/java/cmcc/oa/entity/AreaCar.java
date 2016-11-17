package cmcc.oa.entity;

public class AreaCar {
	private Integer id;
	
	private String ownerArea;
	
	private Integer innerArea;
	
	private Integer leaderArea;
	
	private Integer outerArea;
	
	private Integer flag;
	
	private Integer totalCar;
	
	private String message;


	public Integer getFlag() {
		return flag;
	}


	public Integer getId() {
		return id;
	}

	
	public Integer getInnerArea() {
		return innerArea;
	}


	public Integer getLeaderArea() {
		return leaderArea;
	}


	public String getMessage() {
		return message;
	}


	public Integer getOuterArea() {
		return outerArea;
	}


	public String getOwnerArea() {
		return ownerArea;
	}


	public Integer getTotalCar() {
		return totalCar;
	}


	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setInnerArea(Integer innerArea) {
		this.innerArea = innerArea;
	}


	public void setLeaderArea(Integer leaderArea) {
		this.leaderArea = leaderArea;
	}


	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}


	public void setOuterArea(Integer outerArea) {
		this.outerArea = outerArea;
	}


	public void setOwnerArea(String ownerArea) {
		this.ownerArea = ownerArea == null ? null : ownerArea.trim();
	}


	public void setTotalCar(Integer totalCar) {
		this.totalCar = totalCar;
	}


	

}
