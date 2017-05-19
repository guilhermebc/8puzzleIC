import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AStar
{
	public static void search(int[] board, boolean d, char heuristic)
	{
		SearchNode root = new SearchNode(new Puzzle(board));
		Queue<SearchNode> q = new LinkedList<SearchNode>();
		q.add(root);

		int searchCount = 1;

		while (!q.isEmpty()) 
		{
			SearchNode tempNode = (SearchNode) q.poll();
			
			if (!tempNode.getCurState().isGoal())
			{
				ArrayList<State> tempSuccessors = tempNode.getCurState()
						.genSuccessors();
				ArrayList<SearchNode> nodeSuccessors = new ArrayList<SearchNode>();

				
				for (int i = 0; i < tempSuccessors.size(); i++){
					SearchNode checkedNode;
					
					if (heuristic == 'o'){						
						checkedNode = new SearchNode(tempNode, tempSuccessors.get(i), tempNode.getCost()
										+ tempSuccessors.get(i).findCost(),	((Puzzle) tempSuccessors.get(i))
										.getOutOfPlace());
					}
					else{					
						checkedNode = new SearchNode(tempNode, tempSuccessors.get(i), tempNode.getCost()
										+ tempSuccessors.get(i).findCost(), ((Puzzle) tempSuccessors.get(i))
										.getManDist());
					}
					
					if (!checkRepeats(checkedNode)){
						nodeSuccessors.add(checkedNode);
					}
				}
				
				if (nodeSuccessors.size() == 0)
					continue;

				SearchNode lowestNode = nodeSuccessors.get(0);
				
				for (int i = 0; i < nodeSuccessors.size(); i++){
					if (lowestNode.getFCost() > nodeSuccessors.get(i).getFCost()){
						lowestNode = nodeSuccessors.get(i);
					}
				}

				int lowestValue = (int) lowestNode.getFCost();
				
				for (int i = 0; i < nodeSuccessors.size(); i++){
					if (nodeSuccessors.get(i).getFCost() == lowestValue)
					{
						q.add(nodeSuccessors.get(i));
					}
				}

				searchCount++;
			}
			else{
				
				Stack<SearchNode> solutionPath = new Stack<SearchNode>();
				solutionPath.push(tempNode);
				tempNode = tempNode.getParent();

				while (tempNode.getParent() != null){
					solutionPath.push(tempNode);
					tempNode = tempNode.getParent();
				}
				solutionPath.push(tempNode);				
				int loopSize = solutionPath.size();

				for (int i = 0; i < loopSize; i++){
					tempNode = solutionPath.pop();
					tempNode.getCurState().printState();
					System.out.println();
					System.out.println();
				}
				System.out.println("Custo: " + tempNode.getCost());
				if (d){
					System.out.println("N�mero de n�s: "
							+ searchCount);
				}
				System.exit(0);
			}
		}
		System.out.println("Sol��o n�o encontrada!");
	}

	private static boolean checkRepeats(SearchNode n)
	{
		boolean retValue = false;
		SearchNode checkNode = n;

		while (n.getParent() != null && !retValue){
			if (n.getParent().getCurState().equals(checkNode.getCurState()))
			{
				retValue = true;
			}
			n = n.getParent();
		}
		return retValue;
	}
}
