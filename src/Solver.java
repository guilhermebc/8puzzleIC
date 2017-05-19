public class Solver
{
	public static void main(String[] args)
	{
		int searchTypeDebug = 0;
		int eightPuzzleDebug = 1;
		boolean debug = false;

		if (args.length < 1){
			printUsage();
		}
		if (args[0].equals("-d")){
			searchTypeDebug = 1;
			eightPuzzleDebug = 2;
			debug = true;
			System.out.println("Tipo de pesquisa: " + args[searchTypeDebug].toLowerCase());
		}
		String searchType = args[searchTypeDebug].toLowerCase();

		if (args.length > 2){
			int[] startingStateBoard = dispatchEightPuzzle(args,
					eightPuzzleDebug);
			if (searchType.equals("aso"))
			{
				AStar.search(startingStateBoard, debug, 'o');
			}
			else if (searchType.equals("asm"))
			{
				AStar.search(startingStateBoard, debug, 'm');
			}
			else{
				printUsage();
			}
		}
		else{		
			printUsage();			
		}
	}
	private static void printUsage(){
		System.out.println("Uso: ./Main <tipoPesquisa> [Estado inicial do puzzle]");
		System.exit(-1);
	}

	private static int[] dispatchEightPuzzle(String[] a, int d){
		int[] initState = new int[9];
		for (int i = d; i < a.length; i++)
		{
			initState[i - d] = Integer.parseInt(a[i]);
		}
		return initState;
	}
}
