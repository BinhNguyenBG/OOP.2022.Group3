package ElectricalElement;

import org.apache.commons.math3.complex.Complex;

import VoltageSource.VoltageSource;

public class Inductor extends ElectricalElement{
	private double inductance;
		
	public Inductor() {
		// TODO Auto-generated constructor stub
		super();
		String lName = String.format("L%s", super.getId());
		super.setName(lName);
	}
	
	public Inductor(double inductance, String unit) {
		this();
		this.inductance = inductance;
		this.setUnit(unit);
	}

	public double getInductance() {
		return inductance;
	}

	public void setInductance(double inductance) {
		this.inductance = inductance;
	}
	
	public double getSIInductance() {
		if (this.getUnit().equals("mH")) {
			return inductance*1e-3;
		}
		else if (this.getUnit().equals("µH")) {
			return inductance*1e-6;
		}
		else if (this.getUnit().equals("nH")) {
			return inductance*1e-9;
		}
		else if (this.getUnit().equals("pH")) {
			return inductance*1e-12;
		}
		return inductance;
	}
	
	public String toString() {
		return (super.getName() + " = " + this.inductance + "H, U = " + super.getVoltage().getReal() + "+" + super.getVoltage().getImaginary() +"i V, I = " + super.getCurrentIntensity().getReal() + "+" + super.getCurrentIntensity().getImaginary() + "i A, R_L = " + super.getResistance().getReal() + "+" + super.getResistance().getImaginary()+"i ohm");
	}

	@Override
	public Complex computeResistace(VoltageSource s) {
		// TODO Auto-generated method stub
		Complex lRes = j.multiply(2*Math.PI*s.getSIFrequency()*this.getSIInductance());
		return lRes;
	}

}