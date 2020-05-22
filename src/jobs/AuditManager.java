/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import jobs.AuditManagerMessagesThread;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leandro
 */
public class AuditManager {
    // Start of singleton implementation
    
    private static AuditManager instance;
    
    private AuditManager() {
        messagesQueue = new ConcurrentLinkedQueue();
    }
    
    public static AuditManager getInstance() {
        if (instance == null) 
            instance = new AuditManager();
        
        return instance;
    }
    
    // End of singleton implementation
    
    ConcurrentLinkedQueue<String> messagesQueue;
    AuditManagerMessagesThread thread;
    
    public void addAuditMessage(String auditMessage) {
        messagesQueue.add(auditMessage);
    }
    
    String removeAuditMessage() {
        String msg = messagesQueue.poll();
        return msg;
    }
    
    public void activate() {
        if (thread == null) {            
            thread = new AuditManagerMessagesThread();
            thread.start();
        }
    }    
    
    public void deactivate() {
        if(thread != null) {
            thread.setStatus(false);
            
            try {
                thread.join(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AuditManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (thread.isAlive())
                thread.interrupt();
        }
    }
}
