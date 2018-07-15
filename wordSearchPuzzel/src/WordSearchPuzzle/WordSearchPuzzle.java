package WordSearchPuzzle;



///*   
//    0 = horizontal forward          ---
//    1 = vertical forward            |||
//    2 = diagonal left forward       \\\
//    3 = diagonal right forward      ///
//    -------reverse---------
//    4 = horizontal backward         ---
//    5 = vertical backward           |||
//    6 = diagonal left backward      \\\
//    7 = diagonal right backward     ///
//*/
    
import java.util.Random;

public class WordSearchPuzzle
{
    public static int gsize = 14;
    public static char ch[][] = new char[gsize + 1][gsize + 1];
    public static String nlist[] = new String[12];
    public static String location[] = new String[12];
    public static int nt = 0;
    public static String cheatsheet = "";
    public static void createList(){
nlist[0] = "JAVA"; nlist[6] = "JAVASCRIPT";
nlist[1] = "CPLUS"; nlist[7] = "CSHARP";
nlist[2] = "RUBY"; nlist[8] = "HTML";
nlist[3] = "SWIFT"; nlist[9] = "PYTHON";
nlist[4] = "CSS"; nlist[10] = "PHP";
nlist[5] = "SQL"; nlist[11] = "JQUERY";
}

    public static void main(String[] args) {
    
    createList();
    System.out.println(" 1) JAVASCRIPT 2) CSS");
    System.out.println(" 3) CSHARP     4) RUBY");
    System.out.println(" 5) SWIFT      6) SQL");
    System.out.println(" 7) PYTHON     8) PHP");
    System.out.println(" 9) JQUERY    10) HTML");
    System.out.println("11) CPLUS     12) JAVA\n");
    
    for (int l = 0; l < nlist.length; l++) {
    position(nlist[l]);
    }
    String ng = "    ";
    for (int u = 0; u <= gsize; u++){
        if (u > 9) {ng += "" + u;}
        else {ng += u + " ";}
    }
    System.out.println(ng + "\n");
    System.out.println(grid(gsize));
    System.out.println("");
    System.out.println(ng + "\n");
    System.out.println(cheatsheet);
    System.out.println("");
    for (int i = 0; i < 12; i++) {
        System.out.println(location[i]);
    }
    
    }
    
    public static boolean addtogrid(int minrow, int mincol, int maxrow, int maxcol, String word, int dir, String orgword) {
        int l = word.length() - 1;
        int x = new Random().nextInt(maxcol - mincol) + mincol;
        int y = new Random().nextInt(maxrow - minrow) + minrow;
        int dd = 0;
        boolean good = true;
        char wch[] = word.toCharArray();
        for (int c = 0; c < wch.length; c++){
            switch (dir) {
                case 0: case 4: // horizontal
                    if ((ch[x + c][y] == 0 || ch[x + c][y] == wch[c]) && dd < 1) {
                        good = true;
                        if (ch[x + c][y] == wch[c]) {dd++;}
                    } else {good = false;}
                break;
                case 1: case 5: // veritcal
                    if ((ch[x][y + c] == 0 || ch[x][y + c] == wch[c]) && dd < 1) {
                        good = true;
                        if (ch[x][y + c] == wch[c]) {dd++;}
                    } else {good = false;}
                break;
                case 2: case 6: // diagonal L
                    if ((ch[x + c][y + c] == 0 || ch[x + c][y + c] == wch[c]) && dd < 1) {
                        good = true;
                        if (ch[x + c][y + c] == wch[c]) {dd++;}
                    } else {good = false;}                
                break;
                case 3: case 7: // diagonal R
                    if ((ch[x + c][y - c] == 0 || ch[x + c][y - c] == wch[c]) && dd < 1) {
                        good = true;
                        if (ch[x + c][y - c] == wch[c]) {dd++;}
                    } else {good = false;}                
                break;
            }
            if (good==false) {return false;}
        }
        
        if (good) {
            location[nt] = orgword + ": (" + x + " - " + y + ") dir: " + dir; nt++;
            for (int c = 0; c < wch.length; c++){
                switch (dir) {
                    case 0: case 4:
                        ch[x + c][y] = wch[c];
                    break;
                    case 1: case 5:
                        ch[x][y + c] = wch[c];
                    break;
                    case 2: case 6:
                        ch[x + c][y + c] = wch[c];
                    break;
                    case 3: case 7:
                        ch[x + c][y - c] = wch[c];
                    break;
                }
            }
        }
        
        return good;
    }
    
    public static void position(String word) {
        int l = word.length() - 1;
        String w = new String("");
        int minrow = 0, mincol = 0;
        int maxrow = 0, maxcol = 0;
        StringBuilder reverseWord = new StringBuilder(word).reverse();
        int dir = direction();
        if (dir > 3) {w = reverseWord.toString();} else {w = word;}
        switch (dir) {
            case 0: case 4: // horizontal
            maxrow = gsize;
            maxcol = gsize - l;
            break;
            case 1: case 5: // veritcal
            maxrow = gsize - l;
            maxcol = gsize;
            break;
            case 2: case 6: // diagonal L
            maxrow = gsize - l;
            maxcol = gsize - l;
            break;
            case 3: case 7: // diagonal R
            minrow = l;
            maxrow = gsize;
            maxcol = gsize - l;
            break;
        }
        if (addtogrid(minrow, mincol, maxrow, maxcol, w, dir, word)!=true) 
        {position(word);}
    }
    
    public static int direction() {
        Random rnd = new Random();
        return rnd.nextInt(8);
    }
    
    public static String grid(int size) {
        String line = new String("");
        String abc = new String("ABCDEFGHIJKLMNOPQRSTUZWXYZ");
        char c[] = abc.toCharArray();
        char h = ' ';
        Random index = new Random();
        line = " 0  ";
        cheatsheet = " 0  ";
        for (int col = 0; col <= size; col++) {
            for (int row = 0; row <= size; row++) {
                h = c[index.nextInt(26)];
                if (ch[row][col]==0) {
                    ch[row][col] = h;
                    cheatsheet += "." + " ";
                } else {cheatsheet += ch[row][col] + " ";}
                line += ch[row][col] + " ";
            }
            if (col < gsize) {
            if ((col + 1) < 10) {
            line += "\n " + (col + 1) + "  ";
            cheatsheet += "\n " + (col + 1) + "  ";
            } else {
            line += "\n" + (col + 1) + "  ";
            cheatsheet += "\n" + (col + 1) + "  ";
            }
            }
        }
        return line;
    }
    
}