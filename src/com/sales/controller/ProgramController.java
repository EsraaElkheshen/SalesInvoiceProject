
package com.sales.controller;

import com.sales.model.InvoiceHeader;
import com.sales.model.InvoicesOfTableModel;
import com.sales.model.LineHeader;
import com.sales.model.LinesOfTableModel;
import com.sales.view.InvoiceFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProgramController implements ActionListener, ListSelectionListener {
    private InvoiceFrameView frameview;
    
    public ProgramController(InvoiceFrameView frame) {
        this.frameview = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand=e.getActionCommand();
        // print all actions
       System.out.println("Action is : "+ actionCommand);
       //switch to all actions
       switch(actionCommand){
           case "Load File":
               loadFile();
               break;
                case "Save File":
                saveFile();
               break;
                case "New Invoice":
                    newInvoice();
               break;
                case "Delete Invoice":
                    deleteInvoice();
               break;
                case "New Line":
                    newLine();
               break;
                case "Delete Line":
                    deleteLine();
               break;
       
       
       }
    }
    
      @Override
    public void valueChanged(ListSelectionEvent v) {
        int selectedIndex = frameview.getInvoiceTable().getSelectedRow();
        System.out.println("please select row: " + selectedIndex);
        InvoiceHeader currentInvoice = frameview.getInvoices().get(selectedIndex);
        frameview.getInvoiceNumLabel().setText(""+currentInvoice.getNumber());
        frameview.getInvoiceDateLabel().setText(currentInvoice.getDate());
        frameview.getCustomerNameLabel().setText(currentInvoice.getCustomerName());
        frameview.getInvoiceTotalLabel().setText(""+currentInvoice.getInvoiceTotal());
        LinesOfTableModel linesTableModel = new LinesOfTableModel(currentInvoice.getLines());
        frameview.getLineTable().setModel(linesTableModel);
        linesTableModel.fireTableDataChanged();
    }
    // to load file
    private void loadFile() {
        JFileChooser lf = new JFileChooser();
        try {
            int result = lf.showOpenDialog(frameview);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = lf.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                System.out.println("Read Invoices");
                ArrayList<InvoiceHeader> invoicesArray = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] headerParts = headerLine.split(",");
                    int invoiceNumber = Integer.parseInt(headerParts[0]);
                    String invoiceDate = headerParts[1];
                    String customerName = headerParts[2];
                    
                    InvoiceHeader invoiceHeader = new InvoiceHeader(invoiceNumber, invoiceDate, customerName);
                    invoicesArray.add(invoiceHeader);
                }
                System.out.println("Please check point");
                result = lf.showOpenDialog(frameview);
                if(result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = lf.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    System.out.println("Please Read Line");
                    for (String lineLine : lineLines) {
                        String lineParts[] = lineLine.split(",");
                        int invoiceNumber = Integer.parseInt(lineParts[0]);
                        String itemName = lineParts[1];
                        double itemPrice = Double.parseDouble(lineParts[2]);
                        int count = Integer.parseInt(lineParts[3]);
                        InvoiceHeader invo = null;
                        for (InvoiceHeader invoice : invoicesArray) {
                            if (invoice.getNumber() == invoiceNumber) {
                                invo = invoice;
                                break;
                            }
                        }
                        
                        LineHeader line = new LineHeader(itemName, itemPrice, count, invo);
                        invo.getLines().add(line);
                    }
                    System.out.println("Please check point");
                }
                frameview.setInvoices(invoicesArray);
                InvoicesOfTableModel invoicesTableModel = new InvoicesOfTableModel(invoicesArray);
                frameview.setInvoicesTableModel(invoicesTableModel);
                frameview.getInvoiceTable().setModel(invoicesTableModel);
                frameview.getInvoicesTableModel().fireTableDataChanged();
            }
        } catch (IOException E) {
            E.printStackTrace();
        }
    }
    private void saveFile() {
        
    }

    private void newInvoice() {
       
    }

    private void deleteInvoice() {
        
    }

    private void newLine() {
        
    }

    private void deleteLine() {
        
    }
    
}
