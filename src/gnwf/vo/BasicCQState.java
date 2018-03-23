package gnwf.vo;

import java.io.Serializable;

public class BasicCQState implements Serializable{
	private static final long serialVersionUID = -838957882175515198L;
	
	public static final String SELF_FIELDS = "Statedef.Id,Statedef.Name";
	
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + "]";
	}

}
