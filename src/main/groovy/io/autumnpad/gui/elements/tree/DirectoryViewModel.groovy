package io.autumnpad.gui.elements.tree


import javax.swing.tree.DefaultTreeModel
import javax.swing.tree.TreeNode

class DirectoryViewModel extends DefaultTreeModel {

    DirectoryViewModel(TreeNode root) {
        super(root)
    }
}
