package knockKnockBuddy;

public class KnockKnockBuddy {
	private int stage;

	public String processInput(String input) {
		if (input == null) {
			setStage(getStage() + 1);
			return "Knock knock!";
		} else if (getStage() == 1) {
			setStage(getStage() + 1);
			return "To.";
		} else if (getStage() == 2) {
			setStage(getStage() + 1);
			return "To WHOM.";
		} else {
			return "Bye!";
		}
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

}
