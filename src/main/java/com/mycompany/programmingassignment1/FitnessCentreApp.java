package com.mycompany.programmingassignment1;

/**
 * Section B console application: Smart Fitness Centre Management System.
 *
 * Demonstrates arrays, loops, inheritance, constructors and information hiding.
 */
public class FitnessCentreApp {

    public static void main(String[] args) {
        FitnessMember[] members = {
            new RegularMember("M001", "Aisha", 250.00),
            new PremiumMember("M002", "Daniel", 450.00, "Personal training"),
            new RegularMember("M003", "Liam", 250.00),
            new PremiumMember("M004", "Nadia", 450.00, "Nutrition consultation")
        };

        int[][] attendance = {
            {8, 10, 9, 7},
            {12, 14, 13, 15},
            {6, 8, 7, 9},
            {10, 11, 12, 14}
        };

        printReport(members, attendance);
    }

    public static String buildReport(FitnessMember[] members, int[][] attendance) {
        StringBuilder report = new StringBuilder();

        report.append("\n==============================================\n");
        report.append("       SMART FITNESS CENTRE REPORT\n");
        report.append("==============================================\n");

        double totalFees = 0;
        int totalVisits = 0;

        for (int i = 0; i < members.length; i++) {
            int memberVisits = 0;
            for (int j = 0; j < attendance[i].length; j++) {
                memberVisits += attendance[i][j];
                totalVisits += attendance[i][j];
            }

            double fee = members[i].calculateMonthlyFee();
            totalFees += fee;

            report.append("\nMember ").append(i + 1).append("\n");
            report.append("----------------------------------------------\n");
            report.append("MEMBER ID: ").append(members[i].getMemberId()).append("\n");
            report.append("MEMBER NAME: ").append(members[i].getMemberName()).append("\n");
            report.append("MEMBERSHIP TYPE: ").append(members[i].getMembershipType()).append("\n");
            report.append("MONTHLY FEE: R").append(String.format("%.2f", fee)).append("\n");
            report.append("TOTAL VISITS: ").append(memberVisits).append("\n");
        }

        report.append("\n==============================================\n");
        report.append("TOTAL MEMBERS: ").append(members.length).append("\n");
        report.append("TOTAL VISITS: ").append(totalVisits).append("\n");
        report.append("TOTAL MONTHLY FEES: R").append(String.format("%.2f", totalFees)).append("\n");
        report.append("==============================================\n");

        return report.toString();
    }

    private static void printReport(FitnessMember[] members, int[][] attendance) {
        System.out.println(buildReport(members, attendance));
    }
}

/**
 * Parent class demonstrates inheritance.
 */
abstract class FitnessMember {
    private final String memberId;
    private final String memberName;
    private final double baseFee;

    public FitnessMember(String memberId, String memberName, double baseFee) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.baseFee = baseFee;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public abstract double calculateMonthlyFee();

    public abstract String getMembershipType();
}

/**
 * Child class 1.
 */
class RegularMember extends FitnessMember {

    public RegularMember(String memberId, String memberName, double baseFee) {
        super(memberId, memberName, baseFee);
    }

    @Override
    public double calculateMonthlyFee() {
        return getBaseFee();
    }

    @Override
    public String getMembershipType() {
        return "Regular";
    }
}

/**
 * Child class 2.
 */
class PremiumMember extends FitnessMember {
    private final String extraBenefit;

    public PremiumMember(String memberId, String memberName,
                         double baseFee, String extraBenefit) {
        super(memberId, memberName, baseFee);
        this.extraBenefit = extraBenefit;
    }

    public String getExtraBenefit() {
        return extraBenefit;
    }

    @Override
    public double calculateMonthlyFee() {
        return getBaseFee() + 100.00;
    }

    @Override
    public String getMembershipType() {
        return "Premium";
    }
}
