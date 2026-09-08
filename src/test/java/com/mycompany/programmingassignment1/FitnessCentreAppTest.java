package com.mycompany.programmingassignment1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FitnessCentreAppTest {

    @Test
    void regularMemberUsesBaseFee() {
        RegularMember member = new RegularMember("M001", "Aisha", 250.00);
        assertEquals(250.00, member.calculateMonthlyFee(), 0.001);
    }

    @Test
    void premiumMemberAddsBenefitFee() {
        PremiumMember member =
                new PremiumMember("M002", "Daniel", 450.00, "Personal training");
        assertEquals(550.00, member.calculateMonthlyFee(), 0.001);
        assertEquals("Premium", member.getMembershipType());
    }

    @Test
    void reportContainsMemberAndTotals() {
        FitnessMember[] members = {
            new RegularMember("M001", "Aisha", 250.00),
            new PremiumMember("M002", "Daniel", 450.00, "Personal training")
        };

        int[][] attendance = {
            {8, 10},
            {12, 14}
        };

        String report = FitnessCentreApp.buildReport(members, attendance);

        assertTrue(report.contains("Aisha"));
        assertTrue(report.contains("Daniel"));
        assertTrue(report.contains("TOTAL MEMBERS: 2"));
        assertTrue(report.contains("TOTAL VISITS: 44"));
        assertTrue(report.contains("TOTAL MONTHLY FEES: R800.00"));
    }
}
