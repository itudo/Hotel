package com.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

public class Welcome extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Welcome(Composite parent, int style) {
		super(parent, style);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage("src\\images\\welcome.png"));
		lblNewLabel.setBounds(0, 0, 1200, 470);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
