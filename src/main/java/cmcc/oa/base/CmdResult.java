package cmcc.oa.base;

import java.util.Date;

/**
 * 与停车场的对接的类
 * @author renlinggao
 * @Date 2016年10月28日
 */
public class CmdResult {
	private int cmdId; //操作的类型
	private String carNum;
	private boolean isIn;
	private Integer clientId;
	private String welcomeMess;
	private Date sendTime;
	
	public int getCmdId() {
		return cmdId;
	}
	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getWelcomeMess() {
		return welcomeMess;
	}
	public void setWelcomeMess(String welcomeMess) {
		this.welcomeMess = welcomeMess;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public boolean isIsIn() {
		return isIn;
	}
	public void setIsIn(boolean isIn) {
		this.isIn = isIn;
	}

}
