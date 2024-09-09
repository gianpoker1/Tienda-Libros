package com.managementBook.Management_Book.view;

import com.managementBook.Management_Book.model.Book;
import com.managementBook.Management_Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

@Component
public class BookForm extends JFrame {
    BookService bookService;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField idTexto;
    private JTextField libroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JTextField existenciaTexto;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton deleteButton;
    private JButton limpiarButton;
    private DefaultTableModel tableModelBooks;

    @Autowired
    public BookForm(BookService bookService){
        this.bookService = bookService;
        iniciarForma();
        agregarButton.addActionListener(e -> agregarBook());
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarLibroSeleccionado();
            }
        });
        actualizarButton.addActionListener(e -> actualizarBook());

        deleteButton.addActionListener(e -> deleteBook());
        limpiarButton.addActionListener(e -> limpiarFormulario());
    }


    private void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth() / 2);
        int y = (tamanioPantalla.height - getHeight() / 2);
        setLocation(x,y);
    }

    private void agregarBook(){
        //leer los valores del formulario
        if(libroTexto.getText().isEmpty()){
            mostrarMensaje("Proporciona el nombre del libro");
            libroTexto.requestFocusInWindow();
            return;
        }else if (idTexto.getText().isEmpty()){
            var nombreLibro = libroTexto.getText();
            var autor = autorTexto.getText();
            var precio = Double.parseDouble(precioTexto.getText());
            var existencias = Integer.parseInt(existenciaTexto.getText());

            //crear el objeto libro
            var book = new Book();
            book.setNombre(nombreLibro);
            book.setAutor(autor);
            book.setPrecio(precio);
            book.setExistencias(existencias);
            this.bookService.saveBook(book);
            mostrarMensaje("Se añadio el libro.");
            limpiarFormulario();
            listarLibros();
        }
        mostrarMensaje("El libro ya existe.");


    }

    private void cargarLibroSeleccionado(){
        //los indices de las columnas inician en 0
        var fila = tablaLibros.getSelectedRow();
        if(fila != -1){//regresa -1 si no se selecciono ningun registro
            //recuperamos los datos de la fila seleccionada de la tabla
            //y llenamos el formulario con los datos de la tabla
            String idLibro = tablaLibros.getModel().getValueAt(fila, 0).toString();
            //objeto JtextField oculto para guardar el id del libro seleccionado
            idTexto.setText(idLibro);
            String nombre= tablaLibros.getModel().getValueAt(fila, 1).toString();
            libroTexto.setText(nombre);
            String autor = tablaLibros.getModel().getValueAt(fila, 2).toString();
            autorTexto.setText(autor);
            String precio = tablaLibros.getModel().getValueAt(fila, 3).toString();
            precioTexto.setText(precio);
            String existencias = tablaLibros.getModel().getValueAt(fila, 4).toString();
            existenciaTexto.setText(existencias);
        }

    }

    private void actualizarBook(){
        if (this.idTexto.getText().isEmpty()){
            mostrarMensaje("Debe seleccionar un libro.");
        }else{

            //verificamos que el nombre de libro no sea nulo
            if(libroTexto.getText().isEmpty()){
                mostrarMensaje("Proporciona el nombre del libro");
                libroTexto.requestFocusInWindow();
                return;
            }else{

                //llenamos el objeto de libro a actualizar
                int idBook = Integer.parseInt(idTexto.getText());
                var nombreBook = libroTexto.getText();
                var autor = autorTexto.getText();
                var precio = Double.parseDouble(precioTexto.getText());
                var existencias = Integer.parseInt(existenciaTexto.getText());
                var book = new Book(idBook, nombreBook,autor,  precio, existencias);
                bookService.saveBook(book);
                mostrarMensaje("Se actualizo el registro.");
                listarLibros();

            }

        }
    }

    private void deleteBook(){
        var fila = tablaLibros.getSelectedRow();
        if(fila != -1){
            //recuperamos el id de la fila seleccionada
            String idBook = tablaLibros.getModel().getValueAt(fila, 0).toString();
            var book = new Book();
            book.setIdBook(Integer.parseInt(idBook));
            bookService.deleteBook(book);
            mostrarMensaje("Libro Eliminado con exito.");
            limpiarFormulario();
            listarLibros();
        }else{
            mostrarMensaje("No se selecciono ningun libro.");
        }
    }

    private void limpiarFormulario(){
        libroTexto.setText("");
        autorTexto.setText("");
        precioTexto.setText("");
        existenciaTexto.setText("");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //Creamos el elemento idTexto oculto
        idTexto = new JTextField("");
        idTexto.setVisible(false);

        this.tableModelBooks = new DefaultTableModel(0, 5){
            //desactivar la funcion de edicion en la tabla
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        String[] cabeceros = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        this.tableModelBooks.setColumnIdentifiers(cabeceros);
        //instanciar el objeto JTable
        this.tablaLibros = new JTable(tableModelBooks);
        //evitar seleccionar varios registros
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarLibros();
    }

    private void listarLibros(){
        //limpiar la tabla
        tableModelBooks.setRowCount(0);
        //Obtener los libros de la DB
        var books = bookService.listBooks();
        books.forEach((libro)-> {
            Object[] lineaLibro = {
              libro.getIdBook(),
              libro.getNombre(),
              libro.getAutor(),
              libro.getPrecio(),
              libro.getExistencias()
            };
            this.tableModelBooks.addRow(lineaLibro);
        });
    }
}
