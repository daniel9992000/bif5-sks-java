/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.interfaces.BLException;
import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.bl.interfaces.IInstallationService;
import at.heli.scada.dal.interfaces.DalException;
import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.interfaces.MeasurementRepository;
import at.heli.scada.dal.interfaces.TypeRepository;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import at.heli.scada.entities.MeasurementType;
import at.heli.scada.validator.InstallationValidator;
import at.heli.scada.validator.MeasurementValidator;
import at.heli.scada.validator.ValidationError;
import at.heli.scada.validator.Validator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author daniel
 */
@Named
@Alternative
public class InstallationService implements IInstallationService {
    
    private static final Logger log = Logger.getLogger(InstallationService.class.getName());
    
    private MeasurementRepository mrepo;
    private InstallationRepository irepo;
    private TypeRepository trepo;
    
    @Inject
    public InstallationService(MeasurementRepository mrepo, InstallationRepository irepo, TypeRepository trepo)
    {
        this.mrepo = mrepo;
        this.irepo = irepo;
        this.trepo = trepo;
    }

    @Override
    public ExecutionResult<Measurement> createMeasure(Double value, int typeid, String serialno) throws BLException {
        try
        {
            log.log(Level.INFO, "Fetching installation serialno {0}", serialno);
            Installation i = irepo.getBySerialNo(serialno);
            
            if(i == null)
            {
                throw new BLException("Installation with serial no " + serialno + " does not exist!");
            }
            
            log.log(Level.INFO, "Fetching measurement type id {0}", typeid);
            MeasurementType mt = trepo.getById(typeid);
            
            if(mt == null)
            {
                throw new BLException("MeasurmentType with id " + typeid + " does not exist!");
            }
            
            Measurement m = new Measurement();
            m.setMeasuredate(new Date());
            m.setMeasuretime(new Date());
            m.setMeasurevalue(value);
            m.setInstallationid(i);
            m.setTypeid(mt);
            
            Validator<Measurement> validator = new MeasurementValidator();
            validator.validate(m);
            
            if(validator.isValid())
            {
                mrepo.save(m);
                log.log(Level.INFO, "Measurement saved!");
                return new ExecutionResult<Measurement>(m);
            }
            else
            {
                log.log(Level.INFO, "{0} Validation errors", validator.getErrors().size());
                for(ValidationError ve : validator.getErrors())
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error on property ");
                    sb.append(ve.getField());
                    sb.append(": ");
                    sb.append(ve.getMessage());
                    log.log(Level.INFO, sb.toString());
                }
                return new ExecutionResult<Measurement>(m, validator.getErrors());
            }
            
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "cannot save measurement!", err);
            throw new BLException("cannot save measurement!", err);
        }
    }

    @Override
    public ExecutionResult<Installation> createInstallation(Installation i) throws BLException {
        
        try
        {
            Validator<Installation> validator = new InstallationValidator();
            validator.validate(i);
            
            if(validator.isValid())
            {
                irepo.save(i);
                log.log(Level.INFO, "Installation saved!");
                return new ExecutionResult<Installation>(i);
            }
            else
            {
                log.log(Level.INFO, "{0} Validation errors", validator.getErrors().size());
                for(ValidationError ve : validator.getErrors())
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error on property ");
                    sb.append(ve.getField());
                    sb.append(": ");
                    sb.append(ve.getMessage());
                    log.log(Level.INFO, sb.toString());
                }
                return new ExecutionResult<Installation>(i, validator.getErrors());
            }
        }
        catch(DalException err)
        {
            throw new BLException("cannot create installation!", err);
        }
    }
}
