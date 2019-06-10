public class Creator {

	public static void main(String[] args) {
		//730 IF PAGENR%=4 AND CURSOR%=0 AND VOHVOZF%=1 GOTO 735
		//732 X=(RND(1)*25+75):Y=PREVOZF%*INT(X):Z=Y/100
		//735 IF KONTO%>=Z THEN KONTO%=KONTO%-Z : ANZVOZF%=ANZVOZF%+1 : GOTO RENDER
		
		int lineNr = 530;
		int crs = 0;
		String[] names = {"AQU", "AAZ","BDE","BFE","BMD","FFI","FES","FCA","ZEN","ZEK","ZSL","OCO","OMA","OVE","PNF","PNE","TTB","VGE","VPO","VGZ","VTD","WDF","VZF"};
		
		for(String name:names) {
			//int nextLine = lineNr + 5;
			//System.out.println(lineNr + " IF PAGENR%=2 AND CURSOR%=" + crs + " GOTO "+nextLine);
			//System.out.println(lineNr + " IF PN%=2 AND CS%=" + crs + " AND KT%>=P" + name + "% THEN KT%=KT%-P" + name + "% : A" + name + "%=A" + name + "%+1 : GOTO RENDER");
			
			//System.out.println("9" + ((crs < 10) ? "0" : "") + crs + "8 VOH"+name+"% = 1");
			
			int nextLine1 = lineNr + 1;
			int nextLine2 = lineNr + 2;
			
			System.out.println(lineNr + " IF PN%<>4 OR CS%<>" + crs + " AND V" + name + "%=1 GOTO " + (lineNr + 3));
			System.out.println(nextLine1 + " X=(RND(1)*25+75):Y=P" + name + "%*INT(X):Z=Y/100");
			System.out.println(nextLine2 + " IF KT%>=Z THEN KT%=KT%-Z:A" + name + "%=A" + name + "%+1:GOTO RENDER");
			
			crs++;
			lineNr+=3;
		}
	}

}
