import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Result {
    private Result() {}
    
    public static long bioHazard(int n, List<Integer> allergic, List<Integer> poisonous) {
      
        HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<>();

        for (int i = 0; i < allergic.size(); i++) {
            int poison = poisonous.get(i);
            int allergen = allergic.get(i);
            hashMap.putIfAbsent(poison, new HashSet<>());
            hashMap.get(poison).add(allergen);
        }

        long validIntervals = 0;
        for (int start = 1; start <= n; start++) {
            for (int end = start; end <= n; end++) {
                if (isValidInterval(start, end, hashMap)) {
                    validIntervals++;
                }
            }
        }

        return validIntervals;
    }

    private static boolean isValidInterval(int start, int end, HashMap<Integer, HashSet<Integer>> hashMap) {
    
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                
                if (hashMap.containsKey(i) && hashMap.get(i).contains(j)) {
                    return false;
                }
                if (hashMap.containsKey(j) && hashMap.get(j).contains(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}

public class Biohazards {
    public static void main(String[] args) {
        List<Integer> a1 = new ArrayList<>();
        a1.add(2);
        a1.add(1);
        a1.add(3);
        
        List<Integer> a2 = new ArrayList<>();
        a2.add(3);
        a2.add(3);
        a2.add(1);
        
        long result = Result.bioHazard(3, a1, a2);
        System.out.println(result);  // Expected output: 4
    }
}
