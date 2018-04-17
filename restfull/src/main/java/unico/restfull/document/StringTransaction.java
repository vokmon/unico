package unico.restfull.document;

import java.io.Serializable;

/**
 * The document model for storing transaction
 * 
 * @author Arnon Ruangthanawes
 */
public class StringTransaction implements Serializable {

	/**
	 * Serialize id
	 */
	private static final long serialVersionUID = 6869716658585291L;

	/**
	 * String value 1
	 */
	private int value1;

	/**
	 * String value 2
	 */
	private int value2;

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	@Override
	public String toString() {
		return "StringTransaction [value1=" + value1 + ", value2=" + value2 + "]";
	}
}
