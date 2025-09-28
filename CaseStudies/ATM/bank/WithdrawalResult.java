package CaseStudies.ATM.bank;

public record WithdrawalResult(Status status, int balanceAfter) {
    public enum Status { OK, INSUFFICIENT_FUNDS, DAILY_LIMIT_EXCEEDED }
}
