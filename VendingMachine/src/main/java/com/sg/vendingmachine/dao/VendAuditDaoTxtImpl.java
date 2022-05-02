package com.sg.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VendAuditDaoTxtImpl implements VendAuditDao {

    private static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws AuditPersistenceException {
        PrintWriter out;

        try (FileWriter fileWriter = new FileWriter(AUDIT_FILE, true)) {
            out = new PrintWriter(fileWriter);
        } catch (IOException e) {
            throw new AuditPersistenceException("Could not persist audit information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
