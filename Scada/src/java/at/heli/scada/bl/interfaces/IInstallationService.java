/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl.interfaces;

import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;

/**
 *
 * @author daniel
 */
public interface IInstallationService {
    public ExecutionResult<Measurement> createMeasure(Double value, int typeid, String serialno) throws BLException;
}
