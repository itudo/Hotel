package com.utils;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;


public class ImgSizeUtil {
	public void imgSize(final org.eclipse.swt.graphics.Image image,Object obj){
		if (obj instanceof Label) {
			((Label) obj).setImage(image);
		}else if (obj instanceof Button) {
			((Button) obj).setImage(image);
		}else if (obj instanceof Composite) {
			((Composite) obj).setBackgroundImage(image);
		} else if(obj instanceof Shell) {
			((Composite) obj).setBackgroundImage(image);
		}
		
		final int imgWidth = image.getBounds().width;
		final int imgHeight = image.getBounds().height;
		((Control) obj).addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent event) {
				event.gc.drawImage(image, 0, 0, imgWidth, imgHeight, 0, 0,
						event.width, event.height);
			}
		});
	}
}
