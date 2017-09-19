package excel.excel;

public class Output {
	
	private String name;
	private String type;
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
	public String toString()
	{
		return "output: " + name + "; type: " + type;
	}
		

}
