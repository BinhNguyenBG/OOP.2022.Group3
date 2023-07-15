package ElectricalElement;

import org.apache.commons.math3.complex.*;

public class Resistor extends ElectricalElement{
	Complex resistanceValueWithoutUnit;
	public Resistor() {
		// TODO Auto-generated constructor stub
		super();
		String rName = String.format("R%s", super.getId());
		super.setName(rName);
	}
	
	public Resistor(double resistance, String unit) {
		this();
		this.resistanceValueWithoutUnit = new Complex(resistance,0.0);
		this.setUnit(unit);
		this.setResistance(getSIResistance());
		
	}
	
	public Complex getSIResistance() {
		if (this.getUnit().equals("kΩ")) {
			return new Complex(this.resistanceValueWithoutUnit.getReal()*1e3, 0);
		}
		else if (this.getUnit().equals("MΩ")) {
			return new Complex(this.resistanceValueWithoutUnit.getReal()*1e6, 0);
		}
		else if (this.getUnit().equals("GV")) {
			return new Complex(this.resistanceValueWithoutUnit.getReal()*1e9, 0);
		}
		else if (this.getUnit().equals("mV")) {
			return new Complex(this.resistanceValueWithoutUnit.getReal()*1e-3, 0);
		}
		else if (this.getUnit().equals("µV")) {
			return new Complex(this.resistanceValueWithoutUnit.getReal()*1e-6, 0);
		}
		return this.resistanceValueWithoutUnit;
	}
	
	public String toString() {
		return (super.getName() + " = " + super.getResistance().getReal() + "ohm, U = " + super.getVoltage().getReal() + "+" + super.getVoltage().getImaginary() +"i V, I = " + super.getCurrentIntensity().getReal() + "+" + super.getCurrentIntensity().getImaginary() + "i A");
	}

	public Complex getResistanceValueWithoutUnit() {
		return resistanceValueWithoutUnit;
	}
}