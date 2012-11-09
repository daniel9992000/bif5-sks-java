/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.exception.BLException;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.dal.*;
import at.heli.scada.entities.Installation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class InstallationBL {
    
    private static final Logger log = Logger.getLogger(InstallationBL.class.getName());
    
    private Repository<Installation> repo;
    
    public InstallationBL(Repository<Installation> repo)
    {
        this.repo = repo;
    }
    
    public void createInstallation(Installation i) throws BLException
    {
        try
        {
            repo.save(i);
            log.log(Level.INFO, "Installation saved!");
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "cannot save installation!", err);
            throw new BLException("cannot save installation!", err);
        }
    }
    
}
