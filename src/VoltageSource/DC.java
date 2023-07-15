package VoltageSource;

public class DC extends VoltageSource{
	
	public DC(double voltage, String volunit) {
		super(voltage, volunit);
		this.frequency = 0;
		this.frequnit = "Hz";
	}
	
	public String toString() {
		return ("DC: " + super.getVoltage() + "V");
	}
}