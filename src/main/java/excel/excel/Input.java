package excel.excel;

public class Input {
	
	private String name;
	private String type;
	private String typeAtl;
	private String limit;
	public String getName() {
		if( (name!=null) && !name.equalsIgnoreCase("") )
		{
			return name;
		}
		else
		{
			return "none";
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		if( (type!=null) && !type.equalsIgnoreCase("") )
		{
			return type;
		}
		else
		{
			return "xs:any";
		}
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(Double limit) {
		this.limit = String.valueOf( limit );
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public void setLimit(Integer limit) {
		this.limit = String.valueOf( limit );
	}
	
	public String toString()
	{
		return "input: " + name + "; type: " + type + "; limit: " + limit;
	}
	public String getTypeAtl() {
		return typeAtl;
	}
	public void setTypeAtl(String typeAtl) {
		this.typeAtl = typeAtl;
	}

	
	

}
