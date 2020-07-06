/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import file.WriteFile;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

/**
 *
 * @author phamduchoang
 */
public class Notepad extends javax.swing.JFrame {

    /**
     * Creates new form Notepad
     */
    String temp;
    String findStr;
    boolean findDown;
    PrinterJob pj;
    boolean isNewFile;
    String filePath;
    String textoriginal;
    private UndoManager um;

    public Notepad() {
        initComponents();
        um = new UndoManager();
        Document doc = this.txtTextArea.getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });
        setNotepad();
    }

    public void setNotepad() {
        this.setTitle("My Text Editor");
        temp = txtTextArea.getText();
        findDown = true;
        findStr = "";
        isNewFile = true;
        textoriginal = "";
        filePath = "";
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title + " (MTE)"); //To change body of generated methods, choose Tools | Templates.
    }

    public void setFindStr(String findStr) {
        this.findStr = findStr;
    }

    public void setFindDown(boolean findDown) {
        this.findDown = findDown;
    }

    public JTextArea getjTextAreaNote() {
        return txtTextArea;
    }

    public String getFindStr() {
        return findStr;
    }

    private boolean Verify_Save() {
        if (!textoriginal.equals(txtTextArea.getText())) {
            int result;
            Object[] options = {"Save", "Don't save", "Cancel"};
            if (!isNewFile) {
                result = JOptionPane.showOptionDialog(this,
                        "Do you want to save change to"
                        + filePath, "Notepad",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            } else {
                String file = this.getTitle().replaceAll("- Notepad", "");
                result = JOptionPane.showOptionDialog(this,
                        "Do you want to save change to"
                        + file, "Notepad",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if (result == JOptionPane.YES_OPTION) {
                if (!isNewFile) {
                    try {
                        WriteFile wf = new WriteFile(filePath, txtTextArea.getText());
                        wf.Write();
                    } catch (Exception e) {

                    }
                } else {
                    JFileChooser save = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                    save.setFileFilter(filter);
                    int option = save.showSaveDialog(this);
                    if (option == JFileChooser.APPROVE_OPTION) {

                        try {
                            BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath() + ".txt"));
                            out.write(this.txtTextArea.getText());
                            out.close();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                return false;

            }
        }
        return true;
    }

    public void ReplaceAll(String content) {
        String t = txtTextArea.getText();
        t = t.replaceAll(findStr, content);
        txtTextArea.setText(t);
    }

    public void FindText() {
        String t = txtTextArea.getText();
        int posStart = txtTextArea.getCaretPosition();
        int pos;
        if (findDown) {
            pos = t.indexOf(findStr, posStart);
            if (pos == -1) {
                JOptionPane.showMessageDialog(this, "Not found!");
                txtTextArea.setCaretPosition(posStart);
            } else {
                txtTextArea.select(pos, pos + findStr.length());
            }
        } else {
            if (txtTextArea.getSelectedText() != null) {
                posStart -= txtTextArea.getSelectedText().length();
            }
            t = t.substring(0, posStart);
            pos = t.lastIndexOf(findStr);
            if (pos == -1) {
                JOptionPane.showMessageDialog(this, "Not found!");
                txtTextArea.setCaretPosition(posStart);
            } else {
                txtTextArea.select(pos, pos + findStr.length());
            }
        }
    }

    public void ReplaceText(String content) {
        if (txtTextArea.getSelectedText() != null) {
            txtTextArea.replaceSelection(content);
        }
        String t = txtTextArea.getText();
        int posStart = txtTextArea.getCaretPosition();
        int pos = t.indexOf(findStr, posStart);
        if (pos == -1) {
            posStart = 0;
            pos = t.indexOf(findStr, posStart);
            if (pos == -1) {
                JOptionPane.showMessageDialog(this, "Not found!");
                return;
            }
        }
        txtTextArea.select(pos, pos + findStr.length());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtTextArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile1 = new javax.swing.JMenu();
        jMenuItemNew = new javax.swing.JMenuItem();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemSaveAs = new javax.swing.JMenuItem();
        jSeparatorFile3 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuEdit1 = new javax.swing.JMenu();
        jMenuItemUndo = new javax.swing.JMenuItem();
        jMenuItemRedo = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItemCut = new javax.swing.JMenuItem();
        jMenuItemCopy = new javax.swing.JMenuItem();
        jMenuItemPaste = new javax.swing.JMenuItem();
        jMenuItemDel = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItemFind = new javax.swing.JMenuItem();
        jMenuItemReplace = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSelectAll = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItemFont = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtTextArea.setColumns(20);
        txtTextArea.setRows(5);
        jScrollPane2.setViewportView(txtTextArea);

        jMenuFile1.setMnemonic('f');
        jMenuFile1.setText("File");

        jMenuItemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNew.setMnemonic('n');
        jMenuItemNew.setText("New");
        jMenuItemNew.setToolTipText("");
        jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewActionPerformed(evt);
            }
        });
        jMenuFile1.add(jMenuItemNew);

        jMenuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemOpen.setMnemonic('o');
        jMenuItemOpen.setText("Open");
        jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile1.add(jMenuItemOpen);

        jMenuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSave.setMnemonic('s');
        jMenuItemSave.setText("Save");
        jMenuItemSave.setToolTipText("");
        jMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveActionPerformed(evt);
            }
        });
        jMenuFile1.add(jMenuItemSave);

        jMenuItemSaveAs.setMnemonic('a');
        jMenuItemSaveAs.setText("Save As...");
        jMenuItemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveAsActionPerformed(evt);
            }
        });
        jMenuFile1.add(jMenuItemSaveAs);
        jMenuFile1.add(jSeparatorFile3);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemExit.setMnemonic('x');
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile1.add(jMenuItemExit);

        jMenuBar1.add(jMenuFile1);

        jMenuEdit1.setMnemonic('e');
        jMenuEdit1.setText("Edit");
        jMenuEdit1.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenuEdit1MenuSelected(evt);
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
        });

        jMenuItemUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemUndo.setMnemonic('u');
        jMenuItemUndo.setText("Undo");
        jMenuItemUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUndoActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemUndo);

        jMenuItemRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRedo.setMnemonic('r');
        jMenuItemRedo.setText("Redo");
        jMenuItemRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRedoActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemRedo);
        jMenuEdit1.add(jSeparator5);

        jMenuItemCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCut.setMnemonic('t');
        jMenuItemCut.setText("Cut");
        jMenuItemCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCutActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemCut);

        jMenuItemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCopy.setMnemonic('c');
        jMenuItemCopy.setText("Copy");
        jMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopyActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemCopy);

        jMenuItemPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemPaste.setMnemonic('p');
        jMenuItemPaste.setText("Paste");
        jMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPasteActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemPaste);

        jMenuItemDel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuItemDel.setMnemonic('l');
        jMenuItemDel.setText("Delete");
        jMenuItemDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDelActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemDel);
        jMenuEdit1.add(jSeparator6);

        jMenuItemFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFind.setMnemonic('f');
        jMenuItemFind.setText("Find...");
        jMenuItemFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFindActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemFind);

        jMenuItemReplace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemReplace.setMnemonic('r');
        jMenuItemReplace.setText("Replace...");
        jMenuItemReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReplaceActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemReplace);
        jMenuEdit1.add(jSeparator7);

        jMenuItemSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSelectAll.setMnemonic('a');
        jMenuItemSelectAll.setText("Select All");
        jMenuItemSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectAllActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemSelectAll);
        jMenuEdit1.add(jSeparator8);

        jMenuItemFont.setText("Font...");
        jMenuItemFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFontActionPerformed(evt);
            }
        });
        jMenuEdit1.add(jMenuItemFont);

        jMenuBar1.add(jMenuEdit1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewActionPerformed
        // TODO add your handling code here:
        if (!Verify_Save()) {
            return;
        }
        isNewFile = true;
        txtTextArea.setText("");
    }//GEN-LAST:event_jMenuItemNewActionPerformed

    private void jMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenActionPerformed
        // TODO add your handling code here:
        if (!Verify_Save()) {
            return;
        }
        JFileChooser open = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        open.setFileFilter(filter);
        int option = open.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            this.txtTextArea.setText("");
            try {
                Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                while (scan.hasNext()) {
                    this.txtTextArea.append(scan.nextLine() + "\n");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Can't open file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItemOpenActionPerformed

    private void jMenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveActionPerformed
        // TODO add your handling code here:
        if (!textoriginal.equals(txtTextArea.getText())) {
            if (!isNewFile) {
                try {
                    textoriginal = txtTextArea.getText();
                    WriteFile wf = new WriteFile(filePath, textoriginal);
                    wf.Write();
                } catch (Exception e) {
                }
            } else {
                JFileChooser save = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                save.setFileFilter(filter);
                int option = save.showSaveDialog(this);
                if (option == JFileChooser.APPROVE_OPTION) {

                    try {
                        textoriginal = txtTextArea.getText();
                        filePath = save.getSelectedFile().getPath() + ".txt";
                        WriteFile wf = new WriteFile(filePath, textoriginal);
                        wf.Write();
                        isNewFile = false;
                        setTitle(save.getSelectedFile().getName());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }//GEN-LAST:event_jMenuItemSaveActionPerformed

    private void jMenuItemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveAsActionPerformed
        // TODO add your handling code here:
        JFileChooser save = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        save.setFileFilter(filter);
        int option = save.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {

            try {
                textoriginal = txtTextArea.getText();
                filePath = save.getSelectedFile().getPath() + ".txt";
                WriteFile wf = new WriteFile(filePath, textoriginal);
                wf.Write();
                isNewFile = false;
                setTitle(save.getSelectedFile().getName());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jMenuItemSaveAsActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        // TODO add your handling code here:
        if (!Verify_Save()) {
            return;
        }
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUndoActionPerformed
        // TODO add your handling code here:
        if (um.canUndo()) {
            try {
                um.undo();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jMenuItemUndoActionPerformed

    private void jMenuItemRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRedoActionPerformed
        // TODO add your handling code here:
        if (um.canRedo()) {
            try {
                um.redo();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jMenuItemRedoActionPerformed

    private void jMenuItemCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCutActionPerformed
        // TODO add your handling code here:
        txtTextArea.cut();
    }//GEN-LAST:event_jMenuItemCutActionPerformed

    private void jMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopyActionPerformed
        // TODO add your handling code here:
        txtTextArea.copy();
    }//GEN-LAST:event_jMenuItemCopyActionPerformed

    private void jMenuItemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPasteActionPerformed
        // TODO add your handling code here:
        txtTextArea.paste();
    }//GEN-LAST:event_jMenuItemPasteActionPerformed

    private void jMenuItemDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDelActionPerformed
        // TODO add your handling code here:
        txtTextArea.replaceSelection("");
    }//GEN-LAST:event_jMenuItemDelActionPerformed

    private void jMenuItemFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFindActionPerformed
        // TODO add your handling code here:
        FindJFrame f = new FindJFrame(this, true);
        f.setVisible(true);
    }//GEN-LAST:event_jMenuItemFindActionPerformed

    private void jMenuItemReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReplaceActionPerformed
        // TODO add your handling code here:
        ReplaceJFrame r = new ReplaceJFrame(this, true);
        r.setVisible(true);
    }//GEN-LAST:event_jMenuItemReplaceActionPerformed

    private void jMenuItemSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectAllActionPerformed
        // TODO add your handling code here:
        txtTextArea.selectAll();
    }//GEN-LAST:event_jMenuItemSelectAllActionPerformed

    private void jMenuItemFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFontActionPerformed
        // TODO add your handling code here:
        FontJFrame f = new FontJFrame(this, true);
        f.setVisible(true);
    }//GEN-LAST:event_jMenuItemFontActionPerformed

    private void jMenuEdit1MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenuEdit1MenuSelected
        // TODO add your handling code here:
        boolean setFind = !"".equals(txtTextArea.getText());
        jMenuItemFind.setEnabled(setFind);
        boolean setEdit = !(txtTextArea.getSelectedText() == null);
        jMenuItemCopy.setEnabled(setEdit);
        jMenuItemCut.setEnabled(setEdit);
        jMenuItemDel.setEnabled(setEdit);
    }//GEN-LAST:event_jMenuEdit1MenuSelected

//    private void formWindowClosing(java.awt.event.WindowEvent evt) {
//        // TODO add your handling code here:
//        if (!Verify_Save()) {
//            return;
//        }
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notepad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit1;
    private javax.swing.JMenu jMenuFile1;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemCut;
    private javax.swing.JMenuItem jMenuItemDel;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemFind;
    private javax.swing.JMenuItem jMenuItemFont;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemPaste;
    private javax.swing.JMenuItem jMenuItemRedo;
    private javax.swing.JMenuItem jMenuItemReplace;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JMenuItem jMenuItemSelectAll;
    private javax.swing.JMenuItem jMenuItemUndo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparatorFile3;
    private javax.swing.JTextArea txtTextArea;
    // End of variables declaration//GEN-END:variables
}
