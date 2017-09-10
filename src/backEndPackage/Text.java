package backEndPackage;

public class Text {
	StringBuilder builderText;
	public Text(String text){
		builderText = new StringBuilder(text);
	}
	
	boolean isDelimiter (char c){
		String endings = " ,.!?:();-+'\"\n\t";
		return endings.indexOf(c)>-1;	//връща на коя позиция е символа, който е подаден; ако не се намери, връща -1	
	}
	public String translate(){
		char prevChar;
		boolean isChanged = true;
		boolean isFirst=true;//първо преминаване през цикъла
		String[] article ={"a","an","the"};
		
		while(isChanged){
			prevChar=0;
			isChanged = false;
			
			for(int i=0;i<builderText.length();i++){
				
				char currentChar = builderText.charAt(i);
				if(isFirst){
					boolean isDeleted = false;
					for(String str : article){
						/*
						 * ако сегашният индекс е != от върнатия от функцията(т.е. ако се изтрие артикъл и върне предишен индекс),
						 * индексът се връща още веднъж назад и се излиза от вътрешния цикъл 
						 */
						if (i!=deleteArticleAndReturnPreviousIndex(str,i)) { i--; isDeleted = true; break; }
					}
					if(isDeleted) continue;
				}
				
				/*
				 * проверка дали предишният символ е 'с'('С')
				 */
				if(Character.toLowerCase(prevChar) =='c'){
					isChanged=true;
					switch(Character.toLowerCase(currentChar)){
					case 'i':
					case 'e':builderText.setCharAt(i-1,(char)('S' - ('C' - prevChar))); break;
					case 'k':builderText.deleteCharAt(i-1);i--; break;
					default:builderText.setCharAt(i-1,(char)('K' - ('C' - prevChar))); prevChar=(char)('K' - ('C' - prevChar)); break;
					}
				}
				/*
				 * проверка за двойни букви и интервали
				 */
				if(Character.toLowerCase(prevChar) == Character.toLowerCase(currentChar) && (Character.isLetter(currentChar) || currentChar == ' ')){
					isChanged=true;
					switch(Character.toLowerCase(currentChar)){
					case 'e': { builderText.setCharAt(i-1, (char)('I' - ('E' - prevChar)));
								builderText.deleteCharAt(i);currentChar=0;i--; break;}
					case 'o': { builderText.setCharAt(i-1, (char)('U' - ('O' - prevChar)));
								builderText.deleteCharAt(i);currentChar=0;i--; break;}
					default: builderText.deleteCharAt(i);currentChar=0;i--; break;
					//(char)('U' - ('O' - prevChar))    решава се дали U e голямо или малко, като ('О'-'О' || 'O'-'o')
					}
				}
				/*
				 * Проверка дали настоящият символ е ='e';
				 * проверка дали е последен символ, или следващият символ е делимитер
				 * проверка дали предишният символ е разделител(делимитер) и индекса на сегашният е различен от 0
				 */
				if(Character.toLowerCase(currentChar) == 'e'){
					if((i==builderText.length()-1 || isDelimiter(builderText.charAt(i+1))) && 
							!isDelimiter(prevChar) && i!=0){
						isChanged=true;
						builderText.deleteCharAt(i);i--;currentChar=0;
					}
				}
				/*
				 * проверява дали сме на последния символ и той е = на с(С); 
				 * ако е, се заменя с к(К)
				 */
				if(i==builderText.length()-1 && Character.toLowerCase(currentChar) == 'c'){
					builderText.setCharAt(i,(char)('K' - ('C' - prevChar)));
				}
				prevChar = currentChar;	
			}
			isFirst=false;
		}
		return builderText.toString().trim();
	}
	
	int deleteArticleAndReturnPreviousIndex(String article,int i){
	
		int articleLength = article.length(); 	
	  if(i<builderText.length()-articleLength+1 && builderText.substring(i, i+articleLength).equalsIgnoreCase(article)){
			if((i==builderText.length()-articleLength || isDelimiter(builderText.charAt(i+articleLength))) && 
					(i==0 || isDelimiter(builderText.charAt(i-1)))){
				for(int k=0;k<articleLength;k++){
					builderText.deleteCharAt(i);
				}
				i--; 
			}
	    }
		return i; 
	}
}
	

