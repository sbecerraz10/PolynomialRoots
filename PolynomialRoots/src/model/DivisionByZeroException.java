/**
 * 
 */
package model;

/**
 * @author sebastian
 *
 */
public class DivisionByZeroException extends Exception {

	/**
	 * 
	 */
	public DivisionByZeroException() {
		super("Division By Zero");
	}

	/**
	 * @param arg0
	 */
	public DivisionByZeroException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public DivisionByZeroException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DivisionByZeroException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public DivisionByZeroException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
