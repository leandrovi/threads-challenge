/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leandro
 */
public class AuditManagerMessagesThread extends Thread {
    private boolean status;
    
    @Override
    public void run() {
        setStatus(true);
        
        while (status) {
            try {            
                String msg = AuditManager.getInstance().removeAuditMessage();
                
                if (msg != null) {
                    sendMessageToAuditSystem(msg);
                }
                
                // Gets the audit messages in 1 on 1     milliseconds
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(AuditManagerMessagesThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public synchronized void setStatus(boolean value) {
        status = value;
    }
    
    private void sendMessageToAuditSystem(String msg) throws InterruptedException {
        System.out.printf("\n\n%s ** Audit Message ** %s\n\n", Instant.now().toString(), msg);
        Thread.sleep(1);
    }
}
