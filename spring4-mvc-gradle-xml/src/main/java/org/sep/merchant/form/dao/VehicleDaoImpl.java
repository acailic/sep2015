package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.Vehicle;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("vehicleDao")
public class VehicleDaoImpl extends GenericDaoImpl<Vehicle> implements VehicleDao{

}
