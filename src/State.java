import java.util.ArrayList;

public interface State
{
	boolean isGoal();
	ArrayList<State> genSuccessors();
	double findCost();
	public void printState();
	public boolean equals(State s);
}
