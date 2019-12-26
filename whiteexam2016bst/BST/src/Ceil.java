
public class Ceil {
	/**
	 * Trouve dans l'arbre le plus petit élément plus grand ou égal à value 
	 * (donc soit l'élément lui-même soit l'élément situé directement après par ordre de grandeur). 
	 * Si un tel élément n'existe pas, elle doit retourner null.
	 * 
	 * Inserez votre reponse ici
	 */
	public static Integer ceil(Node root, int value) {
		//0 element
		if(root==null)
			return null;
		//1 element
		if(root.getLeft()==null){
				if(root.getValue()<value)
					return null;
				else
					return root.getValue();
		}

		//au moins 2 elements
		Node runner=root;
		Integer best = root.getValue();
		while (true){
			if(runner.getValue()<value && runner.getRight()!=null) {
				runner = runner.getRight();
				best = minDist(best, runner.getValue(), value);
			}
			else if(runner.getValue()>value && runner.getLeft()!=null) {
				runner = runner.getLeft();
				best = minDist(best, runner.getValue(), value);
			}
			else
				break;
		}

		if(best<value)
			return null;
		return best;
    }

    private static int minDist(Integer old, Integer test, int value){
		if(old == null)
			return test;
		else if ((test<old && test-value>=0) || old-value<0)
			return test;
		return old;
	}

}
