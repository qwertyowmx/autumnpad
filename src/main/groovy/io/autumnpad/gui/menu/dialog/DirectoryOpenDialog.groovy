package io.autumnpad.gui.menu.dialog

import javax.swing.*

class DirectoryOpenDialog extends JFileChooser {
    DirectoryOpenDialog() {
        setCurrentDirectory(new File("."));
        setDialogTitle("Select directory");
        setFileSelectionMode(DIRECTORIES_ONLY);
    }
}
