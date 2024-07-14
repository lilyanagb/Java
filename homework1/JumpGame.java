package homework1;
/*
 * Играта скок-подскок се играе по следния начин: на земята имате 
 * нарисуванa последователност от квадратчета (с дължина поне едно)
 *  с неотрицателни числа в тях. Стъпвате на първото квадратче и можете
 *  да минете толкова квадратчета напред, колко е числото, върху което 
 * в момента сте стъпили. Например, ако пише числото 3, можете да
 *  направите 0, 1, 2 или 3 крачки напред. Печелите играта, ако успеете
 *  след достатъчен брой ходове от този вид да стъпите на последното
 *  квадратче.
 */

public class JumpGame {
    public static boolean canWin(int[] array){
        if(array.length <= 1) return true;

        int maxIndexToReach = array[0];
        for(int i = 0; i < array.length; i++){
            if(i > maxIndexToReach) return false;
            if(maxIndexToReach >= array.length - 1) break;
             
            maxIndexToReach = Math.max(maxIndexToReach, i + array[i]);
        }

        return true;
    }

    public static void main(String... args){
        System.out.println(canWin(new int[]{2, 3, 1, 1, 0})); //true
        System.out.println(canWin(new int[]{0, 2, 1, 1, 0})); //false
        System.out.println(canWin(new int[]{3, 2, 1, 0, 0})); //false
        System.out.println(canWin(new int[]{1, 2, 2, 0, 0})); //true
        System.out.println(canWin(new int[]{1, 1, 1, 1, 1, 1, 0})); //true
    }
}
