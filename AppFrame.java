import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
class AppFrame extends JFrame implements ActionListener{
    JButton compressButton;
    JButton decompressButton;
    AppFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        compressButton =new JButton("Compress");
        compressButton.setBounds(20,100,200,30);
        compressButton.addActionListener(this);

        decompressButton=new JButton("Decompress");
        decompressButton.setBounds(260, 100, 200, 30);
        decompressButton.addActionListener(this);

        this.add(compressButton);
        this.add(decompressButton);
        this.setSize(500,300);
        this.getContentPane().setBackground(Color.black);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==compressButton){
            JFileChooser fileChooser=new JFileChooser();
            int response=fileChooser.showOpenDialog(this);
            if(response==JFileChooser.APPROVE_OPTION){
                File path=fileChooser.getSelectedFile();
                try {
                    compressor.method(path);
                    JOptionPane.showMessageDialog(this, "File Compressed Successfuly..\nSaved as :- "+path.getName()+".gz");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this,"Compression Error"+e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if(e.getSource()==decompressButton){
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".gz");
                }
                public String getDescription() {
                    return "GZIP Files (*.gz)";
                }
            });
            int response=fileChooser.showOpenDialog(this);
            if(response==JFileChooser.APPROVE_OPTION){
                File f=fileChooser.getSelectedFile();
                try {
                    if(!f.getName().endsWith(".gz")){
                        throw new IOException("Please select a .gz file");
                    }
                    decompressor.method(f);
                    JOptionPane.showMessageDialog(this, 
                        "File decompressed successfully!\nSaved as: " + 
                        f.getName().substring(0, f.getName().length() - 3),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, 
                        "Decompression error: " + ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    } 
}