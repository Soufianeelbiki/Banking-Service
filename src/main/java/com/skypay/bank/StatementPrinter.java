package com.skypay.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Prints bank statement: most recent transactions first.
 */
public class StatementPrinter {
    private static final Logger log = LoggerFactory.getLogger(StatementPrinter.class);
    private static final String HEADER = "DATE       | AMOUNT  | BALANCE";

    public void print(List<Transaction> transactions) {
        log.info(HEADER);
        // iterate backwards without copying
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            StringBuilder sb = new StringBuilder(64)
                .append(t.getDate()).append(" | ")
                .append(t.getAmount()).append(" | ")
                .append(t.getBalance());
            log.info(sb.toString());
        }
    }
}