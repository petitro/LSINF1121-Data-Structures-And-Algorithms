import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Ceil {
	/**
	 * Trouve dans l'arbre le plus petit élément plus grand ou égal à value
	 * (donc soit l'élément lui-même soit l'élément situé directement après par ordre de grandeur).
	 * Si un tel élément n'existe pas, elle doit retourner null.
	 *
	 * Inserez votre reponse ici
	 */
	public static Integer ceil(Node root, int value) {
		//cas parfait
		if(root.getValue()==value){return value;}

		//cas cote gauche
		else if(root.getValue()>value){
			//cas limite a gauche, (dernier noeud avant la fin)
			if(root.getLeft()==null ){
				return root.getValue();
			}

			Integer answer = ceil(root.getLeft(), value);
			if(answer == null){
				return root.getValue();
			}

			//ce noeud ci n'est pas le plus optimal
			else {
				return answer;
			}
		}
		//cas cote droit
		else {
			//cas limite a droite
			if(root.getRight()==null){
				return null;
			}
			return ceil(root.getRight(), value);
		}
    }
}
