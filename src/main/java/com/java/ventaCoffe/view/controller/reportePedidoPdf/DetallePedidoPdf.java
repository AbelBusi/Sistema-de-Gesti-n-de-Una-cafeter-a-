package com.java.ventaCoffe.view.controller.reportePedidoPdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.java.ventaCoffe.controller.impl.DetallePedidoServiceImpl;
import com.java.ventaCoffe.model.entity.DetallePedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.List;

@Component
public class DetallePedidoPdf {

    Document document;

    FileOutputStream fileOutputStream;

    @Autowired
    private DetallePedidoServiceImpl detallePedidoService;

    //fuente de titulo y parrafo

    Font fuenteTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
    Font fuenteParrafo = FontFactory.getFont(FontFactory.HELVETICA, 12);

    public void crearDocumento() {
        try {
            document = new Document(PageSize.A4, 35, 30, 50, 50);

            String ruta = System.getProperty("user.home");

            fileOutputStream = new FileOutputStream(ruta + "/Documentos/");

            PdfWriter.getInstance(document,fileOutputStream);

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());

        }
    }

    public void abrirDocumento (){

        document.open();

    }

    public void agregarTitulo(String titulo) throws DocumentException {

        PdfPTable tabla = new PdfPTable(1);
        PdfPCell celda = new PdfPCell(new Phrase(titulo,fuenteTitulo));
        celda.setColspan(5);
        celda.setBorderColor(BaseColor.WHITE);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
        document.add(tabla);

    }

    public void agregarParrafo(String texto) throws DocumentException {

        Paragraph parrafo = new Paragraph();
        parrafo.add(new Phrase(texto,fuenteParrafo));
        document.add(parrafo);

    }

    public void agregarSaltoDeLinea() throws DocumentException {

        Paragraph saltosDeLinea= new Paragraph();
        saltosDeLinea.add(new Phrase(Chunk.NEWLINE));
        saltosDeLinea.add(new Phrase(Chunk.NEWLINE));
        document.add(saltosDeLinea);

    }

    public void agregarTableDetallePedido() throws DocumentException {

        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell("ID Producto");
        tabla.addCell("Nombre Producto");
        tabla.addCell("Cantidad Producto");
        tabla.addCell("Total Producto");
        tabla.addCell("ID Pedido");

        List<DetallePedido> detalles = detallePedidoService.mostrarPedidos();
        for (DetallePedido d:detalles) {
            tabla.addCell(String.valueOf(d.getProducto()));
            tabla.addCell(String.valueOf(d.getNombreDetallePedido()));
            tabla.addCell(String.valueOf(d.getCantidadDetallePedido()));
            tabla.addCell(String.valueOf(d.getTotalDetallePedido()));
            tabla.addCell(String.valueOf(d.getPedido()));
        }
        document.add(tabla);
        cerrarDocumento();



    }

    public void cerrarDocumento(){

        document.close();

    }


}
