package util;

import java.util.ArrayList;

import fa.Frekvenca;

/**
 * @author Gregor Panič
 *
 */
public class FAUtil {
	
	public static ArrayList<Frekvenca> getFrekvence(String s) {
		ArrayList<Frekvenca> frekvence=new ArrayList<Frekvenca>();
		
		for(int i=0;i<s.length();i++) {
			if(Character.isLetter(s.charAt(i))) {
				String c=Character.toString(s.charAt(i)).toLowerCase();
				
				boolean found=false;
				for(Frekvenca f:frekvence) {
					if(f.getCrka().equals(c)) {
						f.setStevilo(f.getStevilo()+1);
						found=true;
						break;
					}
				}
				
				if(!found) {
					frekvence.add(new Frekvenca(c, 1));
				}
			}
		}
		
		return frekvence;
	}
	
	public static String decypher(String sifrirano, ArrayList<Frekvenca> sifFrek, ArrayList<Frekvenca> refFrek) {
		char[] sifriranoChar=sifrirano.toCharArray();
		char[] desifriranoChar=new char[sifriranoChar.length];
		for(int i=0;i<sifFrek.size();i++) {
			String cypherChar=sifFrek.get(i).getCrka();
			if(refFrek.size()>i) {
				String plainChar=refFrek.get(i).getCrka();
				for(int j=0;j<sifriranoChar.length;j++) {
					if(Character.isLetter(sifriranoChar[j])) {
						if(Character.toString(sifriranoChar[j]).toLowerCase().equals(cypherChar)) {
							if(Character.isUpperCase((sifriranoChar[j]))) {
								desifriranoChar[j]=plainChar.toUpperCase().charAt(0);
							} else {
								desifriranoChar[j]=plainChar.charAt(0);
							}
						}
					} else {
						desifriranoChar[j]=sifriranoChar[j];
					}
				}
			}
		}
		return String.valueOf(desifriranoChar);
	}
	
	public static String swapCharacters(String orig, String char1, String char2) {
		char[] original=orig.toCharArray();
		char[] swapped=new char[original.length];
		
		for(int i=0;i<original.length;i++) {
			
			if(Character.isLetter(original[i])) {
				if(Character.toString(original[i]).toLowerCase().equals(char1)) {
					if(Character.isUpperCase(original[i])) {
						swapped[i]=char2.toUpperCase().charAt(0);
					} else {
						swapped[i]=char2.charAt(0);
					}
				} else if(Character.toString(original[i]).toLowerCase().equals(char2)) {
					if(Character.isUpperCase(original[i])) {
						swapped[i]=char1.toUpperCase().charAt(0);
					} else {
						swapped[i]=char1.charAt(0);
					}
				} else {
					if(Character.isUpperCase(original[i])) {
						swapped[i]=Character.toUpperCase(original[i]);
					} else {
						swapped[i]=original[i];
					}
				}
			} else {
				swapped[i]=original[i];
			}
		}
		return String.valueOf(swapped);
	}

}
