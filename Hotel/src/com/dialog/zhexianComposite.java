package com.dialog;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class zhexianComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public zhexianComposite(Composite parent, int style) {
		super(parent, SWT.BORDER);
		
		Label lblZhex = new Label(this, SWT.NONE);
		lblZhex.setBounds(103, 103, 61, 17);
		lblZhex.setText("zhex");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
