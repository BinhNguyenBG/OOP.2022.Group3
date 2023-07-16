package Circuit;

import ElectricalElement.*;
import VoltageSource.AC;

import org.apache.commons.math3.complex.Complex;

public class SerialCircuit extends Circuit{

	public SerialCircuit() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public void trigger() {
		if (this.getVoltageSource() instanceof AC) {
			//set resistance of all elements in the circuit
			for (ElectricalElement e: super.getElements()) {
				Complex resistance = e.computeResistace(super.getVoltageSource());
				e.setResistance(resistance);
			}
			//get main current intensity of the circuit
			Complex equiRes = this.getEquivalentResistance();
			Complex curInt = (new Complex(super.getVoltageSource().getSIVoltage(), 0)).divide(equiRes);
			
			//set current intensity and voltage of all elements
			for (ElectricalElement e: super.getElements()) {
				e.setCurrentIntensity(curInt);
				e.setVoltage(curInt.multiply(e.getResistance()));
			}
		} else {
			double inv_equivalentCapacitance = 0;
			for (ElectricalElement e: super.getElements()) {
				if (e instanceof Inductor) {
					//calculate the resistance of the inductor
					Complex lRes = new Complex(0, 0);
					
					e.setResistance(lRes);				
				} else if (e instanceof Capacitor) {
					//calculate the resistance of the capacitor
					Complex cRes = new Complex(Double.POSITIVE_INFINITY, 0);
					
					e.setResistance(cRes);
					inv_equivalentCapacitance += 1/(((Capacitor)e).getSICapacitance());
				}
			}
			//get the voltage of capacitors in serial circuit with DC voltage src
			double equivalentCapacitance = 1/inv_equivalentCapacitance;
			double q = this.getVoltageSource().getVoltage()*equivalentCapacitance;
			//get main current intensity of the circuit
			Complex equiRes = this.getEquivalentResistance();
			Complex curInt = (new Complex(super.getVoltageSource().getSIVoltage(), 0)).divide(equiRes);
			
			//set current intensity and voltage of all elements
			for (ElectricalElement e: super.getElements()) {
				if (e instanceof Capacitor){
					e.setCurrentIntensity(curInt);
					e.setVoltage(new Complex(q/((Capacitor)e).getSICapacitance(), 0));
				} else {
					e.setCurrentIntensity(curInt);
					e.setVoltage(curInt.multiply(e.getResistance()));
				}
				
			}
		}
		
		
		
		
	}
	
	public Complex getEquivalentResistance() {
		Complex res = new Complex(0, 0);
		for (ElectricalElement e: super.getElements()) {
			res = res.add(e.getResistance());
		}
		return res;
	}
	
	public void print() {
		System.out.println("***********************SerialCircuit***********************");
		System.out.println("Electrical Elements:");
		for (ElectricalElement element: super.getElements()) {
			System.out.println(element.toString());
		}
		System.out.println("***********************************************************");
	}
}
