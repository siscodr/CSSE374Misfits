package ClassStorage;

import java.util.ArrayList;

public class MethodFieldsStorage {
	private String name;
	private ArrayList<String> fields;

	public MethodFieldsStorage(String name, ArrayList<String> fields) {
		super();
		this.name = name;
		this.fields = fields;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getFields() {
		return fields;
	}

	public void setFields(ArrayList<String> fields) {
		this.fields = fields;
	}

}
