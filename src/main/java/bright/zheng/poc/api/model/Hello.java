package bright.zheng.poc.api.model;

import java.io.Serializable;

/**
 * The domain model class
 * 
 * @author bright.zheng
 *
 */
public class Hello implements Serializable {

	private static final long serialVersionUID = -4434868206171224257L;
	
	private String msg;

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}