package excel.excel;

import java.util.ArrayList;
import java.util.List;

public class ExcelRead {
	
	private String globalSheetName;
	private String group;
	private List<Input> inputs = new ArrayList<Input>();
	private List<Output> outputs = new ArrayList<Output>();
	
	
	public List<Input> getInputs() {
		return inputs;
	}
	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}
	public List<Output> getOutputs() {
		return outputs;
	}
	public void setOutputs(List<Output> outputs) {
		this.outputs = outputs;
	}
	public String getGlobalSheetName() {
		return globalSheetName;
	}
	public void setGlobalSheetName(String globalSheetName) {
		this.globalSheetName = globalSheetName;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}

}
