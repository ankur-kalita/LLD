package CaseStudies.ATM.strategy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface DispenseStrategy {
    Map<Integer,Integer> makeBundle(int amount, List<Integer> denoms);

    // a simple greedy default
    static DispenseStrategy greedy() {
        return (amount, denoms) -> {
            Map<Integer,Integer> out = new LinkedHashMap<>();
            int remaining = amount;
            for (int d : denoms) {
                int cnt = remaining / d;
                if (cnt > 0) { out.put(d, cnt); remaining -= cnt * d; }
            }
            if (remaining != 0) return Map.of(); // fail (e.g., cannot make exact change)
            return out;
        };
    }
}

