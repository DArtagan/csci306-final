package curling;

public enum Role {
	SKIP("Skip"),
	LEAD("Lead"), 
	SECOND("Second"), 
	THIRD("Third");
	
	private final String text;
	
	private Role(String text)
	{
		this.text = text;
	}
	
	@Override
	public String toString()
	{
		return text;
	}
}
