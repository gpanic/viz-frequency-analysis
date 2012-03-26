package gui.file;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author Gregor Panič
 *
 */
public class TextFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
            return true;
        }
		
		String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
	        if(ext!=null) {
	        if(ext.equals("txt")) {
	        	return true;
	        } else {
	        	return false;
	        }
        } else {
        	return false;
        }
	}

	@Override
	public String getDescription() {
		return "Text files (*.txt)";
	}

}
