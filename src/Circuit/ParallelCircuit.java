package Circuit;

import ElectricalElement.*;

import org.apache.commons.math3.complex.Complex;

public class ParallelCircuit extends Circuit{
	
	public ParallelCircuit() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public void trigger() {
		//get the voltage of the circuit and cast it into Complex
		Complex vol = new Complex(super.getVoltageSource().getSIVoltage(), 0);


		//set voltage of all elements in the circuit equals to the voltage of the source
		for (ElectricalElement e: super.getElements()) {
			e.setVoltage(vol);
		}
		
		//set resistance, current intensity of all elements in the circuit
		for (ElectricalElement e: super.getElements()) {
			Complex resistance = e.computeResistace(super.getVoltageSource());
			e.setResistance(resistance);
			e.setCurrentIntensity(vol.divide(resistance));
		}
	}
	
	public Complex getEquivalentResistance() {
		//calculate the inverse of the equivalent resistance
		Complex invRes = new Complex(0, 0);
		
		Complex one = new Complex(1, 0); //cast number 1 into Complex
		
		for (ElectricalElement e: super.getElements()) {
			invRes = invRes.add(one.divide(e.getResistance()));
		}
		return one.divide(invRes);
	}
	
	public void print() {
		System.out.println("***********************ParallelCircuit***********************");
		System.out.println("Electrical Elements:");
		for (ElectricalElement element: super.getElements()) {
			System.out.println(element.toString());
		}
		System.out.println("*************************************************************");
	}
}
