package input.manage;

public class InputSystemFactory {

	public InputSystem getInputSystem(String type) {
		if(type == null) {
			return null;
		}
		
		if(type.equals("Text")) {
			return new InputSystemTxt();
		}
		
		if(type.equals("Tex")) {
			return new InputSystemLatex();
		}
		
		return null;
	}
}
