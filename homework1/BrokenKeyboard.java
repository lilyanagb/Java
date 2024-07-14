package homework1;
/* 
Сашко си купил нова механична клавиатура, но, за съжаление, 
още на първия ден от използването ѝ, разлял вода върху нея.
Оказало се, че след инцидента някои клавиши от клавиатурата не работят.
На Сашко му се налага да изпрати важно съобщение до приятел, но не може 
да напише някои думи.
За всяко изречение (message), което Сашко иска да изпрати, пресмята колко 
от думите на съобщението могат да се напишат, без да се използват 
изредените счупени клавиши(brokenKeys).*/

public class BrokenKeyboard {

    public static int calculateFullyTypedWords(String message, String brokenKeys){
        if(message.trim().isEmpty()) return 0; 

        String[] arr = message.trim().split("\\s+");
        int counter = 0;
        char[] keys = brokenKeys.toCharArray();
        
        for(String word : arr){
            for(char c : keys){
                if(word.contains(""+c)){
                    counter++;
                    break;
                }
            }
        }

        return arr.length - counter;
    }

    public static void main(String... args){
        System.out.println(calculateFullyTypedWords("i love mjt", "qsf3o")); //2
        System.out.println(calculateFullyTypedWords("secret      message info      ", "sms")); //1
        System.out.println(calculateFullyTypedWords("dve po 2 4isto novi beli kecove", "o2sf")); //2
        System.out.println(calculateFullyTypedWords("     ", "asd")); //0
        System.out.println(calculateFullyTypedWords(" - 1 @ - 4", "s")); //5
    }
}
