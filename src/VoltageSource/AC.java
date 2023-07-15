package VoltageSource;

public class AC extends VoltageSource{
	
	public AC(double voltage, String volunit, double frequency, String frequnit) {
		super(voltage, volunit);
		this.frequency = frequency;
		this.frequnit = frequnit;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	
	public String toString() {
		return ("AC: " + super.getVoltage() + "V, " + this.frequency + " Hz");
	}
}