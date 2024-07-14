package homework1;
/*
IP (Internet Protocol) е протокол на мрежовия слой в OSI модела и е в основата на целия Интернет. 
Различните устройства в мрежата се характеризират с различни IP адреси, благодарение на които те се идентифицират 
и могат да си комуникират. IP адресите биват два вида: IPv4 и IPv6. Днес ще се фокусираме върху IPv4 адресите. 
Един валиден IPv4 адрес има следния формат: x1.x2.x3.x4, където xi се нарича октет, 0 <= xi <= 255 и xiне съдържа 
водещи нули.

Създайте публичен клас IPValidator с метод

public static boolean validateIPv4Address(String str)
който по даден низ str връща true, ако str e валиден IPv4 адрес, и false иначе.
*/

public class IPValidator {

    public static boolean validateIPv4Address(String str){

        if(str == null || str.isEmpty()) return false;

        String[] tokens = str.split("\\.", -1); //няма да ограничава броя на резултатните поднизове и няма да премахва празните поднизове
        if(tokens.length != 4){
            return false;
        }

        for(String c : tokens){
            if(c.length() > 1 && c.charAt(0) == '0') return false;

            int number = Integer.parseInt(c);
            if(number < 0 || number > 255) return false;
        }
        return true;
    }

    public static void main(String[] args){
        //Scanner scanner = new Scanner(System.in);
        //String line = scanner.nextLine();

        System.out.println(validateIPv4Address("192.168.1.1"));
        System.out.println(validateIPv4Address("192.168.1.0"));
        System.out.println(validateIPv4Address("192.168.1.00"));
        System.out.println(validateIPv4Address("192.168@1.1"));
    }
};

