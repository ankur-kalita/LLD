package CaseStudies.TicTacToeDemo;

public class RulesFactory {
    public static GameRules createRules(RuleType ruleType) {
        switch (ruleType) {
            case INTERNATIONAL:
                return new InternationalRules();
            case NATIONAL:
                return new NationalRules();
            default:
                return new InternationalRules();
        }
    }
}
